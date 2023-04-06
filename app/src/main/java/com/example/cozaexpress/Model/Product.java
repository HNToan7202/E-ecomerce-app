package com.example.cozaexpress.Model;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    private String id;
    private String name;
    private String desciption;
    private Double price;
    private Double promotionaprice;
    private Integer quantity;
    private Integer sold;
    private Boolean isselling;
    private String listimage;
    private Category category;
    private Store store;
    private Integer rating;
    private Date createat;
    private Date updateat;
    private String barcode;

    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
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
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Store getStore() {
        return store;
    }
    public void setStore(Store store) {
        this.store = store;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public Date getCreateat() {
        return createat;
    }
    public void setCreateat(Date createat) {
        this.createat = createat;
    }
    public Date getUpdateat() {
        return updateat;
    }
    public void setUpdateat(Date updateat) {
        this.updateat = updateat;
    }
}