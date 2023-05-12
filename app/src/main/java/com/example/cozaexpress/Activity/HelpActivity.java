package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;

import com.example.cozaexpress.R;
import com.example.cozaexpress.Utils.EmailUtil;
import com.example.cozaexpress.Utils.PhoneUtil;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener{

    AppCompatButton btnSendEmail, btnCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        btnSendEmail = findViewById(R.id.btnSendEmail);
        btnCall = findViewById(R.id.btnCall);

        btnSendEmail.setOnClickListener(this);
        btnCall.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSendEmail:
                // Gọi phương thức để gửi email
                EmailUtil.sendEmail(HelpActivity.this, "toannguyen7202@gmail.com", "Help with Coza", "Body");
                break;
            case R.id.btnCall:
                // Gọi phương thức để thực hiện cuộc gọi điện
                PhoneUtil.makePhoneCall(HelpActivity.this, "0559720250");
                break;
        }
    }
}