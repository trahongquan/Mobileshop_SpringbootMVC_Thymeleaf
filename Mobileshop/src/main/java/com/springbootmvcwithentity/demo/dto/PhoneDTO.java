package com.springbootmvcwithentity.demo.dto;

import com.springbootmvcwithentity.demo.entity.Brands;
import com.springbootmvcwithentity.demo.entity.Categories;
import com.springbootmvcwithentity.demo.entity.Phones;
import com.springbootmvcwithentity.demo.entity.extand.*;

import java.io.Serializable;

public class PhoneDTO implements Serializable {

    private int phoneId;

    private int brandId;

    private int categoryId;

    private String phoneName;

    private int modelId;

    private int releaseYear;

    private double screenSize;

    private int storageCapacityId;

    private int ramId;

    private int operatingSystemId;

    private String price;

    private int colorId;

    private String ImageName;

    private int quantity;

    private String seri;

    Brands brand;
    Categories category;
    Models model;
    OperatingSystem operatingSystem;
    RAM ram;
    StorageCapacity storageCapacity;
    Color color;

    public PhoneDTO() {
    }

    public PhoneDTO(int phoneId, int brandId, int categoryId, String phoneName, int modelId, int releaseYear, double screenSize, int storageCapacityId, int ramId, int operatingSystemId, String price, int colorId, String imageName, int quantity, String seri, Brands brand, Categories category, Models model, OperatingSystem operatingSystem, RAM ram, StorageCapacity storageCapacity, Color color) {
        this.phoneId = phoneId;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.phoneName = phoneName;
        this.modelId = modelId;
        this.releaseYear = releaseYear;
        this.screenSize = screenSize;
        this.storageCapacityId = storageCapacityId;
        this.ramId = ramId;
        this.operatingSystemId = operatingSystemId;
        this.price = price;
        this.colorId = colorId;
        ImageName = imageName;
        this.quantity = quantity;
        this.seri = seri;
        this.brand = brand;
        this.category = category;
        this.model = model;
        this.operatingSystem = operatingSystem;
        this.ram = ram;
        this.storageCapacity = storageCapacity;
        this.color = color;
    }

    public PhoneDTO(Phones phone, Brands brand, Categories category, Models model, OperatingSystem operatingSystem, RAM ram, StorageCapacity storageCapacity, Color color) {
        this.phoneId = phone.getPhoneId();
        this.phoneName = phone.getPhoneName();
        this.releaseYear = phone.getReleaseYear();
        this.screenSize = phone.getScreenSize();
        this.price = phone.getPrice();
        ImageName = phone.getImageName();
        this.quantity = phone.getQuantity();
        this.seri = phone.getSeri();
        this.brandId = phone.getBrandId();
        this.categoryId = phone.getCategoryId();
        this.modelId = phone.getModelID();
        this.operatingSystemId = phone.getOperatingSystemID();
        this.storageCapacityId = phone.getStorageCapacityID();
        this.colorId = phone.getColorID();
        this.brand = brand;
        this.category = category;
        this.model = model;
        this.operatingSystem = operatingSystem;
        this.ram = ram;
        this.storageCapacity = storageCapacity;
        this.color = color;
    }

    @Override
    public String toString() {
        return "PhoneDTO{" +
                "phoneId=" + phoneId +
                ", brandId=" + brandId +
                ", categoryId=" + categoryId +
                ", phoneName='" + phoneName + '\'' +
                ", modelId=" + modelId +
                ", releaseYear=" + releaseYear +
                ", screenSize=" + screenSize +
                ", storageCapacityId=" + storageCapacityId +
                ", ramId=" + ramId +
                ", operatingSystemId=" + operatingSystemId +
                ", price='" + price + '\'' +
                ", colorId=" + colorId +
                ", ImageName='" + ImageName + '\'' +
                ", quantity=" + quantity +
                ", seri='" + seri + '\'' +
                ", brand=" + brand +
                ", category=" + category +
                ", model=" + model +
                ", operatingSystem=" + operatingSystem +
                ", ram=" + ram +
                ", storageCapacity=" + storageCapacity +
                ", color=" + color +
                '}';
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

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
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

    public int getStorageCapacityId() {
        return storageCapacityId;
    }

    public void setStorageCapacityId(int storageCapacityId) {
        this.storageCapacityId = storageCapacityId;
    }

    public int getRamId() {
        return ramId;
    }

    public void setRamId(int ramId) {
        this.ramId = ramId;
    }

    public int getOperatingSystemId() {
        return operatingSystemId;
    }

    public void setOperatingSystemId(int operatingSystemId) {
        this.operatingSystemId = operatingSystemId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    public Brands getBrand() {
        return brand;
    }

    public void setBrand(Brands brand) {
        this.brand = brand;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Models getModel() {
        return model;
    }

    public void setModel(Models model) {
        this.model = model;
    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public StorageCapacity getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(StorageCapacity storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
