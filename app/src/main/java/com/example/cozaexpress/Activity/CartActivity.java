package com.example.cozaexpress.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.cozaexpress.Adapter.CartAdapter;
import com.example.cozaexpress.Database.ProductDatabase;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.R;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    ViewPager viewPagerCart;

    View view;

    TabLayout tabLayout;

    RecyclerView rcCart, btnDelete;

    TextView total;

    List<Product> products;

    private LottieAnimationView emptycart;

    public static final String GET_CART_ITEMS = "get_cart_items";

    public static final String GET_SUM   = "get_sum";

    public static final String GET_BUNDLE   = "get_BUNDLE";

    Double sum = 0.0;

    AppCompatButton btnContinue;
    private Double loaddata(Double data){
        return data;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rcCart = findViewById(R.id.rc_item_cart);
        total = findViewById(R.id.tvTotal_cart);
        emptycart = findViewById(R.id.empty_cart);
        btnContinue = findViewById(R.id.btn_continue);
        CartAdapter cartAdapter;
        products = ProductDatabase.getInstance(getApplicationContext()).productDAO().getAll();
        if(products.size() == 0){
            emptycart.setVisibility(View.VISIBLE);
        }

        for(int i = 0 ; i <products.size();i++){
            sum += products.get(i).getPromotionaprice()*products.get(i).getQuantity();
        }

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(GET_CART_ITEMS, (Serializable) products);
                bundle.putDouble(GET_SUM, sum);
                Intent intent1 = new Intent(getApplicationContext(), CheckoutActivity.class);
                intent1.putExtra(GET_BUNDLE,bundle);
                startActivity(intent1);
            }
        });

        total.setText(String.format( "%,.0f" +"đ",sum));

        cartAdapter = new CartAdapter(getApplicationContext(), products);
        cartAdapter.setData(products);
        rcCart.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rcCart.setLayoutManager(layoutManager);
        rcCart.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
    }

}