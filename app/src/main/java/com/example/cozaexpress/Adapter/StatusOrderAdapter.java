package com.example.cozaexpress.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cozaexpress.StatusOrderFragment.BiHuyFragment;
import com.example.cozaexpress.StatusOrderFragment.ChoXacNhanFragment;
import com.example.cozaexpress.StatusOrderFragment.DangGiaoFragment;
import com.example.cozaexpress.StatusOrderFragment.ThanhCongFragment;

public class StatusOrderAdapter extends FragmentStateAdapter {

    public StatusOrderAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
                case 1:
                    return new DangGiaoFragment();
                case 2:
                    return new ThanhCongFragment();
                case 3:
                    return new BiHuyFragment();
                case 0:
                default:
                return new ChoXacNhanFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
