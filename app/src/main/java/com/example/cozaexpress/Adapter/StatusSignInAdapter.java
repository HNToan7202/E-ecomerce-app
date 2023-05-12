package com.example.cozaexpress.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cozaexpress.Fragment.DanhGiaFragment;
import com.example.cozaexpress.Fragment.LoginFragment;
import com.example.cozaexpress.Fragment.SignUpFragment;

public class StatusSignInAdapter extends FragmentStateAdapter {

    public StatusSignInAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
                case 1:
                    return new SignUpFragment();
                case 0:
                default:
                return new LoginFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
