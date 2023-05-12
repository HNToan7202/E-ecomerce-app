package com.example.cozaexpress.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.cozaexpress.Activity.LoginActivity;
import com.example.cozaexpress.Activity.MainActivity;
import com.example.cozaexpress.Activity.SignInActivity;
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    View view;

    EditText edtUsername,edtPassword;
    Button login;

    User user ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_login, container, false);
        edtUsername = view.findViewById(R.id.etUsername);
        edtPassword = view.findViewById(R.id.etPassword);
        login = view.findViewById(R.id.btnSignIn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetUser();
            }
        });
        return view;
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
                    SharedPrefManager.getInstance(getContext()).setUser(user);
                    //AddUser(user);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                else {
                    Toast.makeText(getContext(),"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("TAG",t.getMessage()+"");
                Toast.makeText(getContext(),"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_SHORT).show();
            }
        });
    }
}