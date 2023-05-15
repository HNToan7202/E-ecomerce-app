package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Model.ChangePasswordRequest;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText password, newpassword;
    Button btnChange;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        password = findViewById(R.id.password);
        newpassword = findViewById(R.id.newpassword);
        btnChange = findViewById(R.id.btn_change_pass);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = SharedPrefManager.getInstance(ChangePasswordActivity.this).getUser();
                String pass = password.getText().toString();
                String newpass = newpassword.getText().toString();
                ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
                changePasswordRequest.setCurrentPassword(pass);
                changePasswordRequest.setNewPassword(newpass);
                changePasswordRequest.setUsername(user.getUsername());


                    APIService.apiService.changePassword(changePasswordRequest).enqueue(new Callback<ChangePasswordRequest>() {
                        @Override
                        public void onResponse(Call<ChangePasswordRequest> call, Response<ChangePasswordRequest> response) {
                            if(response.isSuccessful()){
                                Toasty.success(ChangePasswordActivity.this,"Đổi mật khẩu thành công. Vui lòng đăng nhập lại", Toasty.LENGTH_LONG).show();
                                SharedPrefManager.getInstance(ChangePasswordActivity.this).logout();
                                startActivity(new Intent(ChangePasswordActivity.this, MainActivity.class));
                                finish();

                            }
                        }

                        @Override
                        public void onFailure(Call<ChangePasswordRequest> call, Throwable t) {

                        }
                    });
                }

        });
    }
}