package com.example.finalproject_newsapplication;

public class Article {
    private  String author;
    private String content;
    private String description;
    private String publishedAt;
    private Source source;
    private String title;

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public Source getSource() {
        return source;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getTitle() {
        return title;
    }

    private String url;
    private String urlToImage;
}
