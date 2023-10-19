/*
package com.springbootmvcwithentity.demo.Controller.ControllerCart;

import com.springbootmvcwithentity.demo.ClassSuport.CartSession;
import com.springbootmvcwithentity.demo.dto.PhoneCartDTO;
import com.springbootmvcwithentity.demo.entity.Brands;
import com.springbootmvcwithentity.demo.entity.Categories;
import com.springbootmvcwithentity.demo.entity.Phones;
import com.springbootmvcwithentity.demo.entity.Price;
import com.springbootmvcwithentity.demo.service.Phone.PhoneService;
import com.springbootmvcwithentity.demo.service.brand.BrandService;
import com.springbootmvcwithentity.demo.service.categorie.CategoryService;
import com.springbootmvcwithentity.demo.service.price.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class CartControllerSesion {
    private PhoneService phonesService;
    private BrandService brandService;
    private CategoryService categoryService;
    private PriceService priceService;

    @Autowired
    public CartControllerSesion(PhoneService phoneService, BrandService brandService, CategoryService categoryService, PriceService priceService) {
        this.phonesService = phoneService;
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.priceService = priceService;
    }

    // Các thuộc tính và constructor của controller

    @GetMapping("/addToCart/{phoneId}")
    public String addToCart(@PathVariable int phoneId,
                            HttpSession session,
                            HttpServletResponse response,
                            Model model) {
        Phones phone = phonesService.findById(phoneId);

        // Lấy giỏ hàng từ session hoặc tạo mới nếu chưa tồn tại
        CartSession cartSession = (CartSession) session.getAttribute("cartSession");
        if (cartSession == null) {
            cartSession = new CartSession();
            session.setAttribute("cartSession", cartSession);
        }

        // Tạo một danh sách mới để lưu trữ sản phẩm sau khi xử lý
        List<PhoneCartDTO> updatedCart = new ArrayList<>();

        // Kiểm tra xem điện thoại đã có trong giỏ hàng chưa
        boolean found = false;
        for (PhoneCartDTO phoneCartDTO : cartSession.getProducts()) {
            if (phoneCartDTO.getModel().equals(phone.getModel())) {
                // Nếu đã có, tăng số lượng lên 1
                phoneCartDTO.setQuantity(phoneCartDTO.getQuantity() + 1);
                found = true;
            }
            // Thêm sản phẩm vào danh sách mới
            updatedCart.add(phoneCartDTO);
        }

        if (!found) {
            Brands brand = brandService.findById(phone.getBrandId());
            Categories category = categoryService.findById(phone.getCategoryId());
            Price price = priceService.findById(phone.getPrice());

            PhoneCartDTO phoneCartDTOAdd = new PhoneCartDTO(phone, brand, category, price, 1);
            // Nếu chưa có, thêm điện thoại vào giỏ hàng với số lượng là 1
            updatedCart.add(phoneCartDTOAdd);
        }

        // Cập nhật giỏ hàng trong session với danh sách mới
        cartSession.setProducts(updatedCart);

        // Đưa giỏ hàng vào model để hiển thị trên trang Thymeleaf
        model.addAttribute("cartList", cartSession.getProducts());
        model.addAttribute("cartSession", cartSession);
        // Chuyển hướng đến trang cart
        response.setHeader("Location", "/Handshop/cart");

        // Thiết lập biến isRedirected thành true
        boolean isRedirected = true;
        model.addAttribute("isRedirected", isRedirected);
        return "phones/cart";
    }

*/
/*    @GetMapping("/Handshop/cart")
    public String viewCart(HttpSession session, Model model) {
        // Lấy giỏ hàng từ session
        CartSession cartSession = (CartSession) session.getAttribute("cartSession");
        if (cartSession == null) {
            cartSession = new CartSession();
            session.setAttribute("cartSession", cartSession);
        }

        // Đưa giỏ hàng vào model để hiển thị trên trang Thymeleaf
        model.addAttribute("cartList", cartSession.getProducts());
        return "phones/cart";
    }*//*

    @GetMapping("/Handshop/cart")
    public String addToCart(*/
/*@PathVariable int phoneId,*//*

            HttpSession session,
            HttpServletResponse response,
            Model model,
            @RequestParam(name = "Id", required = false, defaultValue = "0") int id) {
        if(id != 0) {
            Phones phone = phonesService.findById(id);

            // Lấy giỏ hàng từ session hoặc tạo mới nếu chưa tồn tại
            CartSession cartSession = (CartSession) session.getAttribute("cartSession");
            if (cartSession == null) {
                cartSession = new CartSession();
                session.setAttribute("cartSession", cartSession);
            }

            // Tạo một danh sách mới để lưu trữ sản phẩm sau khi xử lý
            List<PhoneCartDTO> updatedCart = new ArrayList<>();

            // Kiểm tra xem điện thoại đã có trong giỏ hàng chưa
            boolean found = false;
            for (PhoneCartDTO phoneCartDTO : cartSession.getProducts()) {
                if (phoneCartDTO.getModel().equals(phone.getModel())) {
                    // Nếu đã có, tăng số lượng lên 1
                    phoneCartDTO.setQuantity(phoneCartDTO.getQuantity() + 1);
                    found = true;
                }
                // Thêm sản phẩm vào danh sách mới
                updatedCart.add(phoneCartDTO);
            }

            if (!found) {
                Brands brand = brandService.findById(phone.getBrandId());
                Categories category = categoryService.findById(phone.getCategoryId());
                Price price = priceService.findById(phone.getPrice());

                PhoneCartDTO phoneCartDTOAdd = new PhoneCartDTO(phone, brand, category, price, 1);
                // Nếu chưa có, thêm điện thoại vào giỏ hàng với số lượng là 1
                updatedCart.add(phoneCartDTOAdd);
            }

            // Cập nhật giỏ hàng trong session với danh sách mới
            cartSession.setProducts(updatedCart);

            // Đưa giỏ hàng vào model để hiển thị trên trang Thymeleaf
            model.addAttribute("cartList", cartSession.getProducts());
            model.addAttribute("cartSession", cartSession);
            // Chuyển hướng đến trang cart
            response.setHeader("Location", "/Handshop/cart");

            // Thiết lập biến isRedirected thành true
            boolean isRedirected = true;
            model.addAttribute("isRedirected", isRedirected);
        } else {
            // Lấy giỏ hàng từ session
            CartSession cartSession = (CartSession) session.getAttribute("cartSession");
            if (cartSession == null) {
                cartSession = new CartSession();
                session.setAttribute("cartSession", cartSession);
            }

            // Đưa giỏ hàng vào model để hiển thị trên trang Thymeleaf
            model.addAttribute("cartList", cartSession.getProducts());
        }
        return "phones/cart";
    }
}
*/
