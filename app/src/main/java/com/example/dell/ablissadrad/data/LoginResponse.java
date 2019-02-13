package com.example.dell.ablissadrad.data;

public class LoginResponse {

    private String model;
    private User fields;


    public LoginResponse(String model, User fields) {
        this.model = model;
        this.fields = fields;
    }

    public String getModel() {
        return model;
    }

    public User getFields() {
        return fields;
    }
}
