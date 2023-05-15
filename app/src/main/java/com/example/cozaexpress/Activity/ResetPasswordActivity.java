package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cozaexpress.Model.ResetPasswordRequest;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText edtMail, edtNewPassword;

    Button btnResetPassword;

    String otp;

    ResetPasswordRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        edtMail = findViewById(R.id.edtMail);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            edtMail.setText(bundle.getString("email"));
            otp = bundle.getString("otp");
        }
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request = new ResetPasswordRequest();
                request.setEmail(edtMail.getText().toString());
                request.setOtp(otp);
                request.setNewPassword(edtNewPassword.getText().toString());


                APIService.apiService.resetPassword(request).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(ResetPasswordActivity.this, "Password reset successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ResetPasswordActivity.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ResetPasswordActivity.this, SignInActivity.class);
                        startActivity(intent);
                    }
                }, 2000);
            }
        });

    }
}