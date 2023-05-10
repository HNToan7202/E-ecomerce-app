package com.example.cozaexpress.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cozaexpress.Adapter.WishlistAdapter;
import com.example.cozaexpress.DAO.WishListModel;
import com.example.cozaexpress.Database.WishlistDatabase;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.R;

import java.util.ArrayList;
import java.util.List;

public class WhishlistActivity extends AppCompatActivity {

    RecyclerView rc_whishlist;
    WishlistAdapter wishlistAdapter;
    List<WishListModel> wishListModels;

    List<Product> products = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whishlist);
        AnhXa();
        wishListModels = WishlistDatabase.getInstance(this).wishlistDAO().getAll();

        for (int i = 0 ; i < wishListModels.size(); i++){
            products.addAll(wishListModels.get(i).getStringToProduct());
        }
        //products = wishListModels.get(0).getStringToProduct();

        wishlistAdapter = new WishlistAdapter(this, products);
        rc_whishlist.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        rc_whishlist.setLayoutManager(layoutManager);
        rc_whishlist.setAdapter(wishlistAdapter);
        wishlistAdapter.notifyDataSetChanged();
    }

    private void AnhXa() {
        rc_whishlist = findViewById(R.id.rc_whishlist);
    }

}