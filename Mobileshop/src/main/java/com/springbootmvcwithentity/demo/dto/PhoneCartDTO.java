package com.springbootmvcwithentity.demo.dto;


import com.springbootmvcwithentity.demo.entity.Brands;
import com.springbootmvcwithentity.demo.entity.Categories;
import com.springbootmvcwithentity.demo.entity.Phones;

import java.io.Serializable;

public class PhoneCartDTO implements Serializable {

    private int phoneId;

    private int brandId;

    private int categoryId;

    private String phoneName;

    private String model;

    private int releaseYear;

    private double screenSize;

    private int storageCapacity;

    private int ram;

    private String operatingSystem;

    private String color;

    private String ImageName;

    private String price;

    private int quantity;

    private String seri;

    private Brands brand;

    private Categories category;

    private int quantityorder;

    public int getQuantityorder() {
        return quantityorder;
    }

    public void setQuantityorder(int quantityorder) {
        this.quantityorder = quantityorder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Categories getCategory() {

        return category;
    }
    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Brands getBrand() {
        return brand;
    }

    public void setBrand(Brands brand) {
        this.brand = brand;
    }

    public PhoneCartDTO() {
    }

    public PhoneCartDTO(Phones phone, Brands brand, Categories category, int quantityorder){
        this.phoneId = phone.getPhoneId();
        this.brandId = phone.getBrandId();
        this.categoryId = phone.getCategoryId();
        this.phoneName = phone.getPhoneName();
        this.model = phone.getModel();
        this.price = phone.getPrice();
        this.seri = phone.getSeri();
        this.releaseYear = phone.getReleaseYear();
        this.screenSize = phone.getScreenSize();
        this.storageCapacity = phone.getStorageCapacity();
        this.ram = phone.getRam();
        this.operatingSystem = phone.getOperatingSystem();
        this.color = phone.getColor();
        this.ImageName = phone.getImageName();
        this.quantity = phone.getQuantity();
        this.brand = brand;
        this.category = category;
        this.quantityorder = quantityorder;
    }

    @Override
    public String toString() {
        return "PhoneCartDTO{" +
                "phoneId=" + phoneId +
                ", brandId=" + brandId +
                ", categoryId=" + categoryId +
                ", phoneName='" + phoneName + '\'' +
                ", model='" + model + '\'' +
                ", releaseYear=" + releaseYear +
                ", screenSize=" + screenSize +
                ", storageCapacity=" + storageCapacity +
                ", ram=" + ram +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", color='" + color + '\'' +
                ", ImageName='" + ImageName + '\'' +
                ", price='" + price + '\'' +
                ", quantity=" + quantity +
                ", seri='" + seri + '\'' +
                ", brand=" + brand +
                ", category=" + category +
                ", quantityorder=" + quantityorder +
                '}';
    }
}

