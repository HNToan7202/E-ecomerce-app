package com.example.cozaexpress.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cozaexpress.Fragment.BestSellerFragment;
import com.example.cozaexpress.Fragment.HotSaleFragment;
import com.example.cozaexpress.Fragment.ProductSaleFragment;

public class ProductSaleAdapter extends FragmentStateAdapter {

    public ProductSaleAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 :
                default:
                    return new ProductSaleFragment();
            case 1:
                return new BestSellerFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
