package com.springbootmvcwithentity.demo.test;

import com.springbootmvcwithentity.demo.dto.PhoneDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class controllerAPIajax {

    @GetMapping("/my-page")
    public String myPage(Model model) {
        // Thực hiện logic ở đây
        return "/test/my-page"; // Trả về tên template Thymeleaf
    }

    @ResponseBody
    @RequestMapping(value = "/ajax-request", method = RequestMethod.POST)
    public String handleAjaxRequest(@RequestBody String requestData) {
        // Xử lý yêu cầu AJAX ở đây và trả về phản hồi (response) dưới dạng chuỗi
        System.out.println("Đã chạy qua đây");
        return "Response from server: " + requestData;
    }

    @ResponseBody
    @RequestMapping(value = "/bid/check", method = RequestMethod.GET)
    String bidCheckService(@RequestBody String requestData) {
        String ok = requestData;
        System.out.println(ok);
        return ok;
    }

/*    @GetMapping("/bid/check")
    String bidCheckService() {
        return "OK";
    }*/

}

/** /////////////////////////////// */

/*@Controller
//@RestController
public class controllerAPIajax {

    @GetMapping("/my-page")
    public String myPage(Model model) {
        // Thực hiện logic ở đây
        return "/test/my-page"; // Trả về tên template Thymeleaf
    }
*//*    @RequestMapping(value = "/ajax-request", method = RequestMethod.GET)
    public void handleAjaxRequest(@RequestBody String requestData) {
        String ok = requestData;
        System.out.println(ok);
        // Xử lý yêu cầu AJAX ở đây và trả về phản hồi (response) dưới dạng chuỗi
//        return "Response from server: " + requestData;
    }*//*
    @ResponseBody
    @RequestMapping(value = "/bid/check", method = RequestMethod.GET)
    void bidCheckService(@RequestBody String requestData) {
        String ok = requestData;
        System.out.println(ok);
//        return ok;
    }
    @ResponseBody
    @RequestMapping(value = "/ajax-request", method = RequestMethod.GET)
    void bidCheckServices(@RequestBody String requestData) {
        String ok = requestData+"kkk";
        System.out.println(ok);
//        return ok;
    }
}*/