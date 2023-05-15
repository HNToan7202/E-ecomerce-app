package com.example.cozaexpress.Model;

import java.io.Serializable;

public class ForgotPasswordRes implements Serializable {

    int statusCode; // 1 thành công 0 thất bại
    String message;

    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public ForgotPasswordRes(int statusCode, String message) {
        super();
        this.statusCode = statusCode;
        this.message = message;
    }

}
