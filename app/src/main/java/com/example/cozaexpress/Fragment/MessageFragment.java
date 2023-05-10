package com.example.cozaexpress.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cozaexpress.Adapter.Category1Adapter;
import com.example.cozaexpress.Adapter.CategoryAdapter;
import com.example.cozaexpress.Adapter.LastProductAdapter;
import com.example.cozaexpress.Model.Category;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageFragment extends Fragment {
    Category1Adapter categoryAdapter;
    List<Category> categoryList;
    LastProductAdapter productAdapter;

    RecyclerView rcCate;
    List<Product> productList;
    RecyclerView rcProduct;
    //Hàm trả về view

    //Hàm trả về view
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product, container, false);
        AnhXa();
        GetCateNew();
        getProducts();
        return view;
    }
    private void getProductsByCate(List<Product> productList) {
        productAdapter = new LastProductAdapter(getContext(),productList);
        rcProduct.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        rcProduct.setLayoutManager(layoutManager);
        rcProduct.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }
    private void AnhXa() {
        rcCate = view.findViewById(R.id.rc_category);
        rcProduct = view.findViewById(R.id.rc_category_product);
    }

    private void getProducts() {
        APIService.apiService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productList = response.body();
                    productAdapter = new LastProductAdapter(getContext(),productList);
                    rcProduct.setHasFixedSize(false);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
                    rcProduct.setLayoutManager(layoutManager);
                    rcProduct.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();
                    //Toast.makeText(getContext(), "Đã Load Product", Toast.LENGTH_LONG).show();
                }
                else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                //Toast.makeText(getContext(), "Load Thất Bại", Toast.LENGTH_LONG).show();
                Log.e("TAG", t.getMessage());
            }
        });
    }

    public void GetCateNew(){
        APIService.apiService.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    categoryList = response.body();
                    categoryAdapter = new Category1Adapter(getContext(), categoryList, (position, nameCate) -> APIService.apiService.ListProductByCate(nameCate).enqueue(new Callback<List<Product>>() {

                        @Override
                        public void onResponse(Call<List<Product>> call1, Response<List<Product>> response1) {
                            if(response1.isSuccessful()){
                                List<Product> productList = (List<Product>) response1.body();
                                getProductsByCate(productList);
                            }
                            else{
                                Toast.makeText(getContext(), "loi server" , Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Product>> call1, Throwable t) {
                            Log.e("TAG",t.getMessage());
                            Toast.makeText(getContext(), "Fail" , Toast.LENGTH_SHORT).show();

                        }
                    }));
                    rcCate.setHasFixedSize(false);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    rcCate.setLayoutManager(layoutManager);
                    rcCate.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();

                }
                else{
                    Toast.makeText(getContext(), "Success NO ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
