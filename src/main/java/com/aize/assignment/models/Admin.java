package com.aize.assignment.models;

import javax.persistence.*;

@Entity
public class Admin extends User{
    private String email;

    // lastlogin, settings

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
