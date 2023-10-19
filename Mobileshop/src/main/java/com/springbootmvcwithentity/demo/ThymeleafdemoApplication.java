package com.springbootmvcwithentity.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
public class ThymeleafdemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThymeleafdemoApplication.class, args);
    }
}
