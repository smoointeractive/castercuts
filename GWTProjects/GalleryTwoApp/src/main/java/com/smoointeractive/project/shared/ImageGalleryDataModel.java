package com.smoointeractive.project.shared;

import java.io.Serializable;

/**
 * Created by sachamoo on 4/24/17.
 */
public class ImageGalleryDataModel implements Serializable{
    private int id;
    private String name;
    private String description;
    private String thumbnail;
    private String imageurl;

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

    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImageurl() { return imageurl; }
    public void setImageurl(String imageurl) { this.imageurl = imageurl; }
}
