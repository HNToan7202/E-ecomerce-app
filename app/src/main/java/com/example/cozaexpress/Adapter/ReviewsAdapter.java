package com.example.cozaexpress.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cozaexpress.Fragment.ChuaDanhGia;
import com.example.cozaexpress.Fragment.FragmentReviews;
import com.example.cozaexpress.Fragment.FragmentReviewsSum;
import com.example.cozaexpress.Fragment.OrderDetailFragment;
import com.example.cozaexpress.Model.StatusOrder;
import com.example.cozaexpress.Model.StatusReviews;

public class ReviewsAdapter extends FragmentStateAdapter {

    public ReviewsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
            default:
                return new FragmentReviews();
            case 1:
                return new ChuaDanhGia();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
