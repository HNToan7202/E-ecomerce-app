package com.example.cozaexpress.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cozaexpress.Adapter.OrderItemAdapter;
import com.example.cozaexpress.Model.Order;
import com.example.cozaexpress.Model.OrderItem;
import com.example.cozaexpress.Model.StatusOrder;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderItemActivity extends AppCompatActivity {

    String id, Status;
    OrderItemAdapter orderItemAdapter;
    RecyclerView rcOrderItem;
    List<OrderItem> listOrderItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_item);
        AnhXa();
    }

    private void ChapNhanOrder() {
        APIService.apiService.changeStatuss(id,StatusOrder.DANGGIAO).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.isSuccessful()){
                    Toast.makeText(OrderItemActivity.this, "Đã chấp nhận các đơn hàng trên", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(OrderDetailActivity.this, MainActivity.class);
//                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(OrderItemActivity.this, "Có lỗi ", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void TuChoiOrder() {
        APIService.apiService.changeStatuss(id,StatusOrder.TUCHOI).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.isSuccessful()){
                    Toast.makeText(OrderItemActivity.this, "Đã từ chối các đơn hàng trên", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(OrderDetailActivity.this, MainActivity.class);
//                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(OrderItemActivity.this, "Có lỗi ", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void AnhXa() {
        rcOrderItem = findViewById(R.id.rc_OrderItemList);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        //kiểm tra xem có lấy đc ko
        if (bundle != null) {
            id = bundle.getString("OrderId");
            Status = bundle.getString("Status");
            Log.d("TAG", "AnhXa: "+Status);
            Log.d("TAG", "ID: "+id);
            getOrderItem(id);
        }
    }

    private void getOrderItem(String id) {
        APIService.apiService.getOrderItemList(id).enqueue(new Callback<List<OrderItem>>() {
            @Override
            public void onResponse(Call<List<OrderItem>> call, Response<List<OrderItem>> response) {
                if(response.isSuccessful()){
                    Log.e("TAG","IDSUCCES"+ id);
                    listOrderItem = response.body();
                    orderItemAdapter = new OrderItemAdapter(OrderItemActivity.this, listOrderItem);
                    rcOrderItem.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(OrderItemActivity.this,1);
                    rcOrderItem.setLayoutManager(layoutManager);
                    rcOrderItem.setAdapter(orderItemAdapter);
                    orderItemAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<OrderItem>> call, Throwable t) {
                Log.e("TAG","IDFAIL"+ id);
            }
        });
    }
}