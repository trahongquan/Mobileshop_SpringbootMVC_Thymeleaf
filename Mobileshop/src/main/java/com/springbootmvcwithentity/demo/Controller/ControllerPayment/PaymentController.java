package com.springbootmvcwithentity.demo.Controller.ControllerPayment;

import com.springbootmvcwithentity.demo.ClassSuport.Product;
import com.springbootmvcwithentity.demo.ClassSuport.StringToList;
import com.springbootmvcwithentity.demo.dao.CustomerRepository;
import com.springbootmvcwithentity.demo.dao.OrderItemRepository;
import com.springbootmvcwithentity.demo.dao.PaymentMethodRepository;
import com.springbootmvcwithentity.demo.dao.PhoneRepository;
import com.springbootmvcwithentity.demo.entity.*;
import com.springbootmvcwithentity.demo.service.Customer.CustomerService;
import com.springbootmvcwithentity.demo.service.Customer.CustomerServiceImpl;
import com.springbootmvcwithentity.demo.service.PaymentMethod.PaymentMethodService;
import com.springbootmvcwithentity.demo.service.Users.UserService;
import com.springbootmvcwithentity.demo.service.authority.AuthorityService;
import com.springbootmvcwithentity.demo.service.order.orderService;
import com.springbootmvcwithentity.demo.service.orderitem.orderitemsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
//@RestController
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
    public PaymentController(PhoneRepository phoneRepository, CustomerRepository customerRepository, orderitemsService orderitemsservice, orderService ordeservice, PaymentMethodRepository paymentMethodRepository, UserService userService, AuthorityService authorityService, PasswordEncoder passwordEncoder) {
        this.phoneRepository = phoneRepository;
        this.customerRepository = customerRepository;
        this.orderitemsservice = orderitemsservice;
        this.ordeservice = ordeservice;
        this.paymentMethodRepository = paymentMethodRepository;
        this.userService = userService;
        this.authorityService = authorityService;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostMapping("/RequestOrder")
//    @ResponseBody
//    @RequestMapping(value = "/RequestOrder", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
    @PostMapping(value = "/RequestOrder", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String Payment(
                          @RequestParam("inputmodel") List<String> inputmodel
                        , @RequestParam("inputquantity") List<String> inputquantity
                        , @RequestParam("customerId") int customerId
                        , @RequestParam("email") String email
                        , @RequestParam("Orderdate") String Orderdate
                        , @RequestParam("paymentMethod") String paymentMethodname
                        , @RequestParam("totalAmount") String totalAmount
                        , @RequestParam("numberOrAdresspayment") String numberOrAdresspayment
                        , @RequestParam(value = "cvv", required = false, defaultValue = "") String cvv
                        , @RequestParam(value = "dateProcessed") String dateProcessed
                        , @RequestParam(value = "expirationdate", required = false, defaultValue = "") String expirationdate){

        Customer customer = customerRepository.findByEmail(email);
        PaymentMethod paymentMethod = paymentMethodRepository.findByMethodName(paymentMethodname);
        double total = Double.parseDouble(totalAmount);
        Order order = new Order(customer ,Orderdate,dateProcessed, paymentMethod ,total,numberOrAdresspayment,cvv,expirationdate);
        ordeservice.save(order);
        for (int i = 0; i < inputmodel.size(); i++) {
            /** Lọc lấy các phone còn trong kho*/
            Phones phone = phoneRepository.findByModel(inputmodel.get(i));

            int qtyInventory = phone.getQuantity();
            int qtyOrder = Integer.parseInt(inputquantity.get(i));

            if(qtyInventory <= qtyOrder){
                int missing = (qtyOrder - qtyInventory);
                List<String> seris = !phone.getSeri().equals("[]") ? new StringToList().StringToList(phone.getSeri()) : new ArrayList<>();
                List<String> serisOrderItem = new LinkedList<>();
                for (int j = 0; j < qtyInventory; j++) {
                    serisOrderItem.add(seris.get(j));
                }
                OrderItem orderItem = new OrderItem(order.getOrderID(),phone.getPhoneId(),phone.getPrice(),qtyOrder,serisOrderItem.toString(),missing);
                orderitemsservice.save(orderItem);
                seris.clear();
                phone.setSeri(seris.toString());
                phone.setQuantity(0);
                phoneRepository.save(phone);
            }
            if(qtyInventory > qtyOrder){
                int missing = 0;
                List<String> seris = !phone.getSeri().equals("[]") ? new StringToList().StringToList(phone.getSeri()) : new ArrayList<>();
                List<String> serisOrderItem = new LinkedList<>();
                for (int j = 0; j < qtyOrder; j++) {
                    serisOrderItem.add(seris.get(j));
                }
                OrderItem orderItem = new OrderItem(order.getOrderID(),phone.getPhoneId(),phone.getPrice(),qtyOrder,serisOrderItem.toString(),missing);
                orderitemsservice.save(orderItem);
                if (qtyOrder <= seris.size()) {
                    seris.subList(0, qtyOrder).clear();
                }
                phone.setSeri(seris.toString());
                phone.setQuantity(qtyInventory - qtyOrder);
                phoneRepository.save(phone);
            }
        }
        return "/phones/RequestOrder-success";
    }


    @PostMapping(value = "/anonymousUser/RequestOrder", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String anonymousUserPayment(
            Model model,
            @RequestParam("inputmodel") List<String> inputmodel
            , @RequestParam("inputquantity") List<String> inputquantity
            , @RequestParam("emailunknow") String emailunknow
            , @RequestParam("nameunknow") String nameunknow
            , @RequestParam("phoneunknow") String phoneunknow
            , @RequestParam("addressunknow") String addressunknow
            , @RequestParam("Orderdate") Timestamp Orderdate
            , @RequestParam("paymentMethod") String paymentMethodname
            , @RequestParam("totalAmount") String totalAmount
            , @RequestParam("numberOrAdresspayment") String numberOrAdresspayment
            , @RequestParam(value = "cvv", required = false, defaultValue = "") String cvv
            , @RequestParam(value = "dateProcessed") String dateProcessed
            , @RequestParam(value = "expirationdate", required = false, defaultValue = "") String expirationdate){

        Customer cus = customerRepository.findByEmail(emailunknow);
        if(cus==null) {
            Customer customer = new Customer(nameunknow, emailunknow, phoneunknow, phoneunknow, addressunknow, Orderdate);
            customerRepository.save(customer);
            Users user = new Users(customer, 1);
            user.setPassword(passwordEncoder.encode(phoneunknow));
            userService.save(user);
            Authority authority = new Authority("ROLE_CUSTOMER" , user);
            authorityService.createAuthority(authority);

            OrderRequest(customer, inputmodel, inputquantity,paymentMethodname,totalAmount,numberOrAdresspayment,cvv,expirationdate,Orderdate,dateProcessed);

            model.addAttribute("emailunknow", emailunknow);
            model.addAttribute("phoneunknow",phoneunknow);
            return "/phones/RequestOrder-success-unknow";
        } else {
            OrderRequest(cus, inputmodel, inputquantity,paymentMethodname,totalAmount,numberOrAdresspayment,cvv,expirationdate,Orderdate,dateProcessed);
            return "/phones/RequestOrder-success-emailduplicate";
        }
    }

    public void OrderRequest(Customer customer,List<String> inputmodel, List<String> inputquantity,
                            String paymentMethodname, String totalAmount,
                            String numberOrAdresspayment, String cvv, String expirationdate,
                            Timestamp Orderdate, String dateProcessed){
        PaymentMethod paymentMethod = paymentMethodRepository.findByMethodName(paymentMethodname);
        double total = Double.parseDouble(totalAmount);
        Order order = new Order(customer, Orderdate.toString(), dateProcessed, paymentMethod, total, numberOrAdresspayment, cvv, expirationdate);
        ordeservice.save(order);
        for (int i = 0; i < inputmodel.size(); i++) {
            /** Lọc lấy các phone còn trong kho*/
            Phones phone = phoneRepository.findByModel(inputmodel.get(i));

            int qtyInventory = phone.getQuantity();
            int qtyOrder = Integer.parseInt(inputquantity.get(i));

            if (qtyInventory <= qtyOrder) {
                int missing = (qtyOrder - qtyInventory);
                List<String> seris = !phone.getSeri().equals("[]") ? new StringToList().StringToList(phone.getSeri()) : new ArrayList<>();
                List<String> serisOrderItem = new LinkedList<>();
                for (int j = 0; j < qtyInventory; j++) {
                    serisOrderItem.add(seris.get(j));
                }
                OrderItem orderItem = new OrderItem(order.getOrderID(), phone.getPhoneId(), phone.getPrice(), qtyOrder, serisOrderItem.toString(), missing);
                orderitemsservice.save(orderItem);
                seris.clear();
                phone.setSeri(seris.toString());
                phone.setQuantity(0);
                phoneRepository.save(phone);
            }
            if (qtyInventory > qtyOrder) {
                int missing = 0;
                List<String> seris = !phone.getSeri().equals("[]") ? new StringToList().StringToList(phone.getSeri()) : new ArrayList<>();
                List<String> serisOrderItem = new LinkedList<>();
                for (int j = 0; j < qtyOrder; j++) {
                    serisOrderItem.add(seris.get(j));
                }
                OrderItem orderItem = new OrderItem(order.getOrderID(), phone.getPhoneId(), phone.getPrice(), qtyOrder, serisOrderItem.toString(), missing);
                orderitemsservice.save(orderItem);
                if (qtyOrder <= seris.size()) {
                    seris.subList(0, qtyOrder).clear();
                }
                phone.setSeri(seris.toString());
                phone.setQuantity(qtyInventory - qtyOrder);
                phoneRepository.save(phone);
            }
        }
    }

}
