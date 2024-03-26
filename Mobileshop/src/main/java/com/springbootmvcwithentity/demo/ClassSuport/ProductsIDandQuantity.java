package com.springbootmvcwithentity.demo.ClassSuport;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"ProductsIDandQantity"}) // Xác định thứ tự của các thuộc tính trong JSON
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ProductsIDandQuantity {
    int id, quantity;
//    public ProductsIDandQantity(int id, int quantity){
//        this.id = id;
//        this.quantity = quantity;
//    }
    @JsonCreator
    public ProductsIDandQuantity(@JsonProperty("id") int id, @JsonProperty("quantity") int quantity) {
        this.id = id;
        this.quantity = quantity;
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
}
