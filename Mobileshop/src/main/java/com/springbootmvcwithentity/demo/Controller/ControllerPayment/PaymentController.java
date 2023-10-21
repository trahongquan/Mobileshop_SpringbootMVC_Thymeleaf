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
import com.springbootmvcwithentity.demo.service.order.orderService;
import com.springbootmvcwithentity.demo.service.orderitem.orderitemsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
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

    @Autowired
    public PaymentController(PhoneRepository phoneRepository, CustomerRepository customerRepository, orderService ordeservice, orderitemsService orderitemsservice, PaymentMethodRepository paymentMethodRepository) {
        this.phoneRepository = phoneRepository;
        this.customerRepository = customerRepository;
        this.ordeservice = ordeservice;
        this.orderitemsservice = orderitemsservice;
        this.paymentMethodRepository = paymentMethodRepository;
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


    /*@PostMapping(value = "/anonymousUser/RequestOrder", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String anonymousUserPayment(
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
            *//** Lọc lấy các phone còn trong kho*//*
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
    }*/


}
