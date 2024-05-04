package com.example.finalproject_newsapplication;

import java.util.ArrayList;

public class DataModel {
    private String status;
    private int totalResults;

    private ArrayList<Article> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }
}
