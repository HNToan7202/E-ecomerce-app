package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;
import com.mukesh.OtpView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPActivity extends AppCompatActivity {

    TextView txtNumber;

    OtpView inputOtp;

    AppCompatButton btnContinue;

    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtNumber = findViewById(R.id.txtNumber);
        setContentView(R.layout.activity_otpactivity);
        inputOtp = findViewById(R.id.inputOtp);
        btnContinue = findViewById(R.id.btnContinue);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            email = bundle.getString("email");
        }

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                APIService.apiService.checkOTP(email, inputOtp.getText().toString()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.e("OTP", response.body());
                        if(response.body().equals("true")){
                            Intent intent = new Intent(OTPActivity.this, ResetPasswordActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("email", email);
                            bundle.putString("otp", inputOtp.getText().toString());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else {
                            inputOtp.setError("Wrong OTP");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("OTP", t.getMessage());
                    }
                });
//                String otp = inputOtp.getText().toString();
//                Intent intent = new Intent(OTPActivity.this, ResetPasswordActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("email", email);
//                bundle.putString("otp", otp);
//                intent.putExtras(bundle);
//                startActivity(intent);
            }
        });


    }
}