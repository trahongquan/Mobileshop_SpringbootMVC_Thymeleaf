package com.springbootmvcwithentity.demo.ClassSuport;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"ProductsIDandQantity"}) // Xác định thứ tự của các thuộc tính trong JSON
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ProductsIDandQuantity {
    int id, quantity;
    double price;
//    public ProductsIDandQantity(int id, int quantity){
//        this.id = id;
//        this.quantity = quantity;
//    }
    @JsonCreator
    public ProductsIDandQuantity(@JsonProperty("id") int id, @JsonProperty("quantity") int quantity, @JsonProperty("price") double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("quantity")
    public int getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("price")
    public double getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(double price) {
        this.price = price;
    }
}
