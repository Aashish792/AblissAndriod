package com.example.dell.ablissadrad.data;

// Just a class to store loginresponse items

public class LoginResponse {

    private String model;
    private User fields;
    private  boolean error;



    public LoginResponse(String model, User fields, boolean error) {
        this.model = model;
        this.fields = fields;
        this.error = error;
    }

    public String getModel() {
        return model;
    }

    public User getFields() {
        return fields;
    }

    public boolean isError() {
        return error;
    }
}
