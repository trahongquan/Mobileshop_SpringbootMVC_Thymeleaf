package com.springbootmvcwithentity.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "paymentmethods")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentMethodID")
    private Long paymentMethodID;

    @Column(name = "MethodName", nullable = false)
    private String methodName;

    // Constructors, getters, and setters


    public PaymentMethod(String methodName) {
        this.methodName = methodName;
    }

    public PaymentMethod() {
    }

    public Long getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setPaymentMethodID(Long paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "paymentMethodID=" + paymentMethodID +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
