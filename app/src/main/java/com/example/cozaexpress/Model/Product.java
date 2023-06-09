package com.example.cozaexpress.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity(tableName = "Product")
public class Product implements Serializable {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String desciption;
    private Double price;
    private Double promotionaprice;
    private Integer quantity;
    private Integer sold;
    private Boolean isselling;
    private String listimage;
    private String categoryId;
    private Integer rating;
    private String createat;
    private String updateat;
    private String barcode;

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

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPromotionaprice() {
        return promotionaprice;
    }

    public void setPromotionaprice(Double promotionaprice) {
        this.promotionaprice = promotionaprice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Boolean getIsselling() {
        return isselling;
    }

    public void setIsselling(Boolean isselling) {
        this.isselling = isselling;
    }

    public String getListimage() {
        return listimage;
    }

    public void setListimage(String listimage) {
        this.listimage = listimage;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
