package com.example.cozaexpress.DAO;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class WishListModel implements Serializable {
    @PrimaryKey
    @NonNull
    private String id;

    //private User user;
    private String products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public void convertProductToString(List<Product> products){
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(products).getAsJsonArray(); //Tạo một json array
        String strJsonArray = jsonArray.toString(); //Chuyển json array thành string
        this.products = strJsonArray;

    }
    public List<Product> getStringToProduct(){

        List<Product> productList = new ArrayList<>();
        products = "";
        try {
            JSONArray jsonArray = new JSONArray(this.products);
            JSONObject jsonObject;
            Product p;
            //convert => list user
            Gson gson = new Gson();
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                p = gson.fromJson(jsonObject.toString(), Product.class);
                productList.add(p);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }
}
