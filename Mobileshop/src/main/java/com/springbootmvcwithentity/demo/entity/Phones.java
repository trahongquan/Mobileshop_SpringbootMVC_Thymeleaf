package com.springbootmvcwithentity.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "phones")
public class Phones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PhoneID")
    private int phoneId;

    @Column(name = "BrandID")
    private int brandId;

    @Column(name = "CategoryID")
    private int categoryId;

    @Column(name = "PhoneName")
    private String phoneName;

    @Column(name = "ModelID")
    private int modelID;

    @Column(name = "ReleaseYear")
    private int releaseYear;

    @Column(name = "ScreenSize")
    private double screenSize;

    @Column(name = "StorageCapacityID")
    private int storageCapacityID;

    @Column(name = "RAMID")
    private int ramID;

    @Column(name = "OperatingSystemID")
    private int operatingSystemID;

    @Column(name = "Price")
    private String price;

    @Column(name = "ColorID")
    private int colorID;

    @JsonProperty("ImageName")
    @Column(name = "ImageName")
    private String ImageName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "seri")
    private String seri;

    // Constructors, getters, and setters
    // ...

    public Phones() {
    }

    public Phones(int brandId, int categoryId, String phoneName, int modelID, int releaseYear, double screenSize,
            int storageCapacityID, int ramID, int operatingSystemID, String price, int colorID, String imageName,
            int quantity, String seri) {
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.phoneName = phoneName;
        this.modelID = modelID;
        this.releaseYear = releaseYear;
        this.screenSize = screenSize;
        this.storageCapacityID = storageCapacityID;
        this.ramID = ramID;
        this.operatingSystemID = operatingSystemID;
        this.price = price;
        this.colorID = colorID;
        ImageName = imageName;
        this.quantity = quantity;
        this.seri = seri;
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

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
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

    public int getStorageCapacityID() {
        return storageCapacityID;
    }

    public void setStorageCapacityID(int storageCapacityID) {
        this.storageCapacityID = storageCapacityID;
    }

    public int getRamID() {
        return ramID;
    }

    public void setRamID(int ramID) {
        this.ramID = ramID;
    }

    public int getOperatingSystemID() {
        return operatingSystemID;
    }

    public void setOperatingSystemID(int operatingSystemID) {
        this.operatingSystemID = operatingSystemID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getColorID() {
        return colorID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
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

    @Override
    public String toString() {
        return "Phone{" +
                "phoneId=" + phoneId +
                ", brandId=" + brandId +
                ", categoryId=" + categoryId +
                ", phoneName='" + phoneName + '\'' +
                ", modelID=" + modelID +
                ", releaseYear=" + releaseYear +
                ", screenSize=" + screenSize +
                ", storageCapacityID=" + storageCapacityID +
                ", ramID=" + ramID +
                ", operatingSystemID=" + operatingSystemID +
                ", price='" + price + '\'' +
                ", colorID=" + colorID +
                ", ImageName='" + ImageName + '\'' +
                ", quantity=" + quantity +
                ", seri='" + seri + '\'' +
                '}';
    }
}
