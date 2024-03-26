package com.springbootmvcwithentity.demo.ClassSuport;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.springbootmvcwithentity.demo.dto.PhoneCartDTO;

import java.util.ArrayList;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"phonesList"}) // Xác định thứ tự của các thuộc tính trong JSON
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CartCookie {

    private List<PhoneCartDTO> phoneCartDTOSList = new ArrayList<>();
    private List<Integer> ProductsID = new ArrayList<>();
    private List<ProductsIDandQuantity> listProductsIDandQuantity = new ArrayList<>();

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

    public void setProducts(List<PhoneCartDTO> newProducts) {
        this.phoneCartDTOSList = newProducts;
    }
    public List<Integer> getProductsID() {
        return ProductsID;
    }

    public void setProductsID(List<Integer> newProductsID) {
        this.ProductsID = newProductsID;
    }

    public List<ProductsIDandQuantity> getListProductsIDandQuantity() {
        return listProductsIDandQuantity;
    }

    public void setListProductsIDandQuantity(List<ProductsIDandQuantity> listProductsIDandQuantity) {
        this.listProductsIDandQuantity = listProductsIDandQuantity;
    }

    @Override
    public String toString() {
        return "CartCookie{" +
                "phonesList=" + phoneCartDTOSList +
                '}';
    }
}


