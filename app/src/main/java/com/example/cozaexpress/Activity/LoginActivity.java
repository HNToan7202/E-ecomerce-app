package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.DataLocal.DataLocalManager;

import java.util.HashSet;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Set<String> nameUsers = new HashSet<>();
//        nameUsers = DataLocalManager.getNameUsers();
//
//        TextView tvName = findViewById(R.id.tvNameUsers);
//        tvName.setText(nameUsers.toString());
        User user = new User(1,"Toan", "toan@gmail.com", "Nam", "");
        DataLocalManager.setUser(user);

        Button btnGoToMain = findViewById(R.id.btnLogin);
        btnGoToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
}