package com.example.projetobrecho20.model;

import androidx.annotation.NonNull;

public class Customer {
    private final String id;
    private final String name;
    private final String instagram_user;
    private final String email;

    public Customer(String id, String name, String instagram_user, String email) {
        this.id = id;
        this.name = name;
        this.instagram_user = instagram_user;
        this.email = email;
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getInstagram_user() {
        return instagram_user;
    }


    public String getEmail() {
        return email;
    }

    @NonNull
    @Override
    public String toString() {
        return getInstagram_user();
    }
}
