//package com.springbootmvcwithentity.demo.service.Acc;
//
//import com.springbootmvcwithentity.demo.dao.UserAccountRepository;
//import com.springbootmvcwithentity.demo.entity.UserAccount;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserAccountService {
//    private final UserAccountRepository userAccountRepository;
//
//    @Autowired
//    public UserAccountService(UserAccountRepository userAccountRepository) {
//        this.userAccountRepository = userAccountRepository;
//    }
//
//    // Thêm một tài khoản người dùng mới
//    public UserAccount createUserAccount(UserAccount userAccount) {
//        return userAccountRepository.save(userAccount);
//    }
//
//    // Tìm một tài khoản người dùng theo ID
//    public Optional<UserAccount> getUserAccountById(Long id) {
//        return userAccountRepository.findById(id);
//    }
//
//    // Tìm một tài khoản người dùng theo tên đăng nhập
//    public UserAccount getUserAccountByUsername(String username) {
//        return userAccountRepository.findByUsername(username);
//    }
//
//    // Lấy tất cả các tài khoản người dùng
//    public List<UserAccount> getAllUserAccounts() {
//        return userAccountRepository.findAll();
//    }
//
//    // Xóa một tài khoản người dùng theo ID
//    public void deleteUserAccount(Long id) {
//        userAccountRepository.deleteById(id);
//    }
//
//    // Bạn có thể thêm các phương thức tùy chỉnh khác ở đây nếu cần.
//}
