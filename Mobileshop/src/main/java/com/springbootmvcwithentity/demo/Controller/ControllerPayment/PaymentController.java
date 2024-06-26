package com.springbootmvcwithentity.demo.Controller.ControllerPayment;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootmvcwithentity.demo.ClassSuport.StatusOrder;
import com.springbootmvcwithentity.demo.ClassSuport.StringToList;
import com.springbootmvcwithentity.demo.dao.CustomerRepository;
import com.springbootmvcwithentity.demo.dao.PaymentMethodRepository;
import com.springbootmvcwithentity.demo.dao.PhoneRepository;
import com.springbootmvcwithentity.demo.entity.Authority;
import com.springbootmvcwithentity.demo.entity.Customer;
import com.springbootmvcwithentity.demo.entity.Order;
import com.springbootmvcwithentity.demo.entity.OrderItem;
import com.springbootmvcwithentity.demo.entity.PaymentMethod;
import com.springbootmvcwithentity.demo.entity.Phones;
import com.springbootmvcwithentity.demo.entity.Users;
import com.springbootmvcwithentity.demo.service.Users.UserService;
import com.springbootmvcwithentity.demo.service.authority.AuthorityService;
import com.springbootmvcwithentity.demo.service.order.orderService;
import com.springbootmvcwithentity.demo.service.orderitem.orderitemsService;

@Controller
// @RestController
public class PaymentController {
    private PhoneRepository phoneRepository;
    private CustomerRepository customerRepository;
    private orderitemsService orderitemsservice;
    private orderService ordeservice;
    private PaymentMethodRepository paymentMethodRepository;
    private UserService userService;
    private AuthorityService authorityService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PaymentController(PhoneRepository phoneRepository, CustomerRepository customerRepository,
            orderitemsService orderitemsservice, orderService ordeservice,
            PaymentMethodRepository paymentMethodRepository, UserService userService, AuthorityService authorityService,
            PasswordEncoder passwordEncoder) {
        this.phoneRepository = phoneRepository;
        this.customerRepository = customerRepository;
        this.orderitemsservice = orderitemsservice;
        this.ordeservice = ordeservice;
        this.paymentMethodRepository = paymentMethodRepository;
        this.userService = userService;
        this.authorityService = authorityService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/RequestOrder", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    public String Payment(
            @RequestParam("inputmodel") List<Integer> inputmodel,
            @RequestParam("inputquantity") List<Integer> inputquantity, @RequestParam("customerId") int customerId,
            @RequestParam("email") String email, @RequestParam("Orderdate") Timestamp Orderdate,
            @RequestParam("paymentMethod") String paymentMethodname, @RequestParam("totalAmount") String totalAmount,
            @RequestParam("numberOrAdresspayment") String numberOrAdresspayment,
            @RequestParam(value = "cvv", required = false, defaultValue = "") String cvv,
            @RequestParam(value = "dateProcessed") String dateProcessed,
            @RequestParam(value = "expirationdate", required = false, defaultValue = "") String expirationdate) {

        Customer customer = customerRepository.findByEmail(email);
        OrderRequest(customer, inputmodel, inputquantity, paymentMethodname, totalAmount, numberOrAdresspayment, cvv,
                expirationdate, Orderdate, dateProcessed);
        return "/phones/RequestOrder-success";
    }

    @PostMapping(value = "/anonymousUser/RequestOrder", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    public String anonymousUserPayment(
            Model model,
            @RequestParam("inputmodel") List<Integer> inputmodel,
            @RequestParam("inputquantity") List<Integer> inputquantity, @RequestParam("emailunknow") String emailunknow,
            @RequestParam("nameunknow") String nameunknow, @RequestParam("phoneunknow") String phoneunknow,
            @RequestParam("addressunknow") String addressunknow, @RequestParam("Orderdate") Timestamp Orderdate,
            @RequestParam("paymentMethod") String paymentMethodname, @RequestParam("totalAmount") String totalAmount,
            @RequestParam("numberOrAdresspayment") String numberOrAdresspayment,
            @RequestParam(value = "cvv", required = false, defaultValue = "") String cvv,
            @RequestParam(value = "dateProcessed") String dateProcessed,
            @RequestParam(value = "expirationdate", required = false, defaultValue = "") String expirationdate) {

        Customer cus = customerRepository.findByEmail(emailunknow);
        if (cus == null) {
            Customer customer = new Customer(nameunknow, emailunknow, phoneunknow, phoneunknow, addressunknow,
                    Orderdate);
            customerRepository.save(customer);
            Users user = new Users(customer, 1);
            user.setPassword(passwordEncoder.encode(phoneunknow));
            userService.save(user);
            Authority authority = new Authority("ROLE_CUSTOMER", user);
            authorityService.createAuthority(authority);

            OrderRequest(customer, inputmodel, inputquantity, paymentMethodname, totalAmount, numberOrAdresspayment,
                    cvv, expirationdate, Orderdate, dateProcessed);

            model.addAttribute("emailunknow", emailunknow);
            model.addAttribute("phoneunknow", phoneunknow);
            return "/phones/RequestOrder-success-unknow";
        } else {
            OrderRequest(cus, inputmodel, inputquantity, paymentMethodname, totalAmount, numberOrAdresspayment, cvv,
                    expirationdate, Orderdate, dateProcessed);
            return "/phones/RequestOrder-success-emailduplicate";
        }
    }

    public List<String> SaveOrderItemWithListSeriPhone(Phones phone, Order order, int qtyInventory, int qtyOrder,
            int missing) {
        List<String> seris = !phone.getSeri().equals("[]") ? new StringToList().StringToList(phone.getSeri())
                : new ArrayList<>();
        List<String> serisOrderItem = new LinkedList<>();
        if (missing > 0) {
            for (int j = 0; j < qtyInventory; j++) {
                serisOrderItem.add(seris.get(j));
            }
            seris.clear();
        } else {
            for (int j = 0; j < qtyOrder; j++) {
                serisOrderItem.add(seris.get(j));
            }

            List<String> remainingItems = new ArrayList<>(seris);
            seris = remainingItems.subList(qtyOrder, remainingItems.size());
        }
        OrderItem orderItem = new OrderItem(order.getOrderID(), phone.getPhoneId(), phone.getPrice(), qtyOrder,
                serisOrderItem.toString(), missing, StatusOrder.PENDING_APPROVAL);
        orderitemsservice.save(orderItem);
        return seris;
    }

    public void OrderRequest(Customer customer, List<Integer> inputmodel, List<Integer> inputquantity,
            String paymentMethodname, String totalAmount,
            String numberOrAdresspayment, String cvv, String expirationdate,
            Timestamp Orderdate, String dateProcessed) {
        PaymentMethod paymentMethod = paymentMethodRepository.findByMethodName(paymentMethodname);
        double total = Double.parseDouble(totalAmount);
        Order order = new Order(customer, Orderdate.toString(), dateProcessed, paymentMethod, total,
                numberOrAdresspayment, cvv, expirationdate, StatusOrder.PENDING_APPROVAL);
        ordeservice.save(order);
        for (int i = 0; i < inputmodel.size(); i++) {
            /** Lọc lấy các phone còn trong kho */
            Phones phone = phoneRepository.findByModelID(inputmodel.get(i));
            int qtyInventory = phone.getQuantity();
            int qtyOrder = inputquantity.get(i);
            if (0 <= qtyInventory && qtyInventory <= qtyOrder) {
                int missing = (qtyOrder - qtyInventory);
                List<String> seris = SaveOrderItemWithListSeriPhone(phone, order, qtyInventory, qtyOrder, missing);
                phone.setSeri(seris.toString());
                phone.setQuantity(0);
                phoneRepository.save(phone);
            } else if (qtyInventory > qtyOrder) {
                int missing = 0;
                List<String> seris = SaveOrderItemWithListSeriPhone(phone, order, qtyInventory, qtyOrder, missing);
                phone.setSeri(seris.toString());
                phone.setQuantity(qtyInventory - qtyOrder);
                phoneRepository.save(phone);
            }
        }
    }

}
