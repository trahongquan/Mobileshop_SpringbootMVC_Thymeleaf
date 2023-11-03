package com.springbootmvcwithentity.demo.Controller.AccManager;

import com.springbootmvcwithentity.demo.dao.AuthorityRepository;
import com.springbootmvcwithentity.demo.dao.UserRepository;
import com.springbootmvcwithentity.demo.dto.AccCustomerDTO;
import com.springbootmvcwithentity.demo.entity.Authority;
import com.springbootmvcwithentity.demo.entity.Customer;
import com.springbootmvcwithentity.demo.entity.Users;
import com.springbootmvcwithentity.demo.service.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class AccManagerController {


    private CustomerService customerService;
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;

    @Autowired
    public AccManagerController(CustomerService customerService, UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.customerService = customerService;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }


    @GetMapping("/Handshop/admin/AccCustomerManager")
    public String AccManager(Model model){
        List<Customer> customers = customerService.findAll();
        List<AccCustomerDTO> accCustomerDTOS = new LinkedList<>();
        customers.forEach(customer -> {
            Users user = userRepository.findByUsername(customer.getEmail());
            Authority authority = authorityRepository.findByUsername(customer.getEmail());
            AccCustomerDTO accCustomerDTO = new AccCustomerDTO(customer, user, authority);
            accCustomerDTOS.add(accCustomerDTO);
        });
        model.addAttribute("accCustomerDTOS",accCustomerDTOS);
        return "admin/AccManager";
    }

    /******************************************************************************************************/
                                        /**  */
    /******************************************************************************************************/


}
