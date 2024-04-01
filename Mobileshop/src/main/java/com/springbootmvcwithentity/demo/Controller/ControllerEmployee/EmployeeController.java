package com.springbootmvcwithentity.demo.Controller.ControllerEmployee;

import com.springbootmvcwithentity.demo.dao.EmployeeRepository;
import com.springbootmvcwithentity.demo.dao.PhoneRepository;
import com.springbootmvcwithentity.demo.dto.OrderDTO;
import com.springbootmvcwithentity.demo.entity.Authority;
import com.springbootmvcwithentity.demo.entity.Customer;
import com.springbootmvcwithentity.demo.entity.Employees;
import com.springbootmvcwithentity.demo.entity.Users;
import com.springbootmvcwithentity.demo.service.Phone.PhoneService;
import com.springbootmvcwithentity.demo.service.Users.UserService;
import com.springbootmvcwithentity.demo.service.authority.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.springbootmvcwithentity.demo.service.emp.EmployeeService;


@Controller
@RequestMapping("Handshop/admin")
public class EmployeeController {

    private final EmployeeService employeeService;
    private EmployeeRepository employeeRepository;
    private UserService userService;
    private AuthorityService authorityService;
    private PasswordEncoder passwordEncoder; // Mã hóa mật khẩu customer theo luật BCryt
    private PhoneRepository phoneRepository;
    private PhoneService phoneService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository, UserService userService, AuthorityService authorityService, PasswordEncoder passwordEncoder, PhoneRepository phoneRepository, PhoneService phoneService) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.userService = userService;
        this.authorityService = authorityService;
        this.passwordEncoder = passwordEncoder;
        this.phoneRepository = phoneRepository;
        this.phoneService = phoneService;
    }

    @GetMapping({"/AccEmployeesManager/", "/AccEmployeesManager"})
    public String listEmployees(Model model) {
        List<Employees> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "admin/templateAdmin";
    }

    @GetMapping("/AccEmployeesManager/add")
    public String showEmployeeForm(Model model) {
        Employees employee = new Employees();
        model.addAttribute("employee", employee);
        return "admin/templateAdmin";
    }
    @PostMapping(value = "/AccEmployeesManager/add", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String addEmployee(@ModelAttribute("employee") Employees employee, Authority authority, Users user) {
        saveUser(employee, user);
        authority = new Authority("ROLE_EMPLOYEE",user);
        authorityService.createAuthority(authority);
        return "redirect:/Handshop/admin/AccEmployeesManager";
    }

    @GetMapping("/AccEmployeesManager/showFormForUpdate/{id}")
    public String showEditEmployeeForm(@PathVariable int id, Model model) {
        return checkEmployee(model, id);
    }

    private String checkEmployee(Model model, int id){
        Employees employee = employeeService.findById(id);
        if (employee != null) {
            model.addAttribute("employee", employee);
            return "admin/templateAdmin";
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
    @PostMapping("/AccEmployeesManager/showFormForUpdate/{id}")
    public String editEmployee(@PathVariable int id, @ModelAttribute("employee") Employees updatedEmployee) {
        // Tìm đối tượng Employee hiện có trong cơ sở dữ liệu
        Employees existingEmployee = employeeService.findById(id);

        if(updatedEmployee.getEmail().equals(existingEmployee.getEmail())){
            Users user = userService.findByUsername(existingEmployee.getEmail());
            user.setPassword(passwordEncoder.encode(existingEmployee.getPass()));
            userService.save(user);
        }else {
            authorityService.deleteAuthority(existingEmployee.getEmail()); /** xóa authority trước vì có email là khóa ngoại từ bảng user*/
            userService.deleteUser(existingEmployee.getEmail());
            Users user = new Users(updatedEmployee.getEmail(),passwordEncoder.encode(updatedEmployee.getPass()),(long) existingEmployee.getEmployeeID(),1);
            userService.save(user);
            Authority authority = new Authority("ROLE_EMPLOYEE",user);
            authorityService.createAuthority(authority);
        }

        // Cập nhật thông tin từ updatedEmployee vào existingEmployee
        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setPhone(updatedEmployee.getPhone());
        existingEmployee.setPass(updatedEmployee.getPass());
        existingEmployee.setHireEndDate(updatedEmployee.getHireEndDate());

        // Lưu lại thông tin cập nhật vào cơ sở dữ liệu
        employeeService.save(existingEmployee);

        return "redirect:/Handshop/admin/AccEmployeesManager";
    }

    @GetMapping("/AccEmployeesManager/delete/{id}")
    public String showDeleteEmployeeForm(@PathVariable String id, Model model) {
        int idt = Integer.parseInt(id);
        return checkEmployee(model,idt);
    }

    @PostMapping("/AccEmployeesManager/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employees employee = employeeService.findById(id);
        authorityService.deleteAuthority(employee.getEmail());
        userService.deleteUser(employee.getEmail());
        employeeService.deleteById(id);
        return "redirect:/Handshop/admin/AccEmployeesManager";
    }

    public void saveUser(Employees employee, Users user){
        employeeService.save(employee);
        user = userService.findByUsername(employee.getEmail());
        user.setPassword(passwordEncoder.encode(employee.getPass()));
        userService.save(user);
    }

    @GetMapping("/UpdateEmp/{id}")
    public String showFormForUpdateEmp(@PathVariable int id, Model model) {
        return checkEmployee(model, id);
    }

    /** Thêm annotation @Transactional trước phương thức editEmployee sẽ giúp Spring quản lý giao dịch cho bạn.
     *  Điều này sẽ đảm bảo rằng một giao dịch được kích hoạt
     *  trước khi có bất kỳ hoạt động truy cập cơ sở dữ liệu nào được thực hiện
     *  và sẽ được commit sau khi phương thức kết thúc.
     *  Điều này sẽ giúp tránh lỗi không có EntityManager trong luồng hiện tại.*/
    @Transactional
    @PostMapping("/UpdateEmp/{id}")
    public String showFormForUpdateEmp(@PathVariable int id, @ModelAttribute("employee") Employees updatedEmployee) {
        // Tìm đối tượng Employee hiện có trong cơ sở dữ liệu
        Employees existingEmployee = employeeService.findById(id);

        if(updatedEmployee.getEmail().equals(existingEmployee.getEmail())){
            Users user = userService.findByUsername(existingEmployee.getEmail());
            user.setPassword(passwordEncoder.encode(existingEmployee.getPass()));
            userService.save(user);
        }else {
            authorityService.deleteAuthority(existingEmployee.getEmail()); /** xóa authority trước vì có email là khóa ngoại từ bảng user*/
            userService.deleteUser(existingEmployee.getEmail());
            Users user = new Users(updatedEmployee.getEmail(),passwordEncoder.encode(updatedEmployee.getPass()),(long) existingEmployee.getEmployeeID(),1);
            userService.save(user);
            Authority authority = new Authority("ROLE_EMPLOYEE",user);
            authorityService.createAuthority(authority);
        }

        // Cập nhật thông tin từ updatedEmployee vào existingEmployee
        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setPhone(updatedEmployee.getPhone());
        existingEmployee.setPass(updatedEmployee.getPass());
        existingEmployee.setHireEndDate(updatedEmployee.getHireEndDate());

        // Lưu lại thông tin cập nhật vào cơ sở dữ liệu
        employeeService.save(existingEmployee);

        return "redirect:/Handshop/admin/AccEmployeesManager";
    }
}


