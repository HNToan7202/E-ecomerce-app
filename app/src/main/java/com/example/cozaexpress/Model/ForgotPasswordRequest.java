package com.example.cozaexpress.Model;

import java.io.Serializable;

public class ForgotPasswordRequest implements Serializable {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
