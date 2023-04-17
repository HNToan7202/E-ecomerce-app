package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.example.cozaexpress.Adapter.LastProductAdapter;
import com.example.cozaexpress.Model.Category;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView rcProduct;

    LastProductAdapter productAdapter;

    List<Product> productList;

    ImageView btnBack;

    AppCompatButton btnInsert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        rcProduct = findViewById(R.id.rc_product);
        btnBack = findViewById(R.id.btn_back_to_home);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getCategory();

    }

    private void getCategory() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Category category = (Category) bundle.getSerializable("object_category");
        APIService.apiService.getProductByCate(category).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productList = response.body();
                    if(productList != null || productList.size() != 0){
                        productAdapter = new LastProductAdapter(CategoryActivity.this,productList);
                        rcProduct.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(CategoryActivity.this,2);
                        rcProduct.setLayoutManager(layoutManager);
                        rcProduct.setAdapter(productAdapter);
                        productAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Thanh Cong", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "That Bai", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Loi", Toast.LENGTH_LONG).show();
                Log.e("ERR", t.getMessage());
            }
        });

    }

}