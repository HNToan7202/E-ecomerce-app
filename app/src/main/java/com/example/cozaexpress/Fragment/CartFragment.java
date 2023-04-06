package com.example.cozaexpress.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.cozaexpress.Adapter.CartAdapter;
import com.example.cozaexpress.Adapter.MallViewPagerAdapter;
import com.example.cozaexpress.R;
import com.google.android.material.tabs.TabLayout;


public class CartFragment extends Fragment {

    ViewPager viewPagerCart;

    View view;

    TabLayout tabLayout;

    //Hàm trả về view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        //AnhXa();
        return view;
    }

//    private void AnhXa() {
//        tabLayout = view.findViewById(R.id.tabLayoutCart);
//        viewPagerCart = view.findViewById(R.id.view_pager_cart);
//        CartAdapter viewPagerAdapter = new CartAdapter(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        viewPagerCart.setAdapter(viewPagerAdapter);
//        tabLayout.setupWithViewPager(viewPagerCart);
//    }
}
