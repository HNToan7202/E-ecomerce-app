package com.example.cozaexpress.Model;

import androidx.room.Entity;

import java.io.Serializable;
import java.util.List;

public class Wishlist implements Serializable {
    private String id;
    private User user;
    private List<Product> products;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

}
