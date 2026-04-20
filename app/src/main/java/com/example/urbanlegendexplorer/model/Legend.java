package com.example.urbanlegendexplorer.model;

public class Legend {
    private String id;
    private String title;
    private String category;
    private String location;
    private String description;
    private String imageUrl;
    private long createdAt;

    public Legend() {
    }

    public Legend(String id, String title, String category, String location, String description, String imageUrl, long createdAt) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.location = location;
        this.description = description;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }
}