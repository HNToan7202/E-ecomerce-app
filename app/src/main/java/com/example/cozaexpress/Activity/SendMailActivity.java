package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cozaexpress.Model.ForgotPasswordRequest;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.Response;

public class SendMailActivity extends AppCompatActivity {

    EditText tvSendMail;

    Button btnSendMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);

        tvSendMail = findViewById(R.id.edtSendMail);
        btnSendMail = findViewById(R.id.btnSendEmail);

        btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = tvSendMail.getText().toString();
                ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
                forgotPasswordRequest.setEmail(email);

                APIService.apiService.forgotPassword(email).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(SendMailActivity.this, "Gửi thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SendMailActivity.this, OTPActivity.class));
                        }
                        else {
                            Toast.makeText(SendMailActivity.this, "Gửi thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("TAG", "onFailure: " + t.getMessage());
                    }
                });
//                APIService.apiService.forgotPassword(email).enqueue(new Callback<String>() {
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        if(response.isSuccessful()){
//                            Toast.makeText(SendMailActivity.this, "Gửi thành công", Toast.LENGTH_SHORT).show();
//                        }
//                        else {
//                            Toast.makeText(SendMailActivity.this, "Gửi thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//
//                    }
//                });
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Đoạn mã được thực thi sau khi chờ 3 giây
                        Intent intent = new Intent(SendMailActivity.this, OTPActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("email", email);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish(); // Tùy chọn nếu bạn muốn đóng activity hiện tại
                    }
                }, 3000); // Thời gian chờ 3 giây (3000 milliseconds)
            }

        });




    }
}