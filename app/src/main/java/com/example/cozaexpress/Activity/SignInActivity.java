package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.cozaexpress.Adapter.StatusOrderAdapter;
import com.example.cozaexpress.Adapter.StatusSignInAdapter;
import com.example.cozaexpress.Fragment.CartFragment;
import com.example.cozaexpress.Fragment.LoginFragment;
import com.example.cozaexpress.Fragment.SignUpFragment;
import com.example.cozaexpress.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SignInActivity extends AppCompatActivity implements SignUpFragment.OnSignupCompleteListener {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    StatusSignInAdapter statusSignInAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        tabLayout = findViewById(R.id.tab_layout_login);
        viewPager2 = findViewById(R.id.viewPager2_status_login);

        statusSignInAdapter = new StatusSignInAdapter(this);
        viewPager2.setAdapter(statusSignInAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Đăng Nhập");
                    break;
                case 1:
                    tab.setText("Đăng Ký");
                    break;
            }
        }).attach();
    }

    public void navigateToMessageFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Tạo một instance của CartFragment
        LoginFragment cartFragment = new LoginFragment();

        // Thay thế Fragment hiện tại bằng CartFragment
        fragmentTransaction.replace(R.id.fragment_login, cartFragment);

        // Hoàn thành FragmentTransaction
        fragmentTransaction.commit();
    }

    @Override
    public void onSignupComplete() {
        // Chuyển đổi sang Fragment "Login"
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_login, new LoginFragment())
                .commit();

    }
}