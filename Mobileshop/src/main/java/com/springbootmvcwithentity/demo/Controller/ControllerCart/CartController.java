package com.springbootmvcwithentity.demo.Controller.ControllerCart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootmvcwithentity.demo.ClassSuport.CartCookie;
import com.springbootmvcwithentity.demo.ClassSuport.ProductsIDandQuantity;
import com.springbootmvcwithentity.demo.dao.CustomerRepository;
import com.springbootmvcwithentity.demo.dao.WishRepository;
import com.springbootmvcwithentity.demo.dto.PhoneCartDTO;
import com.springbootmvcwithentity.demo.dto.PhoneDTO;
import com.springbootmvcwithentity.demo.entity.*;
import com.springbootmvcwithentity.demo.entity.extand.*;
import com.springbootmvcwithentity.demo.service.Color.ColorService;
import com.springbootmvcwithentity.demo.service.Model.ModelService;
import com.springbootmvcwithentity.demo.service.OperatingSystem.OperatingSystemService;
import com.springbootmvcwithentity.demo.service.PaymentMethod.PaymentMethodService;
import com.springbootmvcwithentity.demo.service.Phone.PhoneService;
import com.springbootmvcwithentity.demo.service.RAM.RAMService;
import com.springbootmvcwithentity.demo.service.StorageCapacity.StorageCapacityService;
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
import java.util.List;

@Controller
public class CartController {

    private PhoneService phonesService;
    private BrandService brandService;
    private CategoryService categoryService;
    private ModelService modelService;
    private OperatingSystemService operatingSystemService;
    private StorageCapacityService storageCapacityService;
    private RAMService ramService;
    private ColorService colorService;
    private PaymentMethodService paymentMethodService;
    private WishService wishService;
    private WishRepository wishRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public CartController(PhoneService phonesService, BrandService brandService, CategoryService categoryService, ModelService modelService, OperatingSystemService operatingSystemService, StorageCapacityService storageCapacityService, RAMService ramService, ColorService colorService, PaymentMethodService paymentMethodService, WishService wishService, WishRepository wishRepository, CustomerRepository customerRepository) {
        this.phonesService = phonesService;
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.modelService = modelService;
        this.operatingSystemService = operatingSystemService;
        this.storageCapacityService = storageCapacityService;
        this.ramService = ramService;
        this.colorService = colorService;
        this.paymentMethodService = paymentMethodService;
        this.wishService = wishService;
        this.wishRepository = wishRepository;
        this.customerRepository = customerRepository;
    }

    public List<PhoneDTO> Phones2PhoneDTOS(List<Phones> phones){
        List<PhoneDTO> phoneDTOS = new ArrayList<>();
        for (Phones phone : phones) {
            phoneDTOS.add(Phone2PhoneDTO(phone));
        }
        return phoneDTOS;
    }
    public PhoneDTO Phone2PhoneDTO(Phones phone){
        Brands brand = brandService.findById(phone.getBrandId());
        Categories category = categoryService.findById(phone.getCategoryId());
        Models model = modelService.findById(phone.getModelID());
        OperatingSystem operatingSystem = operatingSystemService.findById(phone.getOperatingSystemID());
        StorageCapacity storageCapacity = storageCapacityService.findById(phone.getStorageCapacityID());
        RAM ram = ramService.findById(phone.getRamID());
        Color color = colorService.findById(phone.getColorID());
        PhoneDTO phoneDTO = new PhoneDTO(phone, brand, category, model, operatingSystem, ram, storageCapacity, color);
        return phoneDTO;
    }

    public PhoneCartDTO Phone2PhoneDTOCart(Phones phone, int quantityorder){
        Brands brand = brandService.findById(phone.getBrandId());
        Categories category = categoryService.findById(phone.getCategoryId());
        Models model = modelService.findById(phone.getModelID());
        OperatingSystem operatingSystem = operatingSystemService.findById(phone.getOperatingSystemID());
        StorageCapacity storageCapacity = storageCapacityService.findById(phone.getStorageCapacityID());
        RAM ram = ramService.findById(phone.getRamID());
        Color color = colorService.findById(phone.getColorID());
        PhoneCartDTO phoneCartDTO = new PhoneCartDTO(phone, brand, category, model, operatingSystem, ram, storageCapacity, color, quantityorder);
        return phoneCartDTO;
    }

    private List<PhoneCartDTO> Phones2PhoneDTOCarts(List<Phones> phones){
        List<PhoneCartDTO> phoneCartDTOS = new ArrayList<>();
        phones.forEach(item -> phoneCartDTOS.add(Phone2PhoneDTOCart(item, 0)));
        return phoneCartDTOS;
    }
    private List<PhoneCartDTO> ListPhoneCartDTOFromListID(CartCookie cartCookie){
        List<Integer> updatedCartID = cartCookie.getProductsID();
        List<Phones> phones = new ArrayList<>();
        updatedCartID.forEach(itemID -> {
            phones.add(phonesService.findById(itemID));
        });
        List<PhoneCartDTO> ListPhoneCartDTOFromListProductID = Phones2PhoneDTOCarts(phones);
        return ListPhoneCartDTOFromListProductID;
    }
        @GetMapping("/Handshop/cart")
        public String addToCart(HttpServletResponse response,
                                HttpServletRequest request,
                                Model model,
                                @RequestParam(name = "Id", required = false, defaultValue = "0") int id){
            if(id!=0) {
                Phones phone = phonesService.findById(id);
                // Lấy giỏ hàng từ cookie hoặc tạo mới nếu chưa tồn tại
                CartCookie cartCookie = getCartFromCookie(request);
                List<PhoneCartDTO> ListPhoneCartDTOFromListProductID = ListPhoneCartDTOFromListID(cartCookie);
                for (int i = 0; i < ListPhoneCartDTOFromListProductID.size(); i++) {
                    ListPhoneCartDTOFromListProductID.get(i).setQuantityorder(
                            cartCookie.getListProductsIDandQuantity().get(i).getQuantity()
                    );
                }
                // Kiểm tra xem điện thoại đã có trong giỏ hàng chưa
                boolean found = false;
                for (int i = 0; i < ListPhoneCartDTOFromListProductID.size(); i++) {
                    if (ListPhoneCartDTOFromListProductID.get(i).getModelId() == phone.getModelID()){
                        ListPhoneCartDTOFromListProductID.get(i).setQuantityorder(
                                ListPhoneCartDTOFromListProductID.get(i).getQuantityorder() + 1
                        );
                        cartCookie.getListProductsIDandQuantity().get(i).setQuantity(ListPhoneCartDTOFromListProductID.get(i).getQuantityorder());
                        found = true;
                    }
                }
                if (!found) {
                    PhoneCartDTO phoneCartDTOAdd = Phone2PhoneDTOCart(phone,1);
                    // Nếu chưa có, thêm điện thoại vào giỏ hàng với số lượng là 1
                    ListPhoneCartDTOFromListProductID.add(phoneCartDTOAdd);
                    cartCookie.getProductsID().add(id);
                    cartCookie.getListProductsIDandQuantity().add(new ProductsIDandQuantity(id,1, Double.parseDouble(phone.getPrice())));
                }
                saveCartToCookie(cartCookie, response);
                model.addAttribute("cartListID", cartCookie.getListProductsIDandQuantity());
                model.addAttribute("ListPhoneCartDTOFromListProductID", ListPhoneCartDTOFromListProductID);
                // Chuyển hướng đến trang cart
                response.setHeader("Location", "/Handshop/cart");

                // Thiết lập biến isRedirected thành true
                boolean isRedirected = true;
                model.addAttribute("isRedirected", isRedirected);
            } else {
                RedirectToCart(request,model);
            }
        return "phones/cart";
        }
    private void RedirectToCart( HttpServletRequest request, Model model){
            // Lấy giỏ hàng từ cookie hoặc tạo mới nếu chưa tồn tại
            CartCookie cartCookie = getCartFromCookie(request);
            List<PhoneCartDTO> ListPhoneCartDTOFromListProductID = ListPhoneCartDTOFromListID(cartCookie);
        for (int i = 0; i < ListPhoneCartDTOFromListProductID.size(); i++) {
            ListPhoneCartDTOFromListProductID.get(i).setQuantityorder(cartCookie.getListProductsIDandQuantity().get(i).getQuantity());
        }
            // Đưa giỏ hàng vào model để hiển thị trên trang Thymeleaf
            model.addAttribute("cartListID", cartCookie.getListProductsIDandQuantity());
            model.addAttribute("ListPhoneCartDTOFromListProductID", ListPhoneCartDTOFromListProductID);
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
    public String order(Model model, HttpServletRequest request,
                        @RequestParam(name = "user", required = false, defaultValue = "") String user){
        if(!user.equals("anonymousUser")){
           Customer customer = customerRepository.findByEmail(user);
           model.addAttribute("customer", customer);
        }
        List<PaymentMethod> paymentMethods = paymentMethodService.findAll();
        model.addAttribute("paymentMethods", paymentMethods);
        RedirectToCart(request,model);
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

