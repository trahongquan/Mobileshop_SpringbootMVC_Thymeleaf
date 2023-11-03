package com.springbootmvcwithentity.demo.Controller.ControllerCustomer;

import com.springbootmvcwithentity.demo.ClassSuport.StringToList;
import com.springbootmvcwithentity.demo.dao.*;
//import com.springbootmvcwithentity.demo.dto.OrderDTO;
//import com.springbootmvcwithentity.demo.dto.OrderitemDTO;
import com.springbootmvcwithentity.demo.dto.OrderDTO;
import com.springbootmvcwithentity.demo.dto.OrderitemDTO;
import com.springbootmvcwithentity.demo.dto.PhoneDTO;
import com.springbootmvcwithentity.demo.entity.*;
//import com.springbootmvcwithentity.demo.service.Customer.CustomerService;
import com.springbootmvcwithentity.demo.service.Customer.CustomerService;
import com.springbootmvcwithentity.demo.service.Phone.PhoneService;
import com.springbootmvcwithentity.demo.service.Users.UserService;
import com.springbootmvcwithentity.demo.service.authority.AuthorityService;
import com.springbootmvcwithentity.demo.service.brand.BrandService;
import com.springbootmvcwithentity.demo.service.categorie.CategoryService;
//import com.springbootmvcwithentity.demo.service.order.orderService;
//import com.springbootmvcwithentity.demo.service.orderitem.orderitemsService;
//import com.springbootmvcwithentity.demo.service.price.PriceService;
import com.springbootmvcwithentity.demo.service.order.orderService;
import com.springbootmvcwithentity.demo.service.orderitem.orderitemsService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CustomerController {


    private CustomerRepository customerRepository;
    private UserService userService;
    private AuthorityService authorityService;
    private PasswordEncoder passwordEncoder; // Mã hóa mật khẩu customer theo luật BCryt
    private PhoneRepository phoneRepository;
    private PhoneService phoneService;
    private CustomerService customerService;
    private BrandService brandService;
    private CategoryService categoryService;
    private orderitemsService orderitemsservice;
    private orderService orderservice;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository, UserService userService, AuthorityService authorityService, PasswordEncoder passwordEncoder, PhoneRepository phoneRepository, PhoneService phoneService, CustomerService customerService, BrandService brandService, CategoryService categoryService, orderitemsService orderitemsservice, orderService orderservice, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.customerRepository = customerRepository;
        this.userService = userService;
        this.authorityService = authorityService;
        this.passwordEncoder = passwordEncoder;
        this.phoneRepository = phoneRepository;
        this.phoneService = phoneService;
        this.customerService = customerService;
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.orderitemsservice = orderitemsservice;
        this.orderservice = orderservice;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }



    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("user", new Users());
        model.addAttribute("authority", new Authority());

        return "/customer/registration-form";
    }

    @PostMapping("/register")
    public String registerCustomer(Customer customer, Authority authority, Users user) {

        String encodedPassword = passwordEncoder.encode(customer.getPass());

        customerRepository.save(customer);

        user = new Users(customer, 1);
        user.setPassword(encodedPassword); // Gán mật khẩu đã mã hóa vào đối tượng Users

        userService.save(user);
        authority = new Authority("ROLE_CUSTOMER",user);
        authorityService.createAuthority(authority);
        return "/customer/registration-success"; // Chuyển hướng sau khi đăng ký thành công
    }

    @GetMapping("/CustomerLogin")
    public String showLoginForm(Model model) {
        model.addAttribute("LoginCustomerForm", new LoginCustomerForm());
        return "redirect:/login";
    }

    @GetMapping("/Handshop/myAccount/{username}")
    public String showMyAccount(Model model, @PathVariable("username") String username){
        Customer customerpath = customerRepository.findByEmail(username);
        model.addAttribute("customer",customerpath);

        List<OrderDTO> orderDTOs = InputCustomerOutputListOrderDTO(customerpath);

        LinkedList<OrderDTO> orderDTOsApprove = new LinkedList<>(orderDTOs);
        List<OrderDTO> orderDTOsApprovefilter = orderDTOsApprove.stream().filter(item -> item.getDateProcessed().equals("0000-00-00 00:00:00")).collect(Collectors.toList());
        LinkedList<OrderDTO> orderDTOsNotApprove = new LinkedList<>(orderDTOs);
        List<OrderDTO> orderDTOsNotApprovefilter = orderDTOsNotApprove.stream().filter(item -> !item.getDateProcessed().equals("0000-00-00 00:00:00")).collect(Collectors.toList());


        model.addAttribute("orderDTOsApprovefilter", orderDTOsApprovefilter);
        model.addAttribute("orderDTOsNotApprovefilter", orderDTOsNotApprovefilter);
        model.addAttribute("orderDTOs", orderDTOs);
        return "customer/customerinfo";
    }

    @GetMapping("/Handshop/UpdateCustomerInfo/{id}")
    public String CustomerUpdateCustomerInfo(@PathVariable int id, Model model) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            model.addAttribute("customer", customer);
            return "customer/form-edit-customer";
        } else {
            throw new RuntimeException("Không tìm thấy nhân viên với ID=" + id);
        }
    }

    /** Thêm annotation @Transactional trước phương thức editEmployee sẽ giúp Spring quản lý giao dịch cho bạn.
     *  Điều này sẽ đảm bảo rằng một giao dịch được kích hoạt
     *  trước khi có bất kỳ hoạt động truy cập cơ sở dữ liệu nào được thực hiện
     *  và sẽ được commit sau khi phương thức kết thúc.
     *  Điều này sẽ giúp tránh lỗi không có EntityManager trong luồng hiện tại.*/
    @Transactional
    @PostMapping("/Handshop/UpdateCustomerInfo/{id}")
    public String CustomerUpdateCustomerInfo(@PathVariable int id, @ModelAttribute("customer") Customer updatedCustomer) {
        // Tìm đối tượng Employee hiện có trong cơ sở dữ liệu
        Customer existingCustomer = customerService.findById(id);
        if(updatedCustomer.getEmail().equals(existingCustomer.getEmail())){
            Users user = userService.findByUsername(existingCustomer.getEmail());
            user.setPassword(passwordEncoder.encode(existingCustomer.getPass()));
            userService.save(user);
        }else {
            authorityService.deleteAuthority(existingCustomer.getEmail()); /** xóa authority trước vì có email là khóa ngoại từ bảng user*/
            userService.deleteUser(existingCustomer.getEmail());
            Users user = new Users(updatedCustomer.getEmail(),passwordEncoder.encode(updatedCustomer.getPass()),(long) existingCustomer.getCustomerId(),1);
            userService.save(user);
            Authority authority = new Authority("ROLE_CUSTOMER",user);
            authorityService.createAuthority(authority);
        }
        // Cập nhật thông tin từ updatedCustomer vào existingCustomer
        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPhone(updatedCustomer.getPhone());
        existingCustomer.setPass(updatedCustomer.getPass());
        existingCustomer.setAddress(updatedCustomer.getAddress());

        // Lưu lại thông tin cập nhật vào cơ sở dữ liệu
        customerService.save(existingCustomer);

        return "redirect:/Handshop/myAccount/"+existingCustomer.getEmail();
    }


    /******************************************************************************************************/
                                /** Invoice - Hóa đơn */
    /******************************************************************************************************/

    @GetMapping("/Handshop/invoiceCustomer")
    public String invoiceCustomer(@RequestParam("OrderID") int orderID, Model model){
        Order order = orderservice.findById(orderID);
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderID(orderID);
        List<OrderitemDTO> orderitemDTOs = new LinkedList<>();
        Customer customer = customerService.findById(order.getCustomerId());

        orderItems.forEach(orderItem -> {
            Phones phone = phoneService.findById(orderItem.getPhoneID());
            Brands brand = brandService.findById(phone.getBrandId());
            Categories category = categoryService.findById(phone.getCategoryId());
            OrderitemDTO orderitemDTO = new OrderitemDTO(orderItem, new PhoneDTO(phone,brand,category));
            orderitemDTOs.add(orderitemDTO);
        });

        OrderDTO orderDTO = new OrderDTO(order, orderitemDTOs, customer);
        model.addAttribute("orderDTO", orderDTO);
        return "customer/invoice";
    }

    @PostMapping("/Handshop/editpassCustomer")
    public String editpassCustomer( Model model,
                                    @RequestParam("passCustomer") String passCustomer,
                                    @RequestParam("emailCustomer") String emailCustomer) {
        Customer customer = customerRepository.findByEmail(emailCustomer);
        customer.setPass(passCustomer);
        customerService.save(customer);

        Users user = userService.findByUsername(customer.getEmail());
        user.setPassword(passwordEncoder.encode(customer.getPass()));
        userService.save(user);

        List<OrderDTO> orderDTOs = InputCustomerOutputListOrderDTO(customer);

        LinkedList<OrderDTO> orderDTOsApprove = new LinkedList<>(orderDTOs);
        List<OrderDTO> orderDTOsApprovefilter = orderDTOsApprove.stream().filter(item -> item.getDateProcessed().equals("0000-00-00 00:00:00")).collect(Collectors.toList());
        LinkedList<OrderDTO> orderDTOsNotApprove = new LinkedList<>(orderDTOs);
        List<OrderDTO> orderDTOsNotApprovefilter = orderDTOsNotApprove.stream().filter(item -> !item.getDateProcessed().equals("0000-00-00 00:00:00")).collect(Collectors.toList());

        model.addAttribute("orderDTOsApprovefilter", orderDTOsApprovefilter);
        model.addAttribute("orderDTOsNotApprovefilter", orderDTOsNotApprovefilter);
        model.addAttribute("orderDTOs", orderDTOs);
        model.addAttribute("customer",customer);

        return "customer/customerinfo";
    }

    public List<OrderDTO> InputCustomerOutputListOrderDTO(Customer customer){
        int customerid = customer.getCustomerId();
        List<Order> orders = orderRepository.findAllByCustomerId(customerid);
        List<OrderItem> orderItems = orderitemsservice.findAll();
        List<OrderitemDTO> orderitemDTOs = new LinkedList<>(); /** List OrderItemDTO nằm trong OrderDTO */
        List<OrderDTO> orderDTOs = new LinkedList<>(); /** List OrderItemDTO nằm trong OrderDTO */

        /** Tạo một List orderItemDTOs: danh sách các sản phẩm được mua*/
        orderItems.forEach(orderItem -> {
            List<String> serisOrder = !orderItem.getSeri().equals("[]") ? new StringToList().StringToList(orderItem.getSeri()) : new ArrayList<>();/** Chuyển chuỗi phoneID thành List PhoneID*/
            OrderitemDTO orderitemDTO = new OrderitemDTO(orderItem, serisOrder);
            orderitemDTOs.add(orderitemDTO);
        });

        /** Tạo một List orderDTOs: danh sách các hóa đơn*/
        orders.forEach(order -> {
            List<String> serisOrder = new LinkedList<>(); /** List phone nằm trong orderItem*/
            orderitemDTOs.forEach(item->item.getSeris().forEach(seriItem->serisOrder.add(seriItem)));
//            String employeeID = !order.getEmployeeID().equals("") ? order.getEmployeeID() : "";
            Customer customerOrderDTO = customerService.findById(order.getCustomerId());

            List<OrderitemDTO> orderitemDTOs2 = new LinkedList<>();
            List<Phones> phonesOrder = new LinkedList<>();
            orderitemDTOs.forEach(item -> {
                if(item.getOrderID() == order.getOrderID()){
                    phonesOrder.add(phoneService.findById(item.getPhoneID()));
                    orderitemDTOs2.add(item);
                }
            });
            OrderDTO orderDTO = new OrderDTO(order,orderitemDTOs2,serisOrder ,phonesOrder,customerOrderDTO);
            orderDTOs.add(orderDTO);
        });
        return orderDTOs;
    }

    /******************************************************************************************************/
                                /** Hủy Order */
    /******************************************************************************************************/


    @PostMapping("/Handshop/rejectOrder")
    public String rejectOrder(@RequestParam("OrderID") int orderID){
        Order order = orderservice.findById(orderID);
        Customer customer = customerService.findById(order.getCustomerId());
        String email = customer.getEmail();
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderID(orderID);
        orderItems.forEach(orderItem -> {
            List<String> serisOrderItem = !orderItem.getSeri().equals("[]") ? new StringToList().StringToList(orderItem.getSeri()) : new ArrayList<>();
            Phones phone = phoneService.findById(orderItem.getPhoneID());
            List<String> serisPhone = !phone.getSeri().equals("[]") ? new StringToList().StringToList(phone.getSeri()) : new ArrayList<>();

            Set<String> set = new HashSet<>(serisOrderItem);
            set.addAll(serisPhone);
            List<String> mergedList = new ArrayList<>(set);

            phone.setSeri(mergedList.toString());
            phone.setQuantity(mergedList.size());
            phoneRepository.save(phone);
        });
        for (int i = 0; i < orderItems.size(); i++) {
            orderitemsservice.deleteById(orderItems.get(i).getOrderItemID());
        }
        orderservice.deleteById(orderID);

        return "redirect:/Handshop/myAccount/"+email;
    }

    @GetMapping("/Handshop/rejectOrder")
    public String showRejectOrderForm(@RequestParam("OrderID") int orderID, Model model) {
//        int orderID = Integer.parseInt(OrderID);
        Order order = orderservice.findById(orderID);
        if (order != null) {
            model.addAttribute("order", order);
            return "admin/rejectOrderForm";
        } else {
            throw new RuntimeException("Không tìm thấy order với ID=" + orderID);
        }
    }

    /******************************************************************************************************/
                                    /** Q.lí tài khoản Customer - Admin */
    /******************************************************************************************************/


    @GetMapping("/Handshop/admin/UpdateCustomerInfo/{id}")
    public String UpdateCustomerInfo(@PathVariable int id, Model model) {
        Customer customer = customerService.findById(id);
        if (customer != null) {
            model.addAttribute("customer", customer);
            return "customer/form-edit-customer";
        } else {
            throw new RuntimeException("Không tìm thấy nhân viên với ID=" + id);
        }
    }

    /** Thêm annotation @Transactional trước phương thức editEmployee sẽ giúp Spring quản lý giao dịch cho bạn.
     *  Điều này sẽ đảm bảo rằng một giao dịch được kích hoạt
     *  trước khi có bất kỳ hoạt động truy cập cơ sở dữ liệu nào được thực hiện
     *  và sẽ được commit sau khi phương thức kết thúc.
     *  Điều này sẽ giúp tránh lỗi không có EntityManager trong luồng hiện tại.*/
    @Transactional
    @PostMapping("/Handshop/admin/UpdateCustomerInfo/{id}")
    public String editEmployee(@PathVariable int id, @ModelAttribute("customer") Customer updatedCustomer) {
        // Tìm đối tượng Employee hiện có trong cơ sở dữ liệu
        Customer existingCustomer = customerService.findById(id);
        if(updatedCustomer.getEmail().equals(existingCustomer.getEmail())){
            Users user = userService.findByUsername(existingCustomer.getEmail());
            user.setPassword(passwordEncoder.encode(existingCustomer.getPass()));
            userService.save(user);
        }else {
            authorityService.deleteAuthority(existingCustomer.getEmail()); /** xóa authority trước vì có email là khóa ngoại từ bảng user*/
            userService.deleteUser(existingCustomer.getEmail());
            Users user = new Users(updatedCustomer.getEmail(),passwordEncoder.encode(updatedCustomer.getPass()),(long) existingCustomer.getCustomerId(),1);
            userService.save(user);
            Authority authority = new Authority("ROLE_CUSTOMER",user);
            authorityService.createAuthority(authority);
        }

        // Cập nhật thông tin từ updatedCustomer vào existingCustomer
        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPhone(updatedCustomer.getPhone());
        existingCustomer.setPass(updatedCustomer.getPass());
        existingCustomer.setAddress(updatedCustomer.getAddress());

        // Lưu lại thông tin cập nhật vào cơ sở dữ liệu
        customerService.save(existingCustomer);

        return "redirect:/Handshop/admin/AccCustomerManager";
    }

    @GetMapping("/Handshop/admin/DeleteCustomer/{id}")
    public String DeleteCustomer(@PathVariable String id, Model model) {
        int idt = Integer.parseInt(id);
        Customer customer = customerService.findById(idt);
        if (customer != null) {
            model.addAttribute("customer", customer);
            return "customer/delete";
        } else {
            throw new RuntimeException("Không tìm thấy nhân viên với ID=" + idt);
        }
    }
    @Transactional
    @PostMapping("/Handshop/admin/DeleteCustomer/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Customer customer = customerService.findById(id);
        authorityService.deleteAuthority(customer.getEmail());
        userService.deleteUser(customer.getEmail());
        customerService.deleteById(id);
        return "redirect:/Handshop/admin/AccCustomerManager";
    }
}

