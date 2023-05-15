package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.cozaexpress.Adapter.WishlistAdapter;
import com.example.cozaexpress.DAO.WishListModel;
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Database.WishlistDatabase;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.Model.UserWishlist;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WhishlistActivity extends AppCompatActivity {

    RecyclerView rc_whishlist;
    WishlistAdapter wishlistAdapter;
    List<WishListModel> wishListModels;

    List<Product> products ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whishlist);
        AnhXa();

        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        APIService.apiService.getWishlistByUser(user).enqueue(new Callback<UserWishlist>() {
            @Override
            public void onResponse(Call<UserWishlist> call, Response<UserWishlist> response) {
                products = response.body().getList();
                Log.e("TAG", "onResponse: "+ products.size() );
                wishlistAdapter = new WishlistAdapter(getApplicationContext(), products);
                rc_whishlist.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
                rc_whishlist.setLayoutManager(layoutManager);
                rc_whishlist.setAdapter(wishlistAdapter);
                wishlistAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<UserWishlist> call, Throwable t) {

                Log.e("TAG", "onFailure: "+ t.getMessage() );
            }
        });

    }

    private void AnhXa() {
        rc_whishlist = findViewById(R.id.rc_whishlist);
    }

}