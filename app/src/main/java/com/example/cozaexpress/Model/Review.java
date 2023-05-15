package com.example.cozaexpress.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Review implements Serializable {

    private String id;
    private User user;
    private Product product;
    private String content;
    private Integer rating;
    private String createat;
    private String updateat;

    private String listimage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCreateat() {
        return createat;
    }

    public void setCreateat(String createat) {
        this.createat = createat;
    }

    public String getUpdateat() {
        return updateat;
    }

    public void setUpdateat(String updateat) {
        this.updateat = updateat;
    }

    public String getListimage() {
        return listimage;
    }

    public void setListimage(String listimage) {
        this.listimage = listimage;
    }

    public List<Photo> getListPhoto() {

        String listimg = getListimage();
        List<String> photos = Arrays.asList(listimg.split(","));
        List<Photo> photoList = new ArrayList<>();
        for(String w:photos){
            Photo photo = new Photo(w);
            photoList.add(photo);
        }
        return photoList;
    }
}
