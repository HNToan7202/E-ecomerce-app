package com.example.cozaexpress.DataLocal;

import android.content.Context;

import com.example.cozaexpress.Model.User;
import com.google.gson.Gson;

import java.util.Set;

//Quản lý xem sẽ lưu gì vào sharedpreferenecs
public class DataLocalManager {
    private static final String PRE_FIRST_INSTALL = "PRE_FIRST_INSTALL";
    private static final String PRE_NAME_USER = "PRE_NAME_USER";
    private static final String PRE_OBJECT_USER = "PRE_OBJECT_USER";
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

    public static void setNameUsers(Set<String> nameUsers){
        DataLocalManager.getInstance().mySharedPreferences.putStringSetValue(PRE_NAME_USER, nameUsers);
    }

    public static Set<String> getNameUsers(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringSetVaule(PRE_NAME_USER);
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
}
