package com.springbootmvcwithentity.demo.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "productreviews")
public class productreview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewID")
    private int reviewID;

    @Column(name = "PhoneID")
    private int phoneID;

    @Column(name = "CustomerID")
    private int customerID;

    @Column(name = "Rating")
    private int rating;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "ReviewDate")
    private String reviewDate;


    /****************************************************/

    public productreview() {
    }

    public productreview(int phoneID, int customerID, int rating, String comment, String reviewDate) {
        this.phoneID = phoneID;
        this.customerID = customerID;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
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

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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
