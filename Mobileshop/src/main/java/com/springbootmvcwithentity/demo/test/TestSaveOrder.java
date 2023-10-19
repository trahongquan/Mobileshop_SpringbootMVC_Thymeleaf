/*
package com.springbootmvcwithentity.demo.test;

import com.springbootmvcwithentity.demo.dao.CustomerRepository;
import com.springbootmvcwithentity.demo.dao.OrderRepository;
import com.springbootmvcwithentity.demo.dao.PaymentMethodRepository;
import com.springbootmvcwithentity.demo.entity.Customer;
import com.springbootmvcwithentity.demo.entity.Order;
import com.springbootmvcwithentity.demo.entity.PaymentMethod;
import com.springbootmvcwithentity.demo.service.order.orderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

public class TestSaveOrder {
    @Autowired
    private OrderRepository orderRepository;
    private orderService orderService;
    private CustomerRepository customerRepository;
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    public TestSaveOrder(OrderRepository orderRepository, CustomerRepository customerRepository, PaymentMethodRepository paymentMethodRepository, orderService orderService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.orderService = orderService;
    }

    public static Connection getConnection(){
        String urlMySQL = "jdbc:mysql://localhost:3306/mobileshopdb";//port=3306
        String user = "root";
        String password = "123456";
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");// nap driver
        }
        catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
        }
        try {
            con = DriverManager.getConnection(urlMySQL,user,password);//ket noi
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return con;
    }

    public static int save(Order order){
        int status=0;
//        Optional<Customer> o = customerRepository.findById(1);
//        PaymentMethod p = paymentMethodRepository.findByMethodName("Credit");
//        Customer customer = o.get();
//        order = new Order(customer,"22/12","",p,1999.00,"9309470284792","234","22/14");
        try{
            Connection con = getConnection();
            orderService orderService =new orderService().save(order);
        }catch(Exception e){System.out.println(e);}
        return status;
    }
    public static void main(String args[]) {
        Customer customer = new Customer("john.doe@example.com","123456","John","02335923","uheiwu","iwudsjknjk", new Timestamp(System.currentTimeMillis()));
        PaymentMethod p = new PaymentMethod("Credit");
        Order order = new Order(customer,"22/12","",p,1999.00,"9309470284792","234","22/14");;
        save(order);
    }
}
*/
