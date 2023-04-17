package com.example.cozaexpress.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cozaexpress.DataLocal.DataLocalManager;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView sing_up;
    EditText edtUsername,edtPassword;
    Button login;
    User user ;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sing_up = findViewById(R.id.sign_up_change);

//        AnhXa();
//        if(user!=null){
//            if(isCheckExist(user)){
//                finish();
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            }
//        }
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                GetUser();
//            }
//        });
        sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }






}