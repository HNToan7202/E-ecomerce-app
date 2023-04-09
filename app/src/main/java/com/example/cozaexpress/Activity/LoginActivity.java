package com.example.cozaexpress.Activity;

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
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView sing_up;
    EditText edtUsername,edtPassword;
    Button login;
    User user ;
    ProgressBar progressBar;

    TextView tvMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AnhXa();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetUser();
            }
        });
        sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void GetUser() {
        String username = edtUsername.getText().toString().trim();
        String password=  edtPassword.getText().toString().trim();


        //sử dụng MVC
        if(TextUtils.isEmpty(username)){
            edtUsername.setError("Please enter your username");
            edtUsername.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password)){
            edtPassword.setError("Please enter password");
            edtPassword.requestFocus();
            return;
        }

        APIService.apiService.loginWithLocal(username,password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                tvMessage.setVisibility(View.VISIBLE);
//                tvMessage.setText("Đăng Nhập Thành Công");
//                tvMessage.setTextColor(getResources().getColor(R.color.green_color));

                progressBar.setVisibility(View.VISIBLE);
                user = response.body();
                if(user != null){
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                    DataLocalManager.setUser(user);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
//                    tvMessage.setVisibility(View.VISIBLE);
//                    tvMessage.setText("Sai tên đăng nhập hoặc mật khẩu");
//                    tvMessage.setTextColor(getResources().getColor(R.color.red_color));
                    Toast.makeText(getApplicationContext(),"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("TAG",t.getMessage()+"");
//                tvMessage.setVisibility(View.VISIBLE);
//                tvMessage.setText("Sai tên đăng nhập hoặc mật khẩu");
                Toast.makeText(getApplicationContext(),"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void AnhXa() {
        sing_up = findViewById(R.id.sign_up_change);
        edtUsername = findViewById(R.id.username);
        edtPassword = findViewById(R.id.password);
        login = findViewById(R.id.loginBtn);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        tvMessage = findViewById(R.id.tvMessageLogin);
    }

}