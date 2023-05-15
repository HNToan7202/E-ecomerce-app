package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cozaexpress.Adapter.ReviewsAdapter;
import com.example.cozaexpress.Adapter.StatusOrderAdapter;
import com.example.cozaexpress.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ReviewsActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    ImageView btnBackHome;

    ReviewsAdapter reviewsAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        tabLayout = findViewById(R.id.tab_layout_review);
        viewPager2 = findViewById(R.id.viewPager2_status_reviews);
        btnBackHome = findViewById(R.id.btnBackHome);

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        reviewsAdapter = new ReviewsAdapter(this);
        viewPager2.setAdapter(reviewsAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Đã Đánh Giá");
                    break;
                case 1:
                    tab.setText("Chưa Đánh Giá");
                    break;
            }
        }).attach();
    }
}