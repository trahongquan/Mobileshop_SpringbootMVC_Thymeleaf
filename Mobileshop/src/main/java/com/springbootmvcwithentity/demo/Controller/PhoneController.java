package com.springbootmvcwithentity.demo.Controller;

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
import com.springbootmvcwithentity.demo.service.categorie.CategoryService;
import com.springbootmvcwithentity.demo.ClassSuport.StringToList;
import com.springbootmvcwithentity.demo.service.order.orderService;
import com.springbootmvcwithentity.demo.service.orderitem.orderitemsService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
            // Khai báo đối tượng DecimalFormat với mẫu định dạng mong muốn
//                DecimalFormat df = new DecimalFormat("#.##");
//                double result = Double.parseDouble(phoneDTO.getPriceDTO().getSellPrice()) / (1 - Double.parseDouble(phoneDTO.getPriceDTO().getDiscount()))/* + random*/;
//                String formattedResult = df.format(result);

        }
        return phoneDTOS;
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
        List<Phones> phones = phoneRepository.findAllByPhoneNameContaining(phone.getPhoneName().split(" ")[0]);
        List<PhoneDTO> phoneDTOS = Phone2PhoneDTOS(phones);
        System.out.println(phone.getPhoneName().split(" ")[0]);
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
        List<Phones> phones = phoneRepository.findAllByPhoneNameContaining(inputdatasearch);
        List<PhoneDTO> phoneDTOS = Phone2PhoneDTOS(phones);
        model.addAttribute("phoneDTOS", phoneDTOS); /** cách xử lý ở backEnd*/
        return "index";
    }

    /******************************************************************************************************/
                                                    /** Khu vực của admin*/
    /******************************************************************************************************/


    @GetMapping({"/admin", "/admin/"})
    public String redirectToAdminHandshopListAdmin(Model model) {
        List<Phones> phones = phoneService.findAll();
        List<PhoneDTO> phoneDTOS = Phone2PhoneDTOS(phones);
        model.addAttribute("phoneDTOS", phoneDTOS); /** cách xử lý ở backEnd*/
        return "admin/list-phones";
    }

    @GetMapping({"/admin/soldphones"})
    public String redirectToAdminHandshopListSoldPhones(Model model) {
        List<OrderItem> orderItems = orderitemsservice.findAll();
        List<OrderitemDTO> orderitemDTOS = new LinkedList<>();
        orderItems.forEach(item ->{
            Phones phone = phoneService.findById(item.getPhoneID());
            Brands brand = brandService.findById(phone.getBrandId());
            Categories category = categoryService.findById(phone.getCategoryId());
            orderitemDTOS.add(new OrderitemDTO(item,new PhoneDTO(phone,brand,category)));
        });
        model.addAttribute("orderitemDTOS", orderitemDTOS); /** cách xử lý ở backEnd*/
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
        List<String> listseri = new StringToList().StringToList(phone.getSeri());
        phone.setQuantity(listseri.size());

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
            System.out.println(phone);
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
    public String showbrandandcategoryForm(Model model) {
        List<Brands> brands = brandService.findAll();
        List<Categories> categories = categoryService.findAll();
        Phones phone = new Phones();
        model.addAttribute("phone", phone);
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        return "admin/add-Brand-Category";
    }


    @PostMapping("/admin/brand/add")
    public String addBrand(@RequestParam("addBrand") String brandName){
        Brands brand = new Brands(brandName);
        brandService.save(brand);
        return "redirect:/Handshop/admin/brandandcategory";
    }
    @PostMapping("/admin/category/add")
    public String addCategory(@RequestParam("addCategory") String categoryName){
        Categories category = new Categories(categoryName);
        categoryService.save(category);
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
        System.out.println(seriMissings.toString());
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
            System.out.println("PhoneID: " + phoneID);
            System.out.println("Seri List: " + seriList);
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

            // Lặp qua từng dòng trong bảng Excel
            for (Row row : sheet) {
                // Bỏ qua dòng tiêu đề
                if (row.getRowNum() == 0) {
                    continue;
                }

                // Tạo một đối tượng Phones từ dữ liệu trong dòng
                Phones phone = new Phones();
                phone.setBrandId((int) row.getCell(0).getNumericCellValue());
                phone.setCategoryId((int) row.getCell(1).getNumericCellValue());
                phone.setPhoneName(row.getCell(2).getStringCellValue());
                phone.setModel(row.getCell(3).getStringCellValue());
                phone.setReleaseYear((int) row.getCell(4).getNumericCellValue());
                phone.setScreenSize(row.getCell(5).getNumericCellValue());
                phone.setStorageCapacity((int) row.getCell(6).getNumericCellValue());
                phone.setRam((int) row.getCell(7).getNumericCellValue());
                phone.setOperatingSystem(row.getCell(8).getStringCellValue());
                phone.setPrice(row.getCell(9).getStringCellValue());
                phone.setColor(row.getCell(10).getStringCellValue());
                phone.setImageName(row.getCell(11).getStringCellValue());
                phone.setQuantity((int) row.getCell(12).getNumericCellValue());
                phone.setSeri(row.getCell(13).getStringCellValue());

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
