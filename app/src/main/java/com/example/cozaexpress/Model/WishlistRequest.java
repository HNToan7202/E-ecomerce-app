package com.example.cozaexpress.Model;

import java.io.Serializable;

public class WishlistRequest implements Serializable {
    private User user;
    private Product product;
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
}
