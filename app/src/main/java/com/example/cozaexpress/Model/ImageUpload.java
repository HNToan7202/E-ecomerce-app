package com.example.material95.Model;

public class ImageUpload {
    private int id;
    private String username;
    private String avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public ImageUpload() {
    }

    public ImageUpload(int id, String username, String avatar) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
    }
}
