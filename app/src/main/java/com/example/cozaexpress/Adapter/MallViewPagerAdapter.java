package com.example.cozaexpress.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cozaexpress.Activity.FollowedFragment;
import com.example.cozaexpress.Activity.ViewFragment;

public class MallViewPagerAdapter extends FragmentStateAdapter {

    public MallViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new FollowedFragment();
            case 0 :
            default:
                return new ViewFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }



}
