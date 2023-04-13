package com.example.cozaexpress.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.cozaexpress.Database.UserDatabase;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.Model.iClickListener;
import com.example.cozaexpress.R;
import com.google.android.material.color.utilities.TonalPalette;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    EditText edtUserName, edtPassword;
    Button btnThem;
    RecyclerView rcUser;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        AnhXa();
//        userAdapter = new UserAdapter(new iClickListener() {
//            @Override
//            public void updateUser(User user) {
//                clickUpdateUser(user);
//            }
//        });
//        userList = new ArrayList<>();
        //lấy ds user trong room
//        userList = UserDatabase.getInstance(this).userDAO().getAll();
//        userAdapter.setData(userList);
//        rcUser.setHasFixedSize(true);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        rcUser.setLayoutManager(layoutManager);
//        rcUser.setAdapter(userAdapter);
//        btnThem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addUser();
//            }
//        });
    }

//    private void clickUpdateUser(User user) {
//
//        Intent intent = new Intent(UserActivity.this,UpdateUserActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("object name", user);
//        intent.putExtras(bundle);
//        mActivityResultLauncher.launch(intent);
//    }

//    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            Log.e("TAG", "onActivityResult");
//            if(result.getResultCode()== Activity.RESULT_OK){
//                Intent data = result.getData();
//                if(data == null){
//                    return;
//                }
//                loadData();
//            }
//
//        }
//    });

//    private void loadData() {
//        //lấy danh sách user trong room
//        userList = UserDatabase.getInstance(this).userDAO().getAll();
//        userAdapter.setData(userList);
//    }

//    private void addUser() {
//        String username = edtUserName.getText().toString().trim();
//        String password = edtPassword.getText().toString().trim();
//        if(TextUtils.isEmpty(username)|| TextUtils.isEmpty(password))
//        {
//            return;
//        }
//        User user = new User(username,password);
//        if(isCheckExist(user)){
//            Toast.makeText(this, "User đã tồn tại", Toast.LENGTH_LONG).show();
//        }
//        //add vào room
//        UserDatabase.getInstance(this).userDAO().insertAll(user);
//        Toast.makeText(this, "Thêm user thành công", Toast.LENGTH_LONG).show();
//        edtPassword.setText("");
//        edtUserName.setText("");
//        hideSoftKeyboard();
//
//        userList = UserDatabase.getInstance(this).userDAO().getAll();
//        userAdapter.setData(userList);
//
//    }

    private void hideSoftKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (NullPointerException ex)
        {
            ex.printStackTrace();
        }
    }

    private void AnhXa() {
        edtUserName = findViewById(R.id.edtNameUser);
        edtPassword = findViewById(R.id.edtPassUser);
        btnThem = findViewById(R.id.btnThem);
        rcUser = findViewById(R.id.rc_user_item);
    }
//    private boolean isCheckExist(@NotNull User user){
//        List<User> list = UserDatabase.getInstance(this).userDAO().checkUser(user.getUsername());
//        return list != null && !list.isEmpty();
//    }
}