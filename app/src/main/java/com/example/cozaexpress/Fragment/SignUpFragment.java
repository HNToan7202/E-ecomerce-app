package com.example.cozaexpress.Fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cozaexpress.Activity.SignInActivity;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;
import com.hbb20.CountryCodePicker;
import com.kaopiz.kprogresshud.KProgressHUD;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFragment extends Fragment {

    EditText edtUsername,edtPassword,edtEmail,edtPhone,edtConfirmPassword, edtFullName;

    String username,password,email,phone,confirmPassword, fullName;
    com.rilixtech.widget.countrycodepicker.CountryCodePicker ccp;

    Button register_btn;

    private String check;

    private KProgressHUD progressDialog;

    private OnSignupCompleteListener listener;

    int check1 = 0;

    // ...

    // Gọi phương thức này khi người dùng hoàn thành đăng ký
    private void onSignupComplete() {
        if (listener != null) {
            listener.onSignupComplete();
        }
    }

    // Interface để giao tiếp với Activity
    public interface OnSignupCompleteListener {
        void onSignupComplete();
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signup, container, false);
        AnhXa();
        return view;
    }

    private void AnhXa() {
        // Inflate the layout for this fragment
        edtUsername = view.findViewById(R.id.edtUsername);
        edtPassword = view.findViewById(R.id.etPassword);
        edtEmail = view.findViewById(R.id.etEmail);
        edtPhone = view.findViewById(R.id.etPhone);
        edtConfirmPassword = view.findViewById(R.id.etConfPassword);
        edtFullName = view.findViewById(R.id.etFullName);
        ccp = view.findViewById(R.id.ccp);
        register_btn = view.findViewById(R.id.btnRegister);


//        edtUsername.addTextChangedListener(usernameWatcher);
//        edtEmail.addTextChangedListener(emailWatcher);
//        edtPhone.addTextChangedListener(numberWatcher);
//        edtFullName.addTextChangedListener(nameWatcher);
//        edtPassword.addTextChangedListener(passwordWatcher);
//        edtConfirmPassword.addTextChangedListener(passwordWatcher);


        register_btn.setOnClickListener(view -> {
            if (validateName() && validateFullName() && validateEmail() && validateNumber() && validatePassword() && validateConfirmPassword()) {
                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();
                email = edtEmail.getText().toString();
                phone = edtPhone.getText().toString();
                fullName = edtFullName.getText().toString();
                confirmPassword = edtConfirmPassword.getText().toString();

                User user = new User(fullName, email, username, password, phone);
                user.setAvatar("");

                progressDialog  =  KProgressHUD.create(getContext())
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
                APIService.apiService.addUser(user).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        progressDialog.dismiss();
                        if (response.isSuccessful()) {
                            Log.d(TAG+"_TXN","2. ACCOUNT CREATED");

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG+"_SS", "createUserWithEmail:success");
                            Toasty.success(getContext(),"Đăng Ký Thành Công",Toast.LENGTH_LONG,true).show();
                            clear();
                            check1 = 1;
                            if(check1 == 1){
                                SignInActivity signInActivity = (SignInActivity) getActivity();
                                signInActivity.navigateToMessageFragment();
                            }
                            Log.e(" CHECK", "AnhXa: "+ check1);
                            Log.d("TAG", "onResponse: " + response.body().toString());
                        }
                        else{
                            progressDialog.dismiss();
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG+"_ERR", "createUserWithEmail:failure", task.getException());
                            Toasty.error(getContext(),"Failed to Register", Toast.LENGTH_LONG,true).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.w(TAG+"_ERR", "Error: "+ t.getMessage());
                        Toasty.error(getContext(),"Failed to Register",Toast.LENGTH_LONG,true).show();
                    }
                });


            }
        });
    }

    private boolean validateName() {

        check = edtUsername.getText().toString();

        return !(check.length() < 4 || check.length() > 20);

    }

    private boolean validateFullName() {

        check = edtFullName.getText().toString();

        return !(check.length() < 4 || check.length() > 20);

    }

    TextWatcher nameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (check.length() < 4 || check.length() > 20) {
                edtFullName.setError("Tên phải từ 4 đến 20 ký tự");
            }
        }

    };

    TextWatcher usernameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (check.length() < 4 || check.length() > 20) {
                edtUsername.setError("Tên phải từ 4 đến 20 ký tự");
            }
        }

    };

    TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (check.length() < 4 || check.length() > 20) {
                edtUsername.setError("Tên phải từ 4 đến 20 ký tự");
            }
        }

    };

    //TextWatcher for Email -----------------------------------------------------

    TextWatcher emailWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (check.length() < 4 || check.length() > 40) {
                edtEmail.setError("Địa chỉ email phải có từ 4 đến 40 ký tự");
            } else if (!check.matches("^[A-za-z0-9.@]+")) {
                edtEmail.setError("Chỉ cho phép . và @");
            } else if (!check.contains("@") || !check.contains(".")) {
                edtEmail.setError("Email không hợp lệ");
            }

        }

    };

    //TextWatcher for Mobile -----------------------------------------------------

    TextWatcher numberWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (check.length()>10) {
                edtPhone.setError("Số điện thoại không được quá 10 số");
            }else if(check.length()<10){
                edtPhone.setError("Số điện thoại phải đủ 10 số");
            }
        }

    };


    private boolean validateNumber() {

        check = edtPhone.getText().toString();
        Log.e("inside number",check.length()+" ");
        if (check.length()>10) {
            return false;
        }else if(check.length()<10){
            return false;
        }
        return true;
    }

    private boolean validatePassword() {

        check = edtPassword.getText().toString();

        return !(check.length() < 4 || check.length() > 20);

    }

    private boolean validateConfirmPassword() {

        check = edtConfirmPassword.getText().toString();

        return !(check.length() < 6 || check.length() > 20);

    }


    private boolean validateEmail() {

        check = edtEmail.getText().toString();

        if (check.length() < 4 || check.length() > 40) {
            return false;
        } else if (!check.matches("^[A-za-z0-9.@]+")) {
            return false;
        } else if (!check.contains("@") || !check.contains(".")) {
            return false;
        }

        return true;
    }
    private void clear(){
        edtUsername.setText("");
        edtFullName.setText("");
        edtEmail.setText("");
        edtPhone.setText("");
        edtPassword.setText("");
        edtConfirmPassword.setText("");
    }

}