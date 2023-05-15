package com.example.cozaexpress.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cozaexpress.Fragment.OrderDetailFragment;
import com.example.cozaexpress.Model.StatusOrder;
import com.example.cozaexpress.StatusOrderFragment.BiHuyFragment;
import com.example.cozaexpress.StatusOrderFragment.ChoXacNhanFragment;
import com.example.cozaexpress.StatusOrderFragment.DangGiaoFragment;
import com.example.cozaexpress.StatusOrderFragment.ThanhCongFragment;

public class StatusOrderAdapter extends FragmentStateAdapter {

    StatusOrder statusOrder = StatusOrder.DANGGIAO;
    public StatusOrderAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

            switch (position){
                case 1:
                    return new OrderDetailFragment(StatusOrder.DANGGIAO);
                case 2:
                    return new OrderDetailFragment(StatusOrder.DAGIAO);
                case 3:
                    return new OrderDetailFragment(StatusOrder.HUY);
                case 4:
                    return new OrderDetailFragment(StatusOrder.TUCHOI);
                case 0:
                default:
                    return new OrderDetailFragment(StatusOrder.CHOXACNHAN);
        }
    }
    @Override
    public int getItemCount() {
        return 4;
    }
}
