package com.example.cozaexpress.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cozaexpress.Adapter.OrderItemAdapter;
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Model.OrderItem;
import com.example.cozaexpress.Model.Review;
import com.example.cozaexpress.Model.StatusOrder;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChuaDanhGia extends Fragment {

    View view;

    RecyclerView rc_order;

    OrderItemAdapter orderItemAdapter;

    List<OrderItem> listOrderItem;

    List<Review> rev;

    List<OrderItem> daDanhGia = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dadanhgia, container, false);

        rc_order = view.findViewById(R.id.rc_order_review_yet);

        User user = SharedPrefManager.getInstance(getContext()).getUser();

        //APIService.apiService.

        APIService.apiService.getOrderByUserNotReview(user).enqueue(new Callback<List<OrderItem>>() {
            @Override
            public void onResponse(Call<List<OrderItem>> call, Response<List<OrderItem>> response) {
                listOrderItem = response.body();

                for(OrderItem orderItem: listOrderItem){
                    if(orderItem.getOrder().getStatusOrder() == StatusOrder.DAGIAO){
                        daDanhGia.add(orderItem);
                    }
                }

                orderItemAdapter = new OrderItemAdapter(getContext(), daDanhGia);
                rc_order.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
                rc_order.setLayoutManager(layoutManager);
                rc_order.setAdapter(orderItemAdapter);
                orderItemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<OrderItem>> call, Throwable t) {

            }
        });


        return view;
    }

}
