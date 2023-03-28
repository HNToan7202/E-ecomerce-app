package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.DataLocal.DataLocalManager;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    TextView tvId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FirstInstall();
        AnhXa();
        User user = DataLocalManager.getUser();
        if(user != null)
        {
            tvId.setText(user.toString());
        }
    }

    private void AnhXa() {
        tvId = findViewById(R.id.tvId);
    }

    private void BindingUsers() {

//        Set<String> nameUsers = new HashSet<>();
//        nameUsers.add("Toàn");
//        nameUsers.add("Huy");
//        DataLocalManager.setNameUsers(nameUsers); //put mảng nameusers vào preference

    }

    private void FirstInstall() {
        if(!DataLocalManager.getFirstInstall()){
            Toast.makeText(this, "First Install App", Toast.LENGTH_LONG).show();
            DataLocalManager.setFirstInstall(true);
        }
    }
}