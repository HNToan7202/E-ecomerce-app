package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cozaexpress.Adapter.AddressAdapter;
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Model.Delivery;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShipperActivity extends AppCompatActivity {

    RecyclerView rc_address;
    List<Delivery> deliveries;

    AddressAdapter addressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper);
        rc_address = findViewById(R.id.rc_address);

        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        APIService.apiService.getDeliveryByUser(user).enqueue(new Callback<List<Delivery>>() {
            @Override
            public void onResponse(Call<List<Delivery>> call, Response<List<Delivery>> response) {
                deliveries = response.body();
                addressAdapter = new AddressAdapter(deliveries , ShipperActivity.this);
                rc_address.setHasFixedSize(false);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShipperActivity.this, RecyclerView.HORIZONTAL, false);
                rc_address.setLayoutManager(layoutManager);
                rc_address.setAdapter(addressAdapter);
                addressAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Delivery>> call, Throwable t) {

            }
        });
    }
}