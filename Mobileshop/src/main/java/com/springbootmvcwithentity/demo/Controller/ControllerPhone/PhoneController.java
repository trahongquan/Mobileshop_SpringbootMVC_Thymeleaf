package com.springbootmvcwithentity.demo.Controller.ControllerPhone;

import com.springbootmvcwithentity.demo.ClassSuport.SeriMissing;
import com.springbootmvcwithentity.demo.dao.*;
import com.springbootmvcwithentity.demo.dto.OrderDTO;
import com.springbootmvcwithentity.demo.dto.OrderitemDTO;
import com.springbootmvcwithentity.demo.dto.PhoneDTO;
import com.springbootmvcwithentity.demo.dto.PrdRevDTO;
import com.springbootmvcwithentity.demo.entity.*;
import com.springbootmvcwithentity.demo.service.Customer.CustomerService;
import com.springbootmvcwithentity.demo.service.Phone.PhoneService;
import com.springbootmvcwithentity.demo.service.PrdRevService.brand.PrdRevService;
import com.springbootmvcwithentity.demo.service.brand.BrandService;
import com.springbootmvcwithentity.demo.service.categorie.CategorieService;
import com.springbootmvcwithentity.demo.service.categorie.CategoryService;
import com.springbootmvcwithentity.demo.ClassSuport.StringToList;
import com.springbootmvcwithentity.demo.service.order.orderService;
import com.springbootmvcwithentity.demo.service.orderitem.orderitemsService;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Controller
@SpringBootApplication
@RequestMapping("/Handshop")
public class PhoneController {

    private PhoneRepository phoneRepository;
    private PhoneService phoneService;
    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private BrandService brandService;
    private CategoryService categoryService;
    private orderitemsService orderitemsservice;
    private orderService orderservice;
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private EmployeeRepository employeeRepository;
    private PrdRevService prdRevService;
    private PrdRevRepository prdRevRepository;

    @Autowired
    public PhoneController(PhoneRepository phoneRepository, PhoneService phoneService, CustomerService customerService, CustomerRepository customerRepository, BrandService brandService, CategoryService categoryService, orderitemsService orderitemsservice, orderService orderservice, OrderRepository orderRepository, OrderItemRepository orderItemRepository, EmployeeRepository employeeRepository, PrdRevService prdRevService, PrdRevRepository prdRevRepository) {
        this.phoneRepository = phoneRepository;
        this.phoneService = phoneService;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.orderitemsservice = orderitemsservice;
        this.orderservice = orderservice;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.employeeRepository = employeeRepository;
        this.prdRevService = prdRevService;
        this.prdRevRepository = prdRevRepository;
    }

    /******************************************************************************************************/
                                            /** Khu vực Chung*/
    /******************************************************************************************************/

    public List<PhoneDTO> Phone2PhoneDTOS(List<Phones> phones){
        List<PhoneDTO> phoneDTOS = new ArrayList<>();

        for (Phones phone : phones) {
            Brands brand = brandService.findById(phone.getBrandId());
            Categories category = categoryService.findById(phone.getCategoryId());
            PhoneDTO phoneDTO = new PhoneDTO(phone, brand, category);
            phoneDTOS.add(phoneDTO);
        }
        return phoneDTOS;
    }
    public PhoneDTO Phone2PhoneDTO(Phones phone){

        Brands brand = brandService.findById(phone.getBrandId());
        Categories category = categoryService.findById(phone.getCategoryId());
        PhoneDTO phoneDTO = new PhoneDTO(phone, brand, category);

        return phoneDTO;
    }

    @GetMapping({"/", ""})
    public String redirectToHandshopList() {
        return "redirect:/Handshop/list";
    }


    @GetMapping({"/list"})
    public String getPhones(Model model) {

        List<Phones> phones = phoneService.findAll().stream().filter(item->item.getQuantity()!=0).collect(Collectors.toList());
        List<PhoneDTO> phoneDTOS = Phone2PhoneDTOS(phones);
        model.addAttribute("phoneDTOS", phoneDTOS); /** cách xử lý ở backEnd*/
        return "index";
    }

    @GetMapping("/ViewDetailPhone/{id}")
    public String ViewDetailPhone(@PathVariable int id, Model model) {
        Phones phone = phoneService.findById(id);
        Brands brand = brandService.findById(phone.getBrandId());
        Categories category = categoryService.findById(phone.getCategoryId());
        PhoneDTO phoneDTO = new PhoneDTO(phone, brand, category);

        List<productreview> productreviews = prdRevRepository.findAllByPhoneID(id);

        List<PrdRevDTO> prdRevDTOS = new LinkedList<>();
        productreviews.forEach(productreview -> {
            Customer customer = customerService.findById(productreview.getCustomerID());
            PrdRevDTO prdRevDTO = new PrdRevDTO(productreview, customer);
            prdRevDTOS.add(prdRevDTO);
        });
        List<Phones> phones = phoneRepository.findAllByPhoneNameContainingOrSeriContaining(phone.getPhoneName().split(" ")[0],phone.getPhoneName().split(" ")[0]);
        List<PhoneDTO> phoneDTOS = Phone2PhoneDTOS(phones);
        if (phoneDTO != null) {
            model.addAttribute("prdRevDTOS", prdRevDTOS);
            model.addAttribute("phoneDTO", phoneDTO);
            model.addAttribute("phoneDTOS", phoneDTOS);
            return "phones/DetailPhone";
        } else {
            throw new RuntimeException("Không tìm thấy điện thoại với ID=" + id);
        }

    }

    @PostMapping("/ViewDetailPhone/comment")
    public String Comment(@RequestParam("phoneID") int phoneid,
                          @RequestParam("email") String email,
                          @RequestParam("rating") int rating,
                          @RequestParam("comment") String comment,
                          @RequestParam("reviewDate") String reviewDate){
        Customer customer = customerRepository.findByEmail(email);
        productreview productreview = new productreview(phoneid, customer.getCustomerId(),rating,comment,reviewDate);
        prdRevService.save(productreview);
    return "redirect:/Handshop/ViewDetailPhone/" + phoneid;
    }


    @GetMapping({"/iphone"})
    public String getListiPhones(Model model) {

        List<Phones> phones = phoneService.findAll().stream().filter(item -> item.getQuantity()!=0 && item.getOperatingSystem().equals("IOS")).collect(Collectors.toList());
        List<PhoneDTO> phoneDTOS = Phone2PhoneDTOS(phones);
        model.addAttribute("phoneDTOS", phoneDTOS); /** cách xử lý ở backEnd*/
        return "index";
    }

    @GetMapping({"/Android"})
    public String getListAndroids(Model model) {
        List<Phones> phones = phoneService.findAll().stream().filter(item -> item.getQuantity()!=0 && item.getOperatingSystem().equals("Android")).collect(Collectors.toList());
        List<PhoneDTO> phoneDTOS = Phone2PhoneDTOS(phones);
        model.addAttribute("phoneDTOS", phoneDTOS); /** cách xử lý ở backEnd*/
        return "index";
    }

    /********************************************************/
    /************************* SEARCH ***********************/
    /********************************************************/

    @PostMapping("/list/search")
    public String Search(@RequestParam("inputdatasearch") String inputdatasearch, Model model) {
        List<Phones> phones = phoneRepository.findAllByPhoneNameContainingOrSeriContaining(inputdatasearch,inputdatasearch);
        List<PhoneDTO> phoneDTOS = Phone2PhoneDTOS(phones);
        model.addAttribute("phoneDTOS", phoneDTOS); /** cách xử lý ở backEnd*/
        return "index";
    }

    /******************************************************************************************************/
                                                    /** Khu vực của admin*/
    /******************************************************************************************************/

    @PostMapping("/admin/searchAdmin")
    public String searchAdmin(@RequestParam("inputdatasearch") String inputdatasearch, Model model) {
        List<Phones> phones = phoneRepository.findAllByPhoneNameContainingOrSeriContaining(inputdatasearch,inputdatasearch);
        List<PhoneDTO> phoneDTOS = Phone2PhoneDTOS(phones);
        model.addAttribute("phoneDTOS", phoneDTOS); /** cách xử lý ở backEnd*/
        return "admin/list-phones";
    }
    @PostMapping("/admin/searchAdminSold")
    public String searchAdminSold(Model model,
                                  @RequestParam("inputdatasearch") String inputdatasearch,
                                  @RequestParam("soldphones") boolean soldphones,
                                  @RequestParam("soldPhonesWait") boolean soldPhonesWait) {
        List<Phones> phones = phoneRepository.findAllByPhoneNameContainingOrSeriContaining(inputdatasearch,inputdatasearch);
        phones.forEach(phones1 -> System.out.println(phones1.toString()));
        List<OrderItem> orderItems = orderitemsservice.findAll();
        List<OrderitemDTO> orderitemDTOSFirst = orderItems2orderitemDTOS(orderItems);
        List<OrderitemDTO> orderitemDTOSFilter = new LinkedList<>();
        if(soldphones == true) {
            orderitemDTOSFilter = filterDateProcess(orderitemDTOSFirst,"0000-00-00 00:00:00", "", false);
        }
        if(soldPhonesWait == true) {
            orderitemDTOSFilter = filterDateProcess(orderitemDTOSFirst,"0000-00-00 00:00:00", "", true);
        }
                                    /**********************************/
                                    /** Chưa tìm kiếm bằng seri được  */
                                    /**********************************/
        List<OrderitemDTO> orderitemDTOS = orderitemDTOSFilter
                .stream()
                .filter(
                    orderitemDTO ->
                        phones.stream().anyMatch(item ->{

                            if(inputdatasearch != null && (orderitemDTO.getSeris() != null && orderitemDTO.getSeris().toString().contains(inputdatasearch))) {
                                return orderitemDTO.getSeris().toString().contains(inputdatasearch) || orderitemDTO.getPhoneDTO().getPhoneName().equals(item.getPhoneName());
                            } else {
                                return orderitemDTO.getPhoneDTO().getPhoneName().equals(item.getPhoneName());
                            }
                        })

                ).collect(Collectors.toList());
        List<String> orderdate = GetDateProcess(orderitemDTOS);

        model.addAttribute("orderitemDTOS", orderitemDTOS); /** cách xử lý ở backEnd*/
        model.addAttribute("orderdate", orderdate); /** cách xử lý ở backEnd*/
        model.addAttribute("soldphones", soldphones); /** cách xử lý ở backEnd*/
        model.addAttribute("soldPhonesWait", soldPhonesWait); /** cách xử lý ở backEnd*/

        return "admin/list-sold-phones";
    }

    @GetMapping({"/admin", "/admin/"})
    public String redirectToAdminHandshopListAdmin(Model model) {
        List<Phones> phones = phoneService.findAll();
        List<PhoneDTO> phoneDTOS = Phone2PhoneDTOS(phones);
        model.addAttribute("phoneDTOS", phoneDTOS); /** cách xử lý ở backEnd*/
        return "admin/list-phones";
    }

    private List<OrderitemDTO> orderItems2orderitemDTOS(List<OrderItem> orderItems){
        List<OrderitemDTO> orderitemDTOS = new LinkedList<>();
        orderItems.forEach(item ->{
            Phones phone = phoneService.findById(item.getPhoneID());
            orderitemDTOS.add(new OrderitemDTO(item,Phone2PhoneDTO(phone)));
        });
        return orderitemDTOS;
    }
    private List<String> GetDateProcess(List<OrderitemDTO> orderitemDTOS){
        List<String> orderdate =new LinkedList<>();
        orderitemDTOS.forEach(orderitemDTO -> {
            Order order = orderservice.findById(orderitemDTO.getOrderID());
            orderdate.add(order.getDateProcessed());
        });
        return orderdate;
    }
    private List<OrderitemDTO> filterDateProcess(List<OrderitemDTO> orderitemDTOS, String stringFilter1, String stringFilter2, boolean invertCondition) {
        return orderitemDTOS.stream()
                .filter(orderitemDTO -> {
                    String dateProcessed = orderservice.findById(orderitemDTO.getOrderID()).getDateProcessed();
                    if (invertCondition) {
                        // Nếu invertCondition là true, đảo ngược điều kiện
                        return (stringFilter1.equals(dateProcessed) || stringFilter2.equals(dateProcessed));
                    } else {
                        // Ngược lại, sử dụng điều kiện như trước
                        return !(stringFilter1.equals(dateProcessed) || stringFilter2.equals(dateProcessed));
                    }
                })
                .collect(Collectors.toList());
    }


    @GetMapping({"/admin/soldphones"})
    public String redirectToAdminHandshopListSoldPhones(Model model) {
        List<OrderItem> orderItems = orderitemsservice.findAll();
        List<OrderitemDTO> orderitemDTOS = orderItems2orderitemDTOS(orderItems);
        List<OrderitemDTO> orderitemDTOFilter = filterDateProcess(orderitemDTOS,"0000-00-00 00:00:00", "", false);
        List<String> orderdate = GetDateProcess(orderitemDTOFilter);
        model.addAttribute("orderitemDTOS", orderitemDTOFilter); /** cách xử lý ở backEnd*/
        model.addAttribute("orderdate", orderdate); /** cách xử lý ở backEnd*/
        model.addAttribute("soldphones", true); /** cách xử lý ở backEnd*/
        model.addAttribute("soldPhonesWait", false); /** cách xử lý ở backEnd*/
        return "admin/list-sold-phones";
    }
    @GetMapping({"/admin/soldPhonesWait"})
    public String redirectToAdminHandshopListSoldPhonesWait(Model model) {
        List<OrderItem> orderItems = orderitemsservice.findAll();
        List<OrderitemDTO> orderitemDTOS = orderItems2orderitemDTOS(orderItems);
        List<OrderitemDTO> orderitemDTOFilter = filterDateProcess(orderitemDTOS,"0000-00-00 00:00:00", "", true);
        List<String> orderdate = GetDateProcess(orderitemDTOFilter);
        boolean soldphones = false;
        boolean soldPhonesWait = true;
        model.addAttribute("orderitemDTOS", orderitemDTOFilter); /** cách xử lý ở backEnd*/
        model.addAttribute("orderdate", orderdate); /** cách xử lý ở backEnd*/
        model.addAttribute("soldphones", false); /** cách xử lý ở backEnd*/
        model.addAttribute("soldPhonesWait", true); /** cách xử lý ở backEnd*/
        return "admin/list-sold-phones";
    }


    @GetMapping("/admin/add")
    public String showPhoneForm(Model model) {
        List<Brands> brands = brandService.findAll();
        List<Categories> categories = categoryService.findAll();
        Phones phone = new Phones();
        model.addAttribute("phone", phone);
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        return "admin/form-phone";
    }

    @PostMapping("/admin/add")
    public String createPhone(@ModelAttribute("phone") Phones phone,
                              @RequestParam("categoryID") int categoryID,
                              @RequestParam("brandID") int brandID,
                              @RequestParam("file") MultipartFile file) {
        phone.setBrandId(brandID);
        phone.setCategoryId(categoryID);
        phone.setQuantity(new StringToList().StringToList(phone.getSeri()).size());
        if (!file.isEmpty()) {
            try {
                // Lưu ảnh vào thư mục static/images
                String imageName = file.getOriginalFilename();
                String ImagePath = "Images/" + imageName;
                file.transferTo(new File(ImagePath));

                // Cập nhật trường ImageName trong Entity Phones
                phone.setImageName(imageName);
                phoneService.save(phone);
                return "redirect:/Handshop/admin";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/Handshop/admin?error";
    }

    //////////////////////

    @GetMapping("/admin/showFormForUpdate/{id}")
    public String showEditPhoneForm(@PathVariable int id, Model model) {
        List<Brands> brands = brandService.findAll();
        List<Categories> categories = categoryService.findAll();
        Phones phone = phoneService.findById(id);
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        model.addAttribute("phone", phone);
        if (phone != null) {
            model.addAttribute("phone", phone);
            return "admin/form-edit-phone";
        } else {
            throw new RuntimeException("Không tìm thấy điện thoại với ID=" + id);
        }
    }

    @PostMapping("/admin/showFormForUpdate/{id}")
    public String editPhone(@PathVariable int id
            , @ModelAttribute("phone") Phones updatedPhone
            , @RequestParam("file") MultipartFile file
            , Model model
    ) {
        // Tìm đối tượng Phone hiện có trong cơ sở dữ liệu
        Phones existingPhone = phoneService.findById(id);
        // Cập nhật thông tin từ updatedPhone vào existingPhone
        existingPhone.setBrandId(updatedPhone.getBrandId());
        existingPhone.setCategoryId(updatedPhone.getCategoryId());
        existingPhone.setPhoneName(updatedPhone.getPhoneName());
        existingPhone.setModel(updatedPhone.getModel());
        existingPhone.setReleaseYear(updatedPhone.getReleaseYear());
        existingPhone.setScreenSize(updatedPhone.getScreenSize());
        existingPhone.setStorageCapacity(updatedPhone.getStorageCapacity());
        existingPhone.setRam(updatedPhone.getRam());
        existingPhone.setOperatingSystem(updatedPhone.getOperatingSystem());
        existingPhone.setColor(updatedPhone.getColor());
        existingPhone.setSeri(updatedPhone.getSeri());
        existingPhone.setQuantity(new StringToList().StringToList(updatedPhone.getSeri()).size());

        if (!file.isEmpty()) {
            try {
                // Lưu ảnh vào thư mục static/images
                String imageName = file.getOriginalFilename();
                String ImagePath = "Images/" + imageName;

                file.transferTo(new File(ImagePath));

                // Cập nhật trường ImageName trong Entity Phones
                existingPhone.setImageName(imageName);
                phoneService.save(existingPhone);
                String Mess = "Cập nhật điện thoại thành công";
                model.addAttribute("Mess", Mess);
                return "redirect:/Handshop/admin";
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/Handshop/showFormForUpdate?eror";
            }
        }
        phoneService.save(existingPhone);
        return "redirect:/Handshop/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String showDeletePhoneForm(@PathVariable String id, Model model) {
        int idt = Integer.parseInt(id);
        Phones phone = phoneService.findById(idt);
        if (phone != null) {
            model.addAttribute("phone", phone);
            return "admin/delete";
        } else {
            throw new RuntimeException("Không tìm thấy điện thoại với ID=" + id);
        }
    }

    @PostMapping("/admin/delete/{id}")
    public String deletePhone(@PathVariable int id) {
        phoneService.deleteById(id);
        return "redirect:/Handshop/admin";
    }

    /******************************************************************************************************/
    /** Brand & Category - Admin */
    /******************************************************************************************************/

    @GetMapping("/admin/brandandcategory")
    public String showbrandandcategoryForm(@ModelAttribute("errorbrand") String errorbrand,
                                           @ModelAttribute("successbrand") String successbrand,
                                           @ModelAttribute("errorcategory") String errorcategory,
                                           @ModelAttribute("successcategory") String successcategory,
                                           @ModelAttribute("errorbrandadd") String errorbrandadd,
                                           @ModelAttribute("successbrandadd") String successbrandadd,
                                           @ModelAttribute("errorcategoryadd") String errorcategoryadd,
                                           @ModelAttribute("successcategoryadd") String successcategoryadd,
                                           Model model) {
        List<Brands> brands = brandService.findAll();
        List<Categories> categories = categoryService.findAll();
        Phones phone = new Phones();
        model.addAttribute("phone", phone);
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        model.addAttribute("errorbrand", errorbrand);
        model.addAttribute("successbrand", successbrand);
        model.addAttribute("errorcategory", errorcategory);
        model.addAttribute("successcategory", successcategory);
        model.addAttribute("errorbrandadd", errorbrandadd);
        model.addAttribute("successbrandadd", successbrandadd);
        model.addAttribute("errorcategoryadd", errorcategoryadd);
        model.addAttribute("successcategoryadd", successcategoryadd);
        return "admin/add-Brand-Category";
    }


    @PostMapping("/admin/brand/add")
    public String addBrand(@RequestParam("addBrand") String brandName, RedirectAttributes redirectAttributes){
        try {
            Brands brand = new Brands(brandName);
            brandService.save(brand);
            redirectAttributes.addFlashAttribute("errorbrandadd", "");
            redirectAttributes.addFlashAttribute("successbrandadd", "Đã thêm Brand thành công");
        } catch (Exception e) {
            // Xử lý các lỗi khác nếu có
            redirectAttributes.addFlashAttribute("errorbrandadd", "Lỗi thêm Brand");
            redirectAttributes.addFlashAttribute("successbrandadd", "");
            e.printStackTrace(); // In lỗi ra console hoặc log
        }
        return "redirect:/Handshop/admin/brandandcategory";
    }
    @PostMapping("/admin/category/add")
    public String addCategory(@RequestParam("addCategory") String categoryName, RedirectAttributes redirectAttributes){
        try {
            Categories category = new Categories(categoryName);
            categoryService.save(category);
            redirectAttributes.addFlashAttribute("errorcategoryadd", "");
            redirectAttributes.addFlashAttribute("successcategoryadd", "Đã thêm Category thành công");
        } catch (Exception e) {
            // Xử lý các lỗi khác nếu có
            redirectAttributes.addFlashAttribute("errorcategoryadd", "Lỗi thêm Category");
            redirectAttributes.addFlashAttribute("successcategoryadd", "");
            e.printStackTrace(); // In lỗi ra console hoặc log
        }
        return "redirect:/Handshop/admin/brandandcategory";
    }
    @PostMapping("/admin/brand/del")
    public String delBrand(@RequestParam("brandID") int brandID, RedirectAttributes redirectAttributes) {
        try {
            // Kiểm tra xem có dữ liệu nào đang sử dụng brandID không
            if (brandService.isBrandInUse(brandID)) {
                // Nếu có, chuyển hướng với thông báo lỗi
                redirectAttributes.addFlashAttribute("errorbrand", "Không thể xóa Brand đã được sử dụng.");
                redirectAttributes.addFlashAttribute("successbrand", "");
            } else {
                // Nếu không có, thực hiện xóa và chuyển hướng
                brandService.deleteById(brandID);
                redirectAttributes.addFlashAttribute("errorbrand", "");
                redirectAttributes.addFlashAttribute("successbrand", "Brand đã được xóa thành công.");
            }
        } catch (Exception e) {
            // Xử lý các lỗi khác nếu có
            redirectAttributes.addFlashAttribute("errorbrand", "Đã xảy ra lỗi trong quá trình xóa Brand.");
            e.printStackTrace(); // In lỗi ra console hoặc log
        }

        return "redirect:/Handshop/admin/brandandcategory";
    }

    @PostMapping("/admin/category/del")
    public String delCategory(@RequestParam("categoryID") int categoryID, RedirectAttributes redirectAttributes) {
        try {
            // Kiểm tra xem có dữ liệu nào đang sử dụng brandID không
            if (categoryService.isCategoryInUse(categoryID)) {
                // Nếu có, chuyển hướng với thông báo lỗi
                redirectAttributes.addFlashAttribute("errorcategory", "Không thể xóa Category đã được sử dụng.");
                redirectAttributes.addFlashAttribute("successcategory", "");
            } else {
                // Nếu không có, thực hiện xóa và chuyển hướng
                categoryService.deleteById(categoryID);
                redirectAttributes.addFlashAttribute("errorcategory", "");
                redirectAttributes.addFlashAttribute("successcategory", "Category đã được xóa thành công.");
            }
        } catch (Exception e) {
            // Xử lý các lỗi khác nếu có
            redirectAttributes.addFlashAttribute("errorcategory", "Đã xảy ra lỗi trong quá trình xóa Category.");
            e.printStackTrace(); // In lỗi ra console hoặc log
        }
        return "redirect:/Handshop/admin/brandandcategory";
    }

    /******************************************************************************************************/
                        /** OrderRequest & Payment - Admin */
    /******************************************************************************************************/


    @GetMapping("/admin/OrderRequest")
    public String showOrderRequest(Model model) {
        List<Order> orders = orderservice.findAll();
        List<OrderItem> orderItems = orderitemsservice.findAll();
        List<OrderitemDTO> orderitemDTOs = new LinkedList<>(); /** List OrderItemDTO nằm trong OrderDTO */
        List<OrderDTO> orderDTOs = new LinkedList<>(); /** List OrderItemDTO nằm trong OrderDTO */

        /** Tạo một List orderItemDTOs: danh sách các sản phẩm được mua*/
        orderItems.forEach(orderItem -> {
            List<String> serisOrder = !orderItem.getSeri().equals("[]") ? new StringToList().StringToList(orderItem.getSeri()) : new ArrayList<>();/** Chuyển chuỗi phoneID thành List PhoneID*/
            OrderitemDTO orderitemDTO = new OrderitemDTO(orderItem, serisOrder);
            orderitemDTOs.add(orderitemDTO);
        });

        /** Tạo một List orderDTOs: danh sách các hóa đơn*/
        orders.forEach(order -> {
            List<String> serisOrder = new LinkedList<>(); /** List phone nằm trong orderItem*/
            orderitemDTOs.forEach(item -> item.getSeris().forEach(seriItem -> serisOrder.add(seriItem)));
//            String employeeID = !order.getEmployeeID().equals("") ? order.getEmployeeID() : "";
            Customer customer = customerService.findById(order.getCustomerId());

            List<OrderitemDTO> orderitemDTOs2 = new LinkedList<>();
            List<Phones> phonesOrder = new LinkedList<>();
            orderitemDTOs.forEach(item -> {
                if (item.getOrderID() == order.getOrderID()) {
                    phonesOrder.add(phoneService.findById(item.getPhoneID()));
                    orderitemDTOs2.add(item);
                }
            });
            OrderDTO orderDTO = new OrderDTO(order, orderitemDTOs2, serisOrder, phonesOrder, customer);
            orderDTOs.add(orderDTO);
        });

        LinkedList<OrderDTO> orderDTOsApprove = new LinkedList<>(orderDTOs);
        List<OrderDTO> orderDTOsApprovefilter = orderDTOsApprove.stream().filter(item -> item.getDateProcessed().equals("0000-00-00 00:00:00")).collect(Collectors.toList());
        LinkedList<OrderDTO> orderDTOsNotApprove = new LinkedList<>(orderDTOs);
        List<OrderDTO> orderDTOsNotApprovefilter = orderDTOsNotApprove.stream().filter(item -> !item.getDateProcessed().equals("0000-00-00 00:00:00")).collect(Collectors.toList());


        model.addAttribute("orderDTOsApprovefilter", orderDTOsApprovefilter);
        model.addAttribute("orderDTOsNotApprovefilter", orderDTOsNotApprovefilter);
        model.addAttribute("orderDTOs", orderDTOs);
        return "/admin/order-request";
    }


    @PostMapping("/admin/approveOrder")
    public String approveOrder(@RequestParam("OrderID") String OrderID,
                               @RequestParam("User") String User,
                               @RequestParam("dateProcessed") String dateProcessed) {
        int orderID = Integer.parseInt(OrderID);
        Order order = orderservice.findById(orderID);
        if (User.equals("admin")) {
            order.setEmployeeID("9");
        } else {
            Employees employee = employeeRepository.findByEmail(User);
            order.setEmployeeID(employee.getEmployeeID() + "");
        }
        order.setDateProcessed(dateProcessed);
        orderservice.save(order);
        return "redirect:/Handshop/admin/OrderRequest";
    }

    @PostMapping("/admin/unapproveOrder")
    public String unapproveOrder(@RequestParam("OrderID") String OrderID,
                                 @RequestParam("dateProcessed") String dateProcessed) {
        int orderID = Integer.parseInt(OrderID);
        Order order = orderservice.findById(orderID);
        order.setEmployeeID("9");
        order.setDateProcessed(dateProcessed);
        orderservice.save(order);
        return "redirect:/Handshop/admin/OrderRequest";
    }

    @PostMapping("/admin/updateOrder")
    public String updateOrder(@RequestParam("OrderID") int orderID, Model model,
                              @RequestParam("dateProcessed") String dateProcessed) {

        List<SeriMissing> seriMissings = new LinkedList<>();

        Order order = orderservice.findById(orderID);
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderID(orderID);
        orderItems.forEach(orderItem -> {
            List<String> serisOrderItem = !orderItem.getSeri().equals("[]") ? new StringToList().StringToList(orderItem.getSeri()) : new ArrayList<>();
            Phones phone = phoneService.findById(orderItem.getPhoneID());
            List<String> serisPhone = !phone.getSeri().equals("[]") ? new StringToList().StringToList(phone.getSeri()) : new ArrayList<>();
            int missing = orderItem.getMissing();
            seriMissings.add(new SeriMissing(phone, missing));
        });
        model.addAttribute("seriMissings", seriMissings);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("order", order);
        return "admin/updateOrder";
    }

    @PostMapping(value = "/admin/updateSeriesOrder", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String updateSeriesOrder(@RequestParam("OrderID") int orderID, Model model,
                                    @RequestParam("seriesPhone") List<String> seriesPhone
    ) {
        List<String> seriduplicate = new ArrayList<>();
        List<Phones> phones = phoneService.findAll();
        Map<Integer, List<String>> checkseriexist = QueryallSeriPhone(phones);
        for (String seri : seriesPhone) {
            for (Phones phone : phones) {
                List<String> listSeriCheckFollowPhoneKey = checkseriexist.get(phone.getPhoneId());
                if (listSeriCheckFollowPhoneKey.contains(seri)) {
//                    List<String> seriOfPhoneKey = !phone.getSeri().equals("") ? new StringToList().StringToList(phone.getSeri()) : new ArrayList<>();
//                    seriOfPhoneKey.remove(seri);
//                    phone.setSeri(seriOfPhoneKey.toString());
//                    phone.setQuantity(phone.getQuantity()-1);
//                    phoneService.save(phone);
                    seriduplicate.add(seri);
                }
            }
        }
        if (!seriduplicate.isEmpty()) {
            model.addAttribute("seriduplicate", seriduplicate);
            model.addAttribute("seriesPhone", seriesPhone);
            Order order = orderservice.findById(orderID);
            model.addAttribute("order", order);
            return "admin/seriduplicateUpdateOrder";
        } else {
            List<OrderItem> orderItems = orderItemRepository.findAllByOrderID(orderID);
            orderItems.forEach(orderItem -> {
                List<String> serisOrderItem = !orderItem.getSeri().equals("[]") ? new StringToList().StringToList(orderItem.getSeri()) : new ArrayList<>();
                Phones phone = phoneService.findById(orderItem.getPhoneID());
                List<String> serisPhone = !phone.getSeri().equals("[]") ? new StringToList().StringToList(phone.getSeri()) : new ArrayList<>();
                int missing = orderItem.getMissing();
                for (int i = 0; i < missing; i++) {
                    serisOrderItem.add(seriesPhone.get(i));
                }
                orderItem.setSeri(serisOrderItem.toString());
                if (serisOrderItem.size() == orderItem.getQuantity()) orderItem.setMissing(0);
                orderitemsservice.save(orderItem);

                if (missing <= seriesPhone.size()) {
                    seriesPhone.subList(0, missing).clear();
                }

            });
            Order order = orderservice.findById(orderID);
            model.addAttribute("order", order);
            return "redirect:/Handshop/admin/OrderRequest";
        }
    }

    @PostMapping(value = "/admin/updateSeriesOrderSeriduplicate", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String updateSeriesOrderSeriduplicate(
            @RequestParam("OrderID") int orderID, Model model,
            @RequestParam("seriesPhone") List<String> seriesPhone,
            @RequestParam("seriduplicate") List<String> seriduplicate
    ) {

        List<Phones> phones = phoneService.findAll();
        Map<Integer, List<String>> checkseriexist = QueryallSeriPhone(phones);
        for (String seri : seriesPhone) {
            for (Phones phone : phones) {
                List<String> listSeriCheckFollowPhoneKey = checkseriexist.get(phone.getPhoneId());
                if (listSeriCheckFollowPhoneKey.contains(seri)) {
                    List<String> seriOfPhoneKey = !phone.getSeri().equals("") ? new StringToList().StringToList(phone.getSeri()) : new ArrayList<>();
                    seriOfPhoneKey.remove(seri);
                    phone.setSeri(seriOfPhoneKey.toString());
                    phone.setQuantity(phone.getQuantity() - 1);
                    phoneService.save(phone);
                }
            }
        }

        List<OrderItem> orderItems = orderItemRepository.findAllByOrderID(orderID);
        orderItems.forEach(orderItem -> {
            List<String> serisOrderItem = !orderItem.getSeri().equals("[]") ? new StringToList().StringToList(orderItem.getSeri()) : new ArrayList<>();
            Phones phone = phoneService.findById(orderItem.getPhoneID());
            List<String> serisPhone = !phone.getSeri().equals("[]") ? new StringToList().StringToList(phone.getSeri()) : new ArrayList<>();
            int missing = orderItem.getMissing();
            for (int i = 0; i < missing; i++) {
                serisOrderItem.add(seriesPhone.get(i));
            }
            orderItem.setSeri(serisOrderItem.toString());
            if (serisOrderItem.size() == orderItem.getQuantity()) orderItem.setMissing(0);
            orderitemsservice.save(orderItem);

            if (missing <= seriesPhone.size()) {
                seriesPhone.subList(0, missing).clear();
            }

        });
        return "redirect:/Handshop/admin/OrderRequest";
    }

    @PostMapping("/admin/rejectOrder")
    public String rejectOrder(@RequestParam("OrderID") int orderID) {
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderID(orderID);
        orderItems.forEach(orderItem -> {
            List<String> serisOrderItem = !orderItem.getSeri().equals("[]") ? new StringToList().StringToList(orderItem.getSeri()) : new ArrayList<>();
            Phones phone = phoneService.findById(orderItem.getPhoneID());
            List<String> serisPhone = !phone.getSeri().equals("[]") ? new StringToList().StringToList(phone.getSeri()) : new ArrayList<>();

            Set<String> set = new HashSet<>(serisOrderItem);
            set.addAll(serisPhone);
            List<String> mergedList = new ArrayList<>(set);

            phone.setSeri(mergedList.toString());
            phone.setQuantity(mergedList.size());
            phoneRepository.save(phone);
        });
        for (int i = 0; i < orderItems.size(); i++) {
            orderitemsservice.deleteById(orderItems.get(i).getOrderItemID());
        }
        orderservice.deleteById(orderID);
        return "redirect:/Handshop/admin/OrderRequest";
    }

    @GetMapping("/admin/rejectOrder")
    public String showRejectOrderForm(@RequestParam("OrderID") int orderID, Model model) {
//        int orderID = Integer.parseInt(OrderID);
        Order order = orderservice.findById(orderID);
        if (order != null) {
            model.addAttribute("order", order);
            return "admin/rejectOrderForm";
        } else {
            throw new RuntimeException("Không tìm thấy order với ID=" + orderID);
        }
    }

    public Map<Integer, List<String>> QueryallSeriPhone(List<Phones> phones) {
        Map<Integer, List<String>> resullt = new HashMap<>();
        phones.forEach(phone -> {
            List<String> seriesPhone = !phone.getSeri().equals("[]") ? new StringToList().StringToList(phone.getSeri()) : new ArrayList<>();
            int phoneID = phone.getPhoneId();
            resullt.put(phoneID, seriesPhone);
        });
        for (Map.Entry<Integer, List<String>> entry : resullt.entrySet()) {
            int phoneID = entry.getKey();
            List<String> seriList = entry.getValue();
        }
        return resullt;
    }

    /******************************************************************************************************/
                                            /** Profit */
    /******************************************************************************************************/
    @GetMapping("/admin/ProfitReport")
    public String ShowProfitReportForm() {
        return "/admin/ProfitReport";
    }

    @PostMapping("/admin/ProfitReport")
    public String ProfitReport(@RequestParam("start_date") String startDate,
                               @RequestParam("end_date") String endDate,
                               Model model) {
        String startDateFormat = startDate.replace("T", " ");
        String endDateFormat = endDate.replace("T", " ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start_date = LocalDateTime.parse(startDateFormat, formatter);
        LocalDateTime end_date = LocalDateTime.parse(endDateFormat, formatter);
        List<Order> orders = orderservice.findAll().stream().filter(item -> !item.getDateProcessed().equals("0000-00-00 00:00:00")).collect(Collectors.toList());

        orders.stream().filter(item -> {
            LocalDateTime dateTime = LocalDateTime.parse(item.getDateProcessed(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String DateTimeStr = dateTime.format(newFormatter);
            LocalDateTime dateTimeFormat = LocalDateTime.parse(DateTimeStr, newFormatter);
            return dateTimeFormat.isAfter(start_date) && dateTimeFormat.isBefore(end_date);
        }).collect(Collectors.toList());

        double profit = 0;
        for (Order order : orders) {
            profit += order.getAmount();
        }
        List<OrderItem> orderItems = orderitemsservice.findAll();
        List<OrderitemDTO> orderitemDTOs = new LinkedList<>(); /** List OrderItemDTO nằm trong OrderDTO */
        List<OrderDTO> orderDTOs = new LinkedList<>(); /** List OrderItemDTO nằm trong OrderDTO */

        /** Tạo một List orderItemDTOs: danh sách các sản phẩm được mua*/
        orderItems.forEach(orderItem -> {
            List<String> serisOrder = !orderItem.getSeri().equals("[]") ? new StringToList().StringToList(orderItem.getSeri()) : new ArrayList<>();/** Chuyển chuỗi phoneID thành List PhoneID*/
            OrderitemDTO orderitemDTO = new OrderitemDTO(orderItem, serisOrder);
            orderitemDTOs.add(orderitemDTO);
        });

        /** Tạo một List orderDTOs: danh sách các hóa đơn*/
        orders.forEach(order -> {
            List<String> serisOrder = new LinkedList<>(); /** List phone nằm trong orderItem*/
            orderitemDTOs.forEach(item -> item.getSeris().forEach(seriItem -> serisOrder.add(seriItem)));
            Customer customer = customerService.findById(order.getCustomerId());

            List<OrderitemDTO> orderitemDTOs2 = new LinkedList<>();
            List<Phones> phonesOrder = new LinkedList<>();
            orderitemDTOs.forEach(item -> {
                if (item.getOrderID() == order.getOrderID()) {
                    phonesOrder.add(phoneService.findById(item.getPhoneID()));
                    orderitemDTOs2.add(item);
                }
            });
            OrderDTO orderDTO = new OrderDTO(order, orderitemDTOs2, serisOrder, phonesOrder, customer);
            orderDTOs.add(orderDTO);
        });
        model.addAttribute("orderDTOs", orderDTOs);
        model.addAttribute("orders", orders);
        model.addAttribute("profit", profit);
        model.addAttribute("startDate", startDateFormat);
        model.addAttribute("endDate", endDateFormat);
        return "/admin/ProfitReportAccees";
    }
    /******************************************************************************************************/
                                            /** Import file exel */
    /******************************************************************************************************/

    @GetMapping("/admin/importExel")
    public String importPhonesGet() {
    return "admin/importExel";
    }

    @PostMapping("/admin/importExel")
    public String importPhones(@RequestParam("file") MultipartFile file) {
        try {
            // Đọc tệp Excel
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            // Lặp qua từng dòng trong bảng Excel
            for (Row row : sheet) {

                // Bỏ qua n dòng đầu tiên
                if (row.getRowNum() < 1) {continue;}
                CellValue checkrow = formulaEvaluator.evaluate(row.getCell(17));
                if(checkrow.getBooleanValue()==true){continue;}
                // Tạo một đối tượng Phones từ dữ liệu trong dòng
                Phones phone = new Phones();
                phone.setBrandId((int) formulaEvaluator.evaluate(row.getCell(15)).getNumberValue());
                phone.setCategoryId((int) formulaEvaluator.evaluate(row.getCell(16)).getNumberValue());
                phone.setPhoneName(row.getCell(3).getStringCellValue());
                phone.setModel(formulaEvaluator.evaluate(row.getCell(4)).getStringValue());
                phone.setReleaseYear((int) row.getCell(5).getNumericCellValue());
                phone.setScreenSize(row.getCell(6).getNumericCellValue());
                phone.setStorageCapacity((int) row.getCell(7).getNumericCellValue());
                phone.setRam((int) row.getCell(8).getNumericCellValue());
                phone.setOperatingSystem(row.getCell(9).getStringCellValue());
                phone.setPrice(row.getCell(10).getStringCellValue());
                phone.setColor(row.getCell(11).getStringCellValue());
                phone.setImageName(row.getCell(12).getStringCellValue());
                phone.setSeri(row.getCell(14).getStringCellValue());
                phone.setQuantity(new StringToList().StringToList(phone.getSeri()).size());

                // Lưu đối tượng Phones vào cơ sở dữ liệu
                phoneService.save(phone);
            }

            // Đóng workbook
            workbook.close();

            return "redirect:Handshop/admin";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:Handshop/admin/eror";
        }
    }
    @GetMapping("/admin/download")
    public ResponseEntity<Resource> downloadExcelTemplate() {
        Resource resource = new ClassPathResource("templates/template.xlsx");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=template.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
    private void fillDataToSheet(Sheet sheet, String[] headers, List<?> dataList, int startCellIndex) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i + startCellIndex);
            cell.setCellValue(headers[i]);
        }

        int rowIndex = 1;
        for (Object data : dataList) {
            Row row = sheet.createRow(rowIndex++);
            Field[] fields = data.getClass().getDeclaredFields();
            int cellIndex = startCellIndex;
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Cell cell = row.createCell(cellIndex++);
                    Object value = field.get(data);
                    if (value != null) {
                        cell.setCellValue(value.toString());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @GetMapping("/admin/ExportInventory")
    public ResponseEntity<Resource> exportInventory() {
        List<Phones> phones = phoneService.findAll();
        List<Brands> brands = brandService.findAll();
        List<Categories> categories = categoryService.findAll();
        // Tạo một Workbook (Sử dụng XSSFWorkbook cho định dạng .xlsx)
        try (Workbook workbook = new XSSFWorkbook()) {
            /** Sheet 2*/
            // Tạo Sheet2 và điền dữ liệu từ brandService và categoryService
            {
                Sheet sheet2 = workbook.createSheet("Dữ liệu");
                Row headerRow = sheet2.createRow(0);
                String[] headers = {"Brand", "BrandID", "Category", "CategoryID", "Model"};

                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i+1);
                    cell.setCellValue(headers[i]);
                }

                int rowIndexbrand = 1;
                int brandSize = brands.size();
                int categorySize = categories.size();
                int phoneModelSize = phones.size();
                int max = Math.max(brandSize, Math.max(categorySize, phoneModelSize));
                for (int i =0 ; i < max ; i++) {
                    Row row = sheet2.createRow(rowIndexbrand++);
                    Cell cell = row.createCell(1); // Cột Brands
                    if(i<brandSize) cell.setCellValue(brands.get(i).getBrandName());
                    cell = row.createCell(2); // Cột BrandID
                    if(i<brandSize) cell.setCellValue(brands.get(i).getBrandID());
                    cell = row.createCell(3); // Cột Categories
                    if(i<categorySize) cell.setCellValue(categories.get(i).getCategoryName());
                    cell = row.createCell(4); // Cột CategoryID
                    if(i<categorySize) cell.setCellValue(categories.get(i).getCategoryID());
                    cell = row.createCell(5); // Cột Model
                    if(i<phoneModelSize) cell.setCellValue(phones.get(i).getModel());
                }
            }
                // Sử dụng phương thức fillDataToSheet để điền dữ liệu cho Sheet2
//                Sheet sheet2 = workbook.createSheet("Sheet2");
//                String[] brandHeaders = {"Brand", "BrandID"};
//                fillDataToSheet(sheet2, brandHeaders, brands, 1);
//
//                String[] categoryHeaders = {"Category", "CategoryID"};
//                fillDataToSheet(sheet2, categoryHeaders, categories, 3);

//                String[] phoneHeaders = {"Model"};
//                fillDataToSheet(sheet2, phoneHeaders, phones, 5);

            /** Sheet 1*/

            // Tạo một Sheet
            Sheet sheet1 = workbook.createSheet("Danh Sách điện thoại");

            // Thêm header vào dòng đầu tiên
            Row headerRow1 = sheet1.createRow(0);
            String[] headers1 = {"ID", "Brand", "Category", "PhoneName", "Model", "ReleaseYear", "ScreenSize", "StorageCapacity",
                    "RAM", "OperatingSystem", "Price", "Color", "ImageName", "Quantity", "Seri", "BrandID", "CategoryID", "Isblank"};

            for (int i = 0; i < headers1.length; i++) {
                Cell cell = headerRow1.createCell(i);
                cell.setCellValue(headers1[i]);
            }

            // Tạo một DataValidation cho dropdown list Brand từ Sheet2
//            DataValidationHelper dataValidationHelper = sheet1.getDataValidationHelper();
//            DataValidationConstraint brandConstraint = dataValidationHelper.createFormulaListConstraint("Dữ liệu!$B$2:$B$" + (brands.size() + 1));
//            CellRangeAddressList brandRange = new CellRangeAddressList(2, Integer.MAX_VALUE, 1, 1);
//            DataValidation brandValidation = dataValidationHelper.createValidation(brandConstraint, brandRange);
//            sheet1.addValidationData(brandValidation);

//            // Tạo một DataValidation cho dropdown list Category từ Sheet2
//            DataValidationConstraint categoryConstraint = dataValidationHelper.createFormulaListConstraint("Dữ liệu!$D$2:$D$" + (categories.size() + 1));
//            CellRangeAddressList categoryRange = new CellRangeAddressList(2, Integer.MAX_VALUE, 2, 2);
//            DataValidation categoryValidation = dataValidationHelper.createValidation(categoryConstraint, categoryRange);
//            sheet1.addValidationData(categoryValidation);

            // Ghi dữ liệu vào Sheet bắt đầu từ dòng thứ 2
            int rowIndex1 = 1;
            for (Phones phone : phones) {
                Row row = sheet1.createRow(rowIndex1++);
                int cellIndex = 0;
                Class<?> phoneClass = phone.getClass();
                Field[] fields = phoneClass.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true); // Cho phép truy cập các thuộc tính private
                    try {
                        Cell cell = row.createCell(cellIndex++);
                        Object value = field.get(phone); // Lấy giá trị của trường từ đối tượng phone
                        if (value != null) {
                            if (value instanceof String) {
                                cell.setCellValue((String) value);
                            } else if (value instanceof Integer) {
                                int id = (Integer) value;
                                if(cellIndex==2) {
                                    Brands brand = brandService.findById((id));
                                    cell.setCellValue(brand.getBrandName());
                                    Cell cellBrandID = row.createCell(15);
                                    cellBrandID.setCellValue(id);
                                }else if(cellIndex==3) {
                                    Categories category = categoryService.findById(id);
                                    cell.setCellValue(category.getCategoryName());
                                    Cell categoryID = row.createCell(16);
                                    categoryID.setCellValue(id);
                                } else {cell.setCellValue((Integer) value);}
                            } else if (value instanceof Double) {
                                cell.setCellValue((Double) value);
                            }
                        }
                    } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

            // Trả về dữ liệu Excel dưới dạng InputStreamResource
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);
            InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bos.toByteArray()));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Danh sách điện thoại.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/admin/ExportSellPhone")
    public ResponseEntity<Resource> ExportSellPhone() {
        List<OrderItem> orderItems = orderitemsservice.findAll();
        List<OrderitemDTO> orderitemDTOS = orderItems2orderitemDTOS(orderItems);

        // Tạo một Workbook (Sử dụng XSSFWorkbook cho định dạng .xlsx)
        try (Workbook workbook = new XSSFWorkbook()) {
            // Tạo một Sheet
            Sheet sheet1 = workbook.createSheet("Danh Sách điện thoại");
            // Thêm header vào dòng đầu tiên
            Row headerRow1 = sheet1.createRow(0);
            String[] headers1 = {"OrderItemID", "OrderID", "PhoneID", "Price", "Quantity", "Missing", "Seri", "Thông tin điện thoại"};
            fillDataToSheet(sheet1, headers1,
                    filterDateProcess(orderitemDTOS,"0000-00-00 00:00:00", "", false),
                    0);
            // Trả về dữ liệu Excel dưới dạng InputStreamResource
            return ResponseExel(workbook, "Danh sách điện thoại đã bán");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    private ResponseEntity<Resource> ResponseExel(Workbook workbook, String ExelName){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        }catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bos.toByteArray()));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+ExelName+".xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
    @GetMapping("/admin/ExportSoldPhonesWait")
    public ResponseEntity<Resource> ExportSoldPhonesWait() {
        List<OrderItem> orderItems = orderitemsservice.findAll();
        List<OrderitemDTO> orderitemDTOS = orderItems2orderitemDTOS(orderItems);

        // Tạo một Workbook (Sử dụng XSSFWorkbook cho định dạng .xlsx)
        try (Workbook workbook = new XSSFWorkbook()) {
            // Tạo một Sheet
            Sheet sheet1 = workbook.createSheet("Danh Sách điện thoại");
            // Thêm header vào dòng đầu tiên
            Row headerRow1 = sheet1.createRow(0);
            String[] headers1 = {"OrderItemID", "OrderID", "PhoneID", "Price", "Quantity", "Missing", "Seri", "Thông tin điện thoại"};
            fillDataToSheet(sheet1, headers1,
                    filterDateProcess(orderitemDTOS,"0000-00-00 00:00:00", "", true),
                    0);
            // Trả về dữ liệu Excel dưới dạng InputStreamResource
            return ResponseExel(workbook, "Danh sách điện thoại đang trong hàng chờ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    /******************************************************************************************************/
                                    /** Xóa comment */
    /******************************************************************************************************/
    @GetMapping("/admin/comment/delete/{phoneId}/{idcom}")
    public String deletecommentbyId(@PathVariable("idcom") int idcom,
                                    @PathVariable("phoneId") int phoneId) {
            prdRevService.deleteById(idcom);
        return "redirect:/Handshop/ViewDetailPhone/" + phoneId;
    }
}
