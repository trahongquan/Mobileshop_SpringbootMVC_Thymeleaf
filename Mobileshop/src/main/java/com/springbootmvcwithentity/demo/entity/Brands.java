package com.springbootmvcwithentity.demo.entity;

import javax.persistence.*;

    @Entity
    @Table(name = "Brands")
public class Brands {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "BrandID")
        private int BrandID;

        @Column(name = "BrandName")
        private String BrandName;

        public Brands() {
        }

        public Brands(String brandName) {
            BrandName = brandName;
        }

        public int getBrandID() {
            return BrandID;
        }

        public void setBrandID(int brandID) {
            BrandID = brandID;
        }

        public String getBrandName() {
            return BrandName;
        }

        public void setBrandName(String brandName) {
            BrandName = brandName;
        }

        @Override
        public String toString() {
            return "Brands{" +
                    "BrandID=" + BrandID +
                    ", BrandName='" + BrandName + '\'' +
                    '}';
        }
    }

