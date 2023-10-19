//package com.springbootmvcwithentity.demo.entity;
//
//import javax.persistence.*;
//
//    @Entity
//    @Table(name = "Price")
//public class Price {
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        @Column(name = "PriceID")
//        private int priceId;
//
//        @Column(name = "PhoneID")
//        private int phoneId;
//
//        @Column(name = "ImportPrice")
//        private int importPrice;
//
//        @Column(name = "SellPrice")
//        private String sellPrice;
//
//        @Column(name = "Discount")
//        private String discount;
//
//        public Price() {
//        }
//
//        public Price(int phoneId, int importPrice, String sellPrice, String discount) {
//            this.phoneId = phoneId;
//            this.importPrice = importPrice;
//            this.sellPrice = sellPrice;
//            this.discount = discount;
//        }
//
//        public int getPriceId() {
//            return priceId;
//        }
//
//        public void setPriceId(int priceId) {
//            this.priceId = priceId;
//        }
//
//        public int getPhoneId() {
//            return phoneId;
//        }
//
//        public void setPhoneId(int phoneId) {
//            this.phoneId = phoneId;
//        }
//
//        public int getImportPrice() {
//            return importPrice;
//        }
//
//        public void setImportPrice(int importPrice) {
//            this.importPrice = importPrice;
//        }
//
//        public String getSellPrice() {
//            return sellPrice;
//        }
//
//        public void setSellPrice(String sellPrice) {
//            this.sellPrice = sellPrice;
//        }
//
//        public String getDiscount() {
//            return discount;
//        }
//
//        public void setDiscount(String discount) {
//            this.discount = discount;
//        }
//
//        @Override
//        public String toString() {
//            return "Price{" +
//                    "priceId=" + priceId +
//                    ", phoneId=" + phoneId +
//                    ", importPrice=" + importPrice +
//                    ", sellPrice='" + sellPrice + '\'' +
//                    ", discount='" + discount + '\'' +
//                    '}';
//        }
//    }
