package com.springbootmvcwithentity.demo.ClassSuport;

public class Product {
    private String model;
    private int quantity;

    public Product(String model, int quantity) {
        this.model = model;
        this.quantity = quantity;
    }

    public Product() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "model='" + model + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
