package com.springbootmvcwithentity.demo.Controller.ControllerPhone;


import com.springbootmvcwithentity.demo.dao.*;
import com.springbootmvcwithentity.demo.dto.PhoneDTO;
import com.springbootmvcwithentity.demo.entity.Phones;
import com.springbootmvcwithentity.demo.service.Customer.CustomerService;
import com.springbootmvcwithentity.demo.service.Phone.PhoneService;
import com.springbootmvcwithentity.demo.service.PrdRevService.brand.PrdRevService;
import com.springbootmvcwithentity.demo.service.brand.BrandService;
import com.springbootmvcwithentity.demo.service.categorie.CategoryService;
import com.springbootmvcwithentity.demo.service.order.orderService;
import com.springbootmvcwithentity.demo.service.orderitem.orderitemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@SpringBootApplication
@RequestMapping("/Handshop")
public class ControllerPhoneImport {
    private PhoneRepository phoneRepository;
    private PhoneService phoneService;
    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private BrandService brandService;
    private CategoryService categoryService;
    private orderitemsService orderitemsservice;
    private orderService orderservice;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private EmployeeRepository employeeRepository;
    private PrdRevService prdRevService;
    private PrdRevRepository prdRevRepository;

    @Autowired
    public ControllerPhoneImport(PhoneRepository phoneRepository, PhoneService phoneService, CustomerService customerService, CustomerRepository customerRepository, BrandService brandService, CategoryService categoryService, orderitemsService orderitemsservice, orderService orderservice, OrderRepository orderRepository, OrderItemRepository orderItemRepository, EmployeeRepository employeeRepository, PrdRevService prdRevService, PrdRevRepository prdRevRepository) {
        this.phoneRepository = phoneRepository;
        this.phoneService = phoneService;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.orderitemsservice = orderitemsservice;
        this.orderservice = orderservice;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.employeeRepository = employeeRepository;
        this.prdRevService = prdRevService;
        this.prdRevRepository = prdRevRepository;
    }

    @GetMapping({"/admin/addOrderImport"})
    public String addOrderImport(Model model) {
//        List<Phones> phones = phoneService.findAll();
//        List<PhoneDTO> phoneDTOS = Phone2PhoneDTOS(phones);
//        model.addAttribute("phoneDTOS", phoneDTOS); /** cách xử lý ở backEnd*/
        return "admin/list-phones";
    }
}
