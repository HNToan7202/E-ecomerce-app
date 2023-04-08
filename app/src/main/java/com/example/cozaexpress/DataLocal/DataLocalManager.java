package com.example.cozaexpress.DataLocal;

import android.content.Context;
import android.util.Log;

import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//Quản lý xem sẽ lưu gì vào sharedpreferenecs
public class DataLocalManager {
    private static final String PRE_FIRST_INSTALL = "PRE_FIRST_INSTALL";
    private static final String PRE_NAME_USER = "PRE_NAME_USER";
    private static final String PRE_OBJECT_USER = "PRE_OBJECT_USER";
    private static final String PRE_LIST_USER = "PRE_LIST_USER";

    private static final String PRE_LIST_PRODUCT = "PRE_LIST_PRODUCT";

    private static final String PRE_LIST_ITEM_CART = "PRE_LIST_ITEM_CART";
    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;
    public  static void init(Context context){
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }
    public static DataLocalManager getInstance(){
        if(instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }
    //Set lần đăng nhập đầu tiên
    public static void setFirstInstall(boolean isFirst){
        DataLocalManager.getInstance().mySharedPreferences.putBooleanValue(PRE_FIRST_INSTALL, isFirst);
    }

    //kiểm tra lần đăng nhập đầu tiên
    public static boolean getFirstInstall(){
        return DataLocalManager.getInstance().mySharedPreferences.getBooleanVaule(PRE_FIRST_INSTALL);
    }

    public static void setNameUsers(Set<String> nameProducts){
        DataLocalManager.getInstance().mySharedPreferences.putStringSetValue(PRE_LIST_PRODUCT, nameProducts);
    }

    public static Set<String> getNameProduct(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringSetVaule(PRE_LIST_PRODUCT);
    }

    //Lưu user vào datalocal
    public static void setUser(User user){
        Gson gson = new Gson();
        String strJsonUser = gson.toJson(user);
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PRE_OBJECT_USER, strJsonUser);
    }

    //Lấy user từ datalocal
    public static User getUser(){
        String strJsonUser = DataLocalManager.getInstance().mySharedPreferences.getStringVaule(PRE_OBJECT_USER);
        Gson gson = new Gson();
        User user = gson.fromJson(strJsonUser, User.class);
        return user;
    }

    public static void setListProduct(List<Product> listProduct){
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(listProduct).getAsJsonArray(); //Tạo một json array
        String strJsonArray = jsonArray.toString();
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PRE_LIST_ITEM_CART, strJsonArray);
    }

    //Add item mới vào cart
    public static void setItemCart(Product product){
        List<Product> productList = DataLocalManager.getListProduct();
        productList.add(product);
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(productList).getAsJsonArray(); //Tạo một json array
        String strJsonArray = jsonArray.toString();
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PRE_LIST_ITEM_CART, strJsonArray);
    }

    public static List<Product> getListProduct(){
        String strJsonArray = DataLocalManager.getInstance()
                .mySharedPreferences
                .getStringVaule(PRE_LIST_ITEM_CART); //lấy chuỗi json từ local
        List<Product> productList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(strJsonArray);
            JSONObject jsonObject;
            Product product;
            //convert => list user
            Gson gson = new Gson();
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                product = gson.fromJson(jsonObject.toString(), Product.class);
                productList.add(product);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public static void setListUser(List<User> listUser){
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(listUser).getAsJsonArray(); //Tạo một json array
        String strJsonArray = jsonArray.toString();
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PRE_LIST_USER, strJsonArray);
    }


    public static List<User> getListUser(){
        String strJsonArray = DataLocalManager.getInstance()
                .mySharedPreferences
                .getStringVaule(PRE_LIST_USER); //lấy chuỗi json từ local
        List<User> userList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(strJsonArray);
            JSONObject jsonObject;
            User user;
            //convert => list user
            Gson gson = new Gson();
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                user = gson.fromJson(jsonObject.toString(), User.class);
                userList.add(user);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
}
