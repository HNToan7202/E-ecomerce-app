package com.example.cozaexpress.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cozaexpress.Adapter.PhotoAdapter;
import com.example.cozaexpress.Adapter.ViewPagerAdapter;
import com.example.cozaexpress.Model.Photo;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.DataLocal.DataLocalManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView mBottom_nav;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FirstInstall();
        AnhXa();
        setUpViewPager();
        setUpNavigationView();


    }

    private void setUpNavigationView() {
        mBottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.action_mall:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.action_live:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.action_notification:
                        mViewPager.setCurrentItem(3);
                        break;
                    case R.id.action_account:
                        mViewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }

    private void AnhXa() {
        mBottom_nav = findViewById(R.id.bottom_nav);
        mViewPager = findViewById(R.id.viewPager);

    }

    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //sự kiện chuyển page khi xử lý page
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0 :
                        mBottom_nav.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1 :
                        mBottom_nav.getMenu().findItem(R.id.action_mall).setChecked(true);
                        break;
                    case 2 :
                        mBottom_nav.getMenu().findItem(R.id.action_live).setChecked(true);
                        break;
                    case 3 :
                        mBottom_nav.getMenu().findItem(R.id.action_notification).setChecked(true);
                        break;
                    case 4 :
                        mBottom_nav.getMenu().findItem(R.id.action_account).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void FirstInstall() {
        if(!DataLocalManager.getFirstInstall()){
            Toast.makeText(this, "First Install App", Toast.LENGTH_LONG).show();
            DataLocalManager.setFirstInstall(true);
        }
    }
}