package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.cozaexpress.Adapter.ReviewAdapter;
import com.example.cozaexpress.Model.Review;
import com.example.cozaexpress.R;

import java.util.List;

public class ViewAllReviewsActivity extends AppCompatActivity {
    RecyclerView rc_seeAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_reviews);
        rc_seeAll = findViewById(R.id.rc_seeAll);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        List<Review> mList = (List<Review>) bundle.getSerializable(ProDetailActivity.DETAIL_REVIEW);
        ReviewAdapter reviewAdapter = new ReviewAdapter(ViewAllReviewsActivity.this, mList);
        rc_seeAll.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewAllReviewsActivity.this, LinearLayoutManager.VERTICAL, false);
        rc_seeAll.setLayoutManager(layoutManager);
        rc_seeAll.setAdapter(reviewAdapter);
        reviewAdapter.notifyDataSetChanged();
    }
}