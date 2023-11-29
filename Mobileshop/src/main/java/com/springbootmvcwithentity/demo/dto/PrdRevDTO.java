package com.springbootmvcwithentity.demo.dto;

import com.springbootmvcwithentity.demo.entity.Customer;
import com.springbootmvcwithentity.demo.entity.productreview;

public class PrdRevDTO {

    private int reviewID;

    private int phoneID;

    private int customerID;

    private Customer customer;

    private int rating;

    private String comment;

    private String reviewDate;

    public PrdRevDTO() {
    }

    public PrdRevDTO(int reviewID, int phoneID, Customer customer, int rating, String comment, String reviewDate) {
        this.reviewID = reviewID;
        this.phoneID = phoneID;
        this.customer = customer;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }
    public PrdRevDTO(productreview productreview, Customer customer) {
        this.reviewID = productreview.getReviewID();
        this.phoneID = productreview.getPhoneID();
        this.customerID = productreview.getCustomerID();
        this.customer = customer;
        this.rating = productreview.getRating();
        this.comment = productreview.getComment();
        this.reviewDate = productreview.getReviewDate();
    }
    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(int phoneID) {
        this.phoneID = phoneID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}
