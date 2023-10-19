package com.springbootmvcwithentity.demo.ClassSuport;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.springbootmvcwithentity.demo.dto.PhoneCartDTO;
import com.springbootmvcwithentity.demo.entity.Phones;

import java.util.ArrayList;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"phonesList"}) // Xác định thứ tự của các thuộc tính trong JSON
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CartCookie {
    private List<PhoneCartDTO> phoneCartDTOSList = new ArrayList<>();

    // Các phương thức để thêm sản phẩm, xóa sản phẩm, tính tổng giá trị, và các phương thức khác cho giỏ hàng
    // Ví dụ:
    public void addProduct(PhoneCartDTO product) {
        phoneCartDTOSList.add(product);
    }

    public void removeProduct(PhoneCartDTO product) {
        phoneCartDTOSList.remove(product);
    }

    public List<PhoneCartDTO> getProducts() {
        return phoneCartDTOSList;
    }

    // Phương thức để thiết lập danh sách sản phẩm mới cho giỏ hàng
    public void setProducts(List<PhoneCartDTO> newProducts) {
        this.phoneCartDTOSList = newProducts;
    }
    // Các phương thức khác liên quan đến giỏ hàng

    @Override
    public String toString() {
        return "CartCookie{" +
                "phonesList=" + phoneCartDTOSList +
                '}';
    }
}


