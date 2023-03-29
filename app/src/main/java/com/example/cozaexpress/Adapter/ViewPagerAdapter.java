package com.example.cozaexpress.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.cozaexpress.Fragment.AccountFragment;
import com.example.cozaexpress.Fragment.HomeFragment;
import com.example.cozaexpress.Fragment.LiveFragment;
import com.example.cozaexpress.Fragment.MallFragment;
import com.example.cozaexpress.Fragment.NotificationFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new HomeFragment();
            case 1:
                return new MallFragment();
            case 2:
                return new LiveFragment();
            case 3:
                return new NotificationFragment();
            case 4:
                return new AccountFragment();
        }
        return null;
    }

    //trả về số lượng của task
    @Override
    public int getCount() {
        return 5;
    }
}
