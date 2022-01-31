package com.aize.assignment.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Customer extends User{

    private String paymentCode;

    @OneToOne
    private Address address;

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPaymentCode() {
        return paymentCode;
    }
}