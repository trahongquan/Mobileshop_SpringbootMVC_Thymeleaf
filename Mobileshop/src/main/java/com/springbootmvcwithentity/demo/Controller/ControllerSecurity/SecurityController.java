package com.springbootmvcwithentity.demo.Controller.ControllerSecurity;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class SecurityController {
    @GetMapping("/login")
    public String Login() {
        return "security/login2";
    }
    @GetMapping("/access-denied")
    public String Accessdenied() {
        return "security/access-denied";
    }
}
