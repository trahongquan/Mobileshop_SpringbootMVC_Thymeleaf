package com.springbootmvcwithentity.demo.entity;
    import com.fasterxml.jackson.annotation.JsonProperty;
    import lombok.Data;

    import javax.persistence.*;

@Data
@Entity
@Table(name = "Phones")
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

    @Column(name = "Model")
    private String model;

    @Column(name = "ReleaseYear")
    private int releaseYear;

    @Column(name = "ScreenSize")
    private double screenSize;

    @Column(name = "StorageCapacity")
    private int storageCapacity;

    @Column(name = "RAM")
    private int ram;

    @Column(name = "OperatingSystem")
    private String operatingSystem;

    @Column(name = "Price")
    private String price;

    @Column(name = "Color")
    private String color;

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

    public Phones(int brandId, int categoryId, String phoneName, String model, int releaseYear, double screenSize, int storageCapacity, int ram, String operatingSystem, String price, String color, String imageName, int quantity, String seri) {
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.phoneName = phoneName;
        this.model = model;
        this.releaseYear = releaseYear;
        this.screenSize = screenSize;
        this.storageCapacity = storageCapacity;
        this.ram = ram;
        this.operatingSystem = operatingSystem;
        this.price = price;
        this.color = color;
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
        return "Phones{" +
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
                ", price='" + price + '\'' +
                ", color='" + color + '\'' +
                ", ImageName='" + ImageName + '\'' +
                ", quantity=" + quantity +
                ", seri='" + seri + '\'' +
                '}';
    }
}
