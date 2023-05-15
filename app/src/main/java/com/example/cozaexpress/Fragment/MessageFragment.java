package com.example.cozaexpress.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cozaexpress.Activity.NotifiActivity;
import com.example.cozaexpress.Adapter.Category1Adapter;
import com.example.cozaexpress.Adapter.CategoryAdapter;
import com.example.cozaexpress.Adapter.DangGiaoAdapter;
import com.example.cozaexpress.Adapter.LastProductAdapter;
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Model.Category;
import com.example.cozaexpress.Model.Order;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.ResponseOrder;
import com.example.cozaexpress.Model.StatusOrder;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageFragment extends Fragment {

    LinearLayout donhang, thongbao;

    RecyclerView rc_Order;

    List<Order> orders;

    DangGiaoAdapter dangGiaoAdapter;

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_live, container, false);
        donhang = view.findViewById(R.id.DonHang);
        thongbao = view.findViewById(R.id.ThongBao);
        rc_Order = view.findViewById(R.id.rc_status_order);

        User user = SharedPrefManager.getInstance(getActivity()).getUser();
        APIService.apiService.getOrderByUser(user).enqueue(new Callback<ResponseOrder>() {
            @Override
            public void onResponse(Call<ResponseOrder> call, Response<ResponseOrder> response) {
                if(response.isSuccessful()){
                    orders = response.body().getOrder();
                    List<Order> orderList = new ArrayList<>();
                    for(Order order :orders){
                        if(order.getStatusOrder().equals(StatusOrder.DANGGIAO)){
                            orderList.add(order);
                        }
                    }

                    dangGiaoAdapter = new DangGiaoAdapter(getContext(),orderList);
                    rc_Order.setHasFixedSize(false);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    rc_Order.setLayoutManager(layoutManager);
                    rc_Order.setAdapter(dangGiaoAdapter);
                    dangGiaoAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ResponseOrder> call, Throwable t) {

            }
        });

        return view;
    }
}
