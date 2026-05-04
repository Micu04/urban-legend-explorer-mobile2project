package com.example.urbanlegendexplorer.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "legends")
public class Legend {

    @PrimaryKey
    @NonNull
    private String id;

    private String title;
    private LegendCategory categoryType;
    private String location;
    private String description;
    private String imageUrl;
    private long createdAt;

    public Legend() {
        this.id = "";
    }

    public Legend(@NonNull String id, String title, LegendCategory categoryType,
                  String location, String description, String imageUrl, long createdAt) {
        this.id = id;
        this.title = title;
        this.categoryType = categoryType;
        this.location = location;
        this.description = description;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LegendCategory getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(LegendCategory categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategory() {
        return categoryType != null ? categoryType.name() : LegendCategory.OTHER.name();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}