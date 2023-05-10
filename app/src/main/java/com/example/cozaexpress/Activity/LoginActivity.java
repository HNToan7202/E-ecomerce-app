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
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Database.UserDatabase;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername,edtPassword;
    Button login;
    User user ;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();
        if(user!=null){
            if(isCheckExist(user)){
                finish();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetUser();
            }
        });
    }
    private void FirstInstall() {
        if(!DataLocalManager.getFirstInstall()){
            Toast.makeText(this, "First Install App", Toast.LENGTH_LONG).show();
            DataLocalManager.setFirstInstall(true);
        }
    }
    private boolean isCheckExist(@NonNull User user){
        List<User> list = UserDatabase.getInstance(this).usersDao().checkUser(user.getUsername());
        return list != null && !list.isEmpty();
    }

    private void GetUser() {
        String username = edtUsername.getText().toString().trim();
        String password=  edtPassword.getText().toString().trim();
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

                user = response.body();
                if(user != null){
                    SharedPrefManager.getInstance(getApplicationContext()).setUser(user);
                    //AddUser(user);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("TAG",t.getMessage()+"");
                Toast.makeText(getApplicationContext(),"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AddUser(User user ) {
        UserDatabase.getInstance(this).usersDao().insertUser(user);
    }


    private void AnhXa() {

//        List<User> userList =UserDatabase.getInstance(getApplicationContext()).usersDao().getAll();
//        if(!userList.isEmpty() && userList.size()>0){
//            user = UserDatabase.getInstance(getApplicationContext()).usersDao().getAll().get(0);
//        }
        edtUsername = findViewById(R.id.username);
        edtPassword = findViewById(R.id.password);
        login = findViewById(R.id.loginBtn);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

}