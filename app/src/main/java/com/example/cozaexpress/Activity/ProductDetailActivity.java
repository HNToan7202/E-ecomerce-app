package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView imgProduct;
    TextView tvPrice, tvDesciption;

    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        imgProduct = findViewById(R.id.imgPro_detail);
        tvPrice = findViewById(R.id.tvProprice);
        tvDesciption = findViewById(R.id.tvProDesciption);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //Product product = (Product) bundle.getSerializable("product");

//        if (product != null) {
//            Glide.with(getApplicationContext())
//                    .load(product.getListimage())
//                    .into(imgProduct);
//            tvPrice.setText(product.getPrice().toString());
//            tvDesciption.setText(product.getDesciption());
//        }

        String id = bundle.getString("idProduct");

        getProductById(id);


    }

    private void getProductById(String id) {
            APIService.apiService.getProductById(id).enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    Product product = response.body();
                    Glide.with(getApplicationContext())
                    .load(product.getListimage())
                    .into(imgProduct);
                    tvPrice.setText(product.getPrice().toString());
                    tvDesciption.setText(product.getDesciption());
                }
                @Override
                public void onFailure(Call<Product> call, Throwable t) {

                }
            });
    }
}