//package com.springbootmvcwithentity.demo.service.Acc;
//
//import com.springbootmvcwithentity.demo.dao.CustomerRepository;
//import com.springbootmvcwithentity.demo.entity.Customer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    CustomerRepository customersRepository;
//
//    public CustomUserDetailsService(CustomerRepository customersRepository) {
//        this.customersRepository = customersRepository;
//    }
//
//    //@Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        // Tìm kiếm người dùng trong bảng customers
//        Customer customer = customersRepository.findByEmailOrPhone(email, email);
//
//        // Nếu không tìm thấy người dùng, ném ngoại lệ UsernameNotFoundException
//        if (customer == null) {
//            throw new UsernameNotFoundException("Người dùng không tồn tại");
//        }
//        // Tạo danh sách các vai trò (roles)
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("CUSTOMER"));
//        // Trả về một UserDetails tương ứng với thông tin của khách hàng
//        System.out.println(new User(customer.getEmail(), customer.getPass(), authorities));
//        return new User(customer.getEmail(), customer.getPass(), authorities);
//    }
//
//}
