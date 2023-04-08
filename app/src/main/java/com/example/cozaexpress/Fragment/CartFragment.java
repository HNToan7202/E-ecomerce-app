package com.example.cozaexpress.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.cozaexpress.Adapter.CartAdapter;
import com.example.cozaexpress.Adapter.MallViewPagerAdapter;
import com.example.cozaexpress.DataLocal.DataLocalManager;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.R;
import com.google.android.material.tabs.TabLayout;

import java.util.List;


public class CartFragment extends Fragment {

    ViewPager viewPagerCart;

    View view;

    TabLayout tabLayout;

    RecyclerView rcCart, btnDelete;


    //Hàm trả về view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        //AnhXa();
        rcCart = view.findViewById(R.id.rc_item_cart);
        List<Product> products = DataLocalManager.getListProduct();
        CartAdapter cartAdapter = new CartAdapter(getContext(), products);

        rcCart.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcCart.setLayoutManager(layoutManager);
        rcCart.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        return view;
    }
}
