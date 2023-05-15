package com.example.cozaexpress.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cozaexpress.Adapter.LastProductAdapter;
import com.example.cozaexpress.Adapter.ProductAdapter;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductSaleFragment extends Fragment {

    ViewPager2 viewPager2;

    RecyclerView rc_product_sale;

    List<Product> products;

    ProductAdapter lastProductAdapter;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_desciption, container, false);
        rc_product_sale = view.findViewById(R.id.rvtinnhan);

        APIService.apiService.getTop5BanChay().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();

                lastProductAdapter = new ProductAdapter(getContext(), products);
                rc_product_sale.setHasFixedSize(false);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                rc_product_sale.setLayoutManager(layoutManager);
                rc_product_sale.setAdapter(lastProductAdapter);
                lastProductAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });

        return view;
    }
}