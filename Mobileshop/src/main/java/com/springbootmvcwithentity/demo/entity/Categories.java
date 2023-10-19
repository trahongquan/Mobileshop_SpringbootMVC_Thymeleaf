package com.springbootmvcwithentity.demo.entity;

import javax.persistence.*;

@Entity
    @Table(name = "categories")
public class Categories{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private int CategoryID;
        @Column(name = "CategoryName")
    private String CategoryName;

    public Categories() {
    }

    public Categories(String categoryeName) {
        CategoryName = categoryeName;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "CategoryID=" + CategoryID +
                ", CategoryName='" + CategoryName + '\'' +
                '}';
    }
}
