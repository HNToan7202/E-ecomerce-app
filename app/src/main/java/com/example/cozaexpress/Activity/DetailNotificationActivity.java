package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.cozaexpress.Adapter.DangGiaoAdapter;
import com.example.cozaexpress.Adapter.OrderItemAdapter;
import com.example.cozaexpress.Model.Order;
import com.example.cozaexpress.Model.OrderItem;
import com.example.cozaexpress.R;
import com.example.cozaexpress.StatusOrderFragment.DangGiaoFragment;
import com.example.cozaexpress.api.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailNotificationActivity extends AppCompatActivity {

    RecyclerView rc_order;

    List<OrderItem> orderItemList;

    OrderItemAdapter orderItemAdapter;

    TextView total_amt_tv,total_items_tv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notification);
        total_amt_tv = findViewById(R.id.total_amt_tv);
        total_items_tv = findViewById(R.id.total_items_tv);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Order order = (Order) bundle.getSerializable(DangGiaoAdapter.ORDER);
        rc_order = findViewById(R.id.rc_oderitemdetail);
        APIService.apiService.getOrderItemList(order.getId()).enqueue(new Callback<List<OrderItem>>() {
            @Override
            public void onResponse(Call<List<OrderItem>> call, Response<List<OrderItem>> response) {
                orderItemList = response.body();
                orderItemAdapter = new OrderItemAdapter(DetailNotificationActivity.this, orderItemList);
                rc_order.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(DetailNotificationActivity.this,1);
                rc_order.setLayoutManager(layoutManager);
                rc_order.setAdapter(orderItemAdapter);
                orderItemAdapter.notifyDataSetChanged();
                total_items_tv.setText("Count:" +orderItemList.size());
                total_amt_tv.setText(String.format( "%,.0f",order.getPrice())+ "đ");
            }

            @Override
            public void onFailure(Call<List<OrderItem>> call, Throwable t) {

            }
        });
    }
}