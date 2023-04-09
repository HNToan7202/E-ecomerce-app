package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cozaexpress.Database.UserDatabase;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;

public class UpdateUserActivity extends AppCompatActivity {

    private EditText edtUserName;
    private EditText edtPassword;

    private Button btnUpdate;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        AnhXa();
        user = (User) getIntent().getExtras().get("object name");
        if(user != null){
            edtUserName.setText(user.getUsername());
            edtPassword.setText(user.getPassword());
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });
    }

    private void AnhXa() {
        edtUserName = findViewById(R.id.edtUserUpdate);
        edtPassword = findViewById(R.id.edtPassUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);
    }
    private void updateUser() {
        String uName = edtUserName.getText().toString().trim();
        String uPass = edtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(uName) || TextUtils.isEmpty(uPass)){
            return;
        }
        user.setUsername(uName);
        user.setPassword(uPass);
        //UserDatabase.getInstance(this).userDAO().update(user);
        Toast.makeText(UpdateUserActivity.this, "Cập nhật thành công", Toast.LENGTH_LONG).show();
        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }

}
