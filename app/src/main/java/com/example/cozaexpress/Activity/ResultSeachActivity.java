package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cozaexpress.R;

public class ResultSeachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_seach);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String key = bundle.getString("key");
    }

}