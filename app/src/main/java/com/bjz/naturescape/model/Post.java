package com.bjz.naturescape.model;

/**
 * Created by bjz on 12/26/2017.
 */

public class Post {
    private int id;
    private boolean addedToFavourite;
    private String title;
    private String author;
    private String image;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAddedToFavourite() {
        return addedToFavourite;
    }

    public void setAddedToFavourite(boolean addedToFavourite) {
        this.addedToFavourite = addedToFavourite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
