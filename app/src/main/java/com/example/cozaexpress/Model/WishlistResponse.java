package com.example.cozaexpress.Model;

import java.io.Serializable;

public class WishlistResponse implements Serializable {
    String message;

    User user;

    Product product;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public WishlistResponse(String message, User user, Product product) {
        super();
        this.message = message;
        this.user = user;
        this.product = product;
    }

}
