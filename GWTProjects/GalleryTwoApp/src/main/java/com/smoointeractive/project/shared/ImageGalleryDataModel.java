package com.smoointeractive.project.shared;

import java.io.Serializable;

/**
 * Created by sachamoo on 4/24/17.
 */
public class ImageGalleryDataModel implements Serializable{
    private int id;
    private String name;
    private String description;
    private String photo;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
