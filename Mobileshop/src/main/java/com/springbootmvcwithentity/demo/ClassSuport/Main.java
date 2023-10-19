//package com.springbootmvcwithentity.demo.ClassSuport;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.springbootmvcwithentity.demo.entity.Phones;
//
//public class Main {
//    public static void main(String[] args) throws JsonProcessingException {
//        CartCookie cart = new CartCookie();
//        // Thêm sản phẩm vào giỏ hàng
//        Phones phone = new Phones(/* Thông tin sản phẩm */);
//        cart.addProduct(phone);
//
//        // Chuyển đổi giỏ hàng thành chuỗi JSON
//        ObjectMapper objectMapper = new ObjectMapper();
//        String cartJson = objectMapper.writeValueAsString(cart);
//
//        System.out.println(cartJson);
//    }
//}
//
