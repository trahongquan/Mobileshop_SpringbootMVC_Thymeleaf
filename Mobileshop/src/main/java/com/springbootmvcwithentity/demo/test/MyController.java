package com.springbootmvcwithentity.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class MyController {

    private final MyService myService;

    @Autowired
    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/selectData")
    public String selectData(Model model) {
        String s = "hạa";

        return s;
//        List<Map<String, Object>> result = myService.executeSelectQuery();
//        model.addAttribute("data", result);
//        return "index"; // Trả về trang hiển thị kết quả
    }
}
