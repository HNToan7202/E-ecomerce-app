package com.example.cozaexpress.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.example.cozaexpress.Adapter.StatusOrderAdapter;
import com.example.cozaexpress.Model.StatusOrder;
import com.example.cozaexpress.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class StatusOrderActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    
    StatusOrderAdapter statusOrderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_order);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        StatusOrder st = StatusOrder.CHOXACNHAN;
        //StatusOrder statusOrder = StatusOrder.valueOf(bundle.getString("status"));


        tabLayout = findViewById(R.id.tab_layout_order);
        viewPager2 = findViewById(R.id.viewPager2_status_order);
        statusOrderAdapter = new StatusOrderAdapter(this);
        viewPager2.setAdapter(statusOrderAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Chờ xác nhận");
                    break;
                case 1:
                    tab.setText("Đang giao");
                    break;
                case 2:
                    tab.setText("Thành công");
                    break;
                case 3:
                    tab.setText("Bị hủy");
                    break;
            }
        }).attach();

    }
}