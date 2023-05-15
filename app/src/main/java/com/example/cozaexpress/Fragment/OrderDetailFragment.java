package com.example.cozaexpress.Fragment;


import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.cozaexpress.Adapter.OrderAdapter;
import com.example.cozaexpress.Adapter.StatusCheckAdapter;
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Model.Order;
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

public class OrderDetailFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    StatusOrder mStatusOrder;
    SwipeRefreshLayout orderDetailFragment;
    OrderAdapter orderAdapter;
    RecyclerView rcOrder;
    LinearLayout layoutBtn;
    List<Order> listOrder;
    View view;
    Button btnmualai, btndanhgia;


    public OrderDetailFragment(StatusOrder statusOrder) {
        // Required empty public constructor
        mStatusOrder = statusOrder;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_order_detail, container, false );
        AnhXa();
        return view;

    }
    private void AnhXa() {
        rcOrder = view.findViewById(R.id.rc_OrderList1);
        orderDetailFragment = view.findViewById(R.id.orderDetailFragment);
        orderDetailFragment.setOnRefreshListener(this);
        getOrders(mStatusOrder);



    }

    private void getOrders(StatusOrder mStatusOrder) {

        User user = SharedPrefManager.getInstance(getContext()).getUser();
        Log.e("user", user.getId());
        Log.d("user", user.getFullName().toString());
        if(user != null){
            APIService.apiService.getOrderByUser(user).enqueue(new Callback<ResponseOrder>() {
                @Override
                public void onResponse(Call<ResponseOrder> call, Response<ResponseOrder> response) {

                    List<Order> orderList = new ArrayList<>();
                    listOrder = response.body().getOrder();
                    for(Order order : listOrder){
                        if(order.getStatusOrder() == mStatusOrder){
                            orderList.add(order);
                        }
                    }
                    orderAdapter = new OrderAdapter(getContext(), orderList);
                    rcOrder.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
                    rcOrder.setLayoutManager(layoutManager);
                    rcOrder.setAdapter(orderAdapter);
                    orderAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<ResponseOrder> call, Throwable t) {

                }
            });
        }

    }

    @Override
    public void onRefresh() {
        getOrders(mStatusOrder);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                orderDetailFragment.setRefreshing(false);
            }
        }, 2000);
    }
}
