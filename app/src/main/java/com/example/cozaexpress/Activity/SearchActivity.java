package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.cozaexpress.Adapter.ProductAdapter;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.R;
import com.example.cozaexpress.Utils.LinePagerIndicatorDecoration;
import com.example.cozaexpress.api.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    androidx.appcompat.widget.SearchView searchViewProduct;
    RecyclerView rc_searchProduct;
    List<Product> productList;
ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        AnhXa();
        LoadData();

    }

    private void LoadData() {
        APIService.apiService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()) {
                    productList = response.body();
                    productAdapter = new ProductAdapter(getApplicationContext(),productList);
                    rc_searchProduct.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
                    rc_searchProduct.setLayoutManager(layoutManager);
                    rc_searchProduct.setAdapter(productAdapter);
                    rc_searchProduct.addItemDecoration(new LinePagerIndicatorDecoration());
                    searchViewProduct = findViewById(R.id.searchViewProduct) ;
                    searchViewProduct.clearFocus();
                    searchViewProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            List<Product> list = new ArrayList<>();
                            for (Product product: productList){
                                if(product.getName().toLowerCase().contains(newText.toLowerCase())){
                                    list.add(product);
                                }
                            }
                            if(list.isEmpty()){
                                Toast.makeText(getApplicationContext(), "Không có", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                productAdapter.setListenterList(list);
                            }
                            return false;
                        }
                    });



                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                //Toast.makeText(getContext(), "Load Thất Bại", Toast.LENGTH_LONG).show();
                Log.e("TAG", t.getMessage());
            }
        });
    }

    private void AnhXa() {

        rc_searchProduct = findViewById(R.id.rc_searchProduct);
    }


}