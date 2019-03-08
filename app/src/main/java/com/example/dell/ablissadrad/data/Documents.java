package com.example.dell.ablissadrad.data;

// Just a class to create constructor of activity items

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Documents {


    private String doc_category;
    @SerializedName("title")
    private String title;
    private String date;
    private String category;
    private String description;
    private String document;
    private String information;

    public Documents(String doc_category, String title, String date, String category, String description, String document, String information) {
        this.doc_category = doc_category;
        this.title = title;
        this.date = date;
        this.category = category;
        this.description = description;
        this.document = document;
        this.information = information;
    }

    public String getDoc_category() {
        return doc_category;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getDocument() {
        return document;
    }

    public String getInformation() {
        return information;
    }
}
