package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    AppCompatButton btnUpdate, btn_change_password;

    ImageView anh_acount;

    TextView account_fullname, tv_account_username, tv_account_email,tv_account_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AnhXa();
    }

    private void AnhXa() {
        btn_change_password = findViewById(R.id.btn_change_password);
        btn_change_password.setOnClickListener(this);
        btnUpdate = findViewById(R.id.btn_update_setting);
        btnUpdate.setOnClickListener(this);
        anh_acount = findViewById(R.id.anh_acount);
        account_fullname = findViewById(R.id.account_fullname);
        tv_account_username = findViewById(R.id.tv_account_username);
        tv_account_email = findViewById(R.id.tv_account_email);
        tv_account_phone = findViewById(R.id.tv_account_phone);
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        account_fullname.setText(user.getFullName());
        tv_account_email.setText(user.getEmail());
        tv_account_phone.setText(user.getPhone());
        tv_account_username.setText(user.getUsername());
        Glide.with(getApplicationContext()).load(user.getAvatar()).into(anh_acount);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_update_setting:
                Intent i = new Intent(ProfileActivity.this, SettingActivity.class);
                startActivity(i);
                break;
            case R.id.btn_change_password:
                Intent intent = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                break;

        }

    }
}