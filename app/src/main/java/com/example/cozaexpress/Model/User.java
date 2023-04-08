package com.example.cozaexpress.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cozaexpress.Model.Store;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "user")
public class User implements Serializable {
    @PrimaryKey()
    @NonNull
    private String id;
    private String firstName = "Nguyen A";
    private String lastName = "A";
    private String email = "user@gamil.com";
    private String phone = "0123456789";
    private String username ="user01";
    private String password;// mật khẩu mã hóa
    private String address = "123 Nguyen";
    private String avatar;
    private String role;
    private Boolean isActive;
    private String resetpasswordtoken;

    private String storeId;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String string, String string1, String string2, String string3, String string4, String string5, String string6, String string7, String string8) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getResetpasswordtoken() {
        return resetpasswordtoken;
    }

    public void setResetpasswordtoken(String resetpasswordtoken) {
        this.resetpasswordtoken = resetpasswordtoken;
    }

//    public Date getCreateat() {
//        return createat;
//    }
//
//    public void setCreateat(Date createat) {
//        this.createat = createat;
//    }
//
//    public Date getUpdateat() {
//        return updateat;
//    }
//
//    public void setUpdateat(Date updateat) {
//        this.updateat = updateat;
//    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
