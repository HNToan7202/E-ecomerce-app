package com.example.cozaexpress.Model;

import java.util.List;

public class UserWishlist {
    String message;
    List<Product> list;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<Product> getList() {
        return list;
    }
    public void setList(List<Product> list) {
        this.list = list;
    }
    public UserWishlist(String message, List<Product> list) {
        super();
        this.message = message;
        this.list = list;
    }

}
