package com.springbootmvcwithentity.demo.Controller.ControllerCart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootmvcwithentity.demo.ClassSuport.CartCookie;
import com.springbootmvcwithentity.demo.dao.CustomerRepository;
import com.springbootmvcwithentity.demo.dao.WishRepository;
import com.springbootmvcwithentity.demo.dto.PhoneCartDTO;
import com.springbootmvcwithentity.demo.entity.*;
import com.springbootmvcwithentity.demo.service.PaymentMethod.PaymentMethodService;
import com.springbootmvcwithentity.demo.service.Phone.PhoneService;
import com.springbootmvcwithentity.demo.service.brand.BrandService;
import com.springbootmvcwithentity.demo.service.cart.WishService;
import com.springbootmvcwithentity.demo.service.categorie.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartController {

    private PhoneService phonesService;
    private BrandService brandService;
    private CategoryService categoryService;
    private PaymentMethodService paymentMethodService;
    private WishService wishService;
    private WishRepository wishRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public CartController(PhoneService phonesService, BrandService brandService, CategoryService categoryService, PaymentMethodService paymentMethodService, WishService wishService, WishRepository wishRepository, CustomerRepository customerRepository) {
        this.phonesService = phonesService;
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.paymentMethodService = paymentMethodService;
        this.wishService = wishService;
        this.wishRepository = wishRepository;
        this.customerRepository = customerRepository;
    }

        @GetMapping("/Handshop/cart")
        public String addToCart(HttpServletResponse response,
                                HttpServletRequest request,
                                Model model,
                                @RequestParam(name = "Id", required = false, defaultValue = "0") int id){
            List<Phones> phonesList = phonesService.findAll();

            Phones phone = phonesService.findById(id);
                if(phone.getPhoneId()!=0) {

                    // Lấy giỏ hàng từ cookie hoặc tạo mới nếu chưa tồn tại
                    CartCookie cartCookie = getCartFromCookie(request);

                    // Tạo một danh sách mới để lưu trữ sản phẩm sau khi xử lý
                    List<PhoneCartDTO> updatedCart = new ArrayList<>();

                    // Kiểm tra xem điện thoại đã có trong giỏ hàng chưa
                    boolean found = false;
                    for (PhoneCartDTO phoneCartDTO : cartCookie.getProducts()) {
                        if (phoneCartDTO.getModel().equals(phone.getModel())) {
                            // Nếu đã có, tăng số lượng lên 1
                            phoneCartDTO.setQuantityorder(phoneCartDTO.getQuantityorder() + 1);
                            found = true;
                        }
                        // Thêm sản phẩm vào danh sách mới
                        updatedCart.add(phoneCartDTO);
                    }

                    if (!found) {
                        Brands brand = brandService.findById(phone.getBrandId());
                        Categories category = categoryService.findById(phone.getCategoryId());

                        PhoneCartDTO phoneCartDTOAdd = new PhoneCartDTO(phone, brand, category, 1);
                        // Nếu chưa có, thêm điện thoại vào giỏ hàng với số lượng là 1
                        //PhoneCartDTO phoneCartDTO = new PhoneCartDTO(phone, 1); // Số lượng mặc định là 1
                        updatedCart.add(phoneCartDTOAdd);
                    }

                    // Cập nhật giỏ hàng với danh sách mới
                    cartCookie.setProducts(updatedCart);

                    // Lưu giỏ hàng vào cookie
                    saveCartToCookie(cartCookie, response);

                    // Đưa giỏ hàng vào model để hiển thị trên trang Thymeleaf
                    model.addAttribute("cartList", cartCookie.getProducts());
                    // Chuyển hướng đến trang cart
                    response.setHeader("Location", "/Handshop/cart");

                    // Thiết lập biến isRedirected thành true
                    boolean isRedirected = true;
                    model.addAttribute("isRedirected", isRedirected);
                } else {
                    // Lấy giỏ hàng từ cookie hoặc tạo mới nếu chưa tồn tại
                    CartCookie cartCookie = getCartFromCookie(request);
                    // Đưa giỏ hàng vào model để hiển thị trên trang Thymeleaf
                    model.addAttribute("cartList", cartCookie.getProducts());
                }
            model.addAttribute("phonesList", phonesList);
            return "phones/cart";
        }

    private CartCookie getCartFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        CartCookie cartCookie = new CartCookie();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cartCookie")) {
                    // Đọc thông tin giỏ hàng từ cookie và cập nhật vào đối tượng CartCookie
                    String cartCookieValue = cookie.getValue();
                    String decodedCartJson = new String(Base64Utils.decodeFromString(cartCookieValue));

                    try {
                        if (!decodedCartJson.equals("[]")) {
                        ObjectMapper objectMapper = new ObjectMapper();
                            cartCookie = objectMapper.readValue(decodedCartJson, CartCookie.class);}
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return cartCookie;
    }

    private void saveCartToCookie(CartCookie cartCookie, HttpServletResponse response) {
        // Chuyển đối tượng CartCookie thành chuỗi JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String cartJson;
        try {
            cartJson = objectMapper.writeValueAsString(cartCookie);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // Xử lý lỗi nếu có
            return; // Để không thực hiện việc lưu cookie nếu có lỗi chuyển đổi thành JSON
        }

        // Mã hóa chuỗi JSON thành Base64 để tránh bị lỗi bởi các kí tự đặc biệt
        String encodedCartJson = Base64Utils.encodeToString(cartJson.getBytes());

        // Tạo một cookie để lưu trữ thông tin giỏ hàng
        Cookie cookie = new Cookie("cartCookie", encodedCartJson);

        // Đặt thời gian sống của cookie (ví dụ: 7 ngày)
        cookie.setMaxAge(7 * 24 * 60 * 60);

        // Thêm cookie vào response
        response.addCookie(cookie);
    }

    @GetMapping("/Handshop/order-item-payment")
    public String order(Model model, @RequestParam(name = "user", required = false, defaultValue = "") String user){
        if(!user.equals("anonymousUser")){
           Customer customer = customerRepository.findByEmail(user);
           model.addAttribute("customer", customer);
        }
        List<PaymentMethod> paymentMethods = paymentMethodService.findAll();
        model.addAttribute("paymentMethods", paymentMethods);

        return "phones/order-item-payment";
    }

    @GetMapping("/Handshop/addWhis/{phoneID}/{email}")
    public String addWhis(@PathVariable int phoneID,@PathVariable String email){
        Customer customer = customerRepository.findByEmail(email);
        if(customer != null) {
            try {
                Wish wish = wishRepository.findByCustomerId(customer.getCustomerId());
                if (wish != null && phoneID == wish.getPhoneID()) {
                    wish.setQuantity(wish.getQuantity() + 1);
                    wishService.addToWish(wish);
                } else {
                    wishService.addToWish(new Wish(customer.getCustomerId(), phoneID, 1));
                }
            } catch (Exception e) {
                e.printStackTrace();
                wishService.addToWish(new Wish(customer.getCustomerId(), phoneID, 1));
            }
        } else {
            wishService.addToWish(new Wish(customer.getCustomerId(),phoneID,1));
        }
        return "index";
    }
}

