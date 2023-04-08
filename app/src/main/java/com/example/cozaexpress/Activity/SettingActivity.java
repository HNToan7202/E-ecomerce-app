package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.R;

public class SettingActivity extends AppCompatActivity {
    ImageView btnBackAccount;

    AppCompatButton btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btnBackAccount = findViewById(R.id.btnBackAccount);
//        btnLogOut = findViewById(R.id.btnLogOut);
        btnBackAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        btnLogOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPrefManager.getInstance(getApplicationContext()).logout();
//            }
//        });

    }
}