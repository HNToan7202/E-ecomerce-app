package com.example.cozaexpress.StatusOrderFragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cozaexpress.Adapter.StatusCheckAdapter;
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Model.Order;
import com.example.cozaexpress.Model.ResponseOrder;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChoXacNhanFragment extends Fragment {

    View view;

    RecyclerView rc_order;

    StatusCheckAdapter statusCheckAdapter;

    List<Order> orderList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_choxacnhan, container, false );
        rc_order = view.findViewById(R.id.rc_order_choxacnhan);
        User user = SharedPrefManager.getInstance(getContext()).getUser();
        Log.e("user", user.getId());
        Log.d("user", user.getFullName().toString());
        if(user != null){
            APIService.apiService.getOrderByUser(user).enqueue(new Callback<ResponseOrder>() {
                @Override
                public void onResponse(Call<ResponseOrder> call, Response<ResponseOrder> response) {
                    orderList = response.body().getOrder();
                    Log.e(TAG, "onResponse: "+ orderList.get(0).getAddress());
                    statusCheckAdapter = new StatusCheckAdapter(getContext(), orderList);
                    rc_order.setHasFixedSize(false);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    rc_order.setLayoutManager(layoutManager);
                    rc_order.setAdapter(statusCheckAdapter);
                    statusCheckAdapter.notifyDataSetChanged();
                }
                @Override
                public void onFailure(Call<ResponseOrder> call, Throwable t) {
                }
            });
        }
        return view;
    }
}
