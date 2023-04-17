package com.example.cozaexpress.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.Fragment.HomeFragment;
import com.example.cozaexpress.Fragment.PhotoFragment;
import com.example.cozaexpress.Model.Photo;
import com.example.cozaexpress.R;

import java.util.List;

public class PhotoAdapter extends FragmentStateAdapter {

    private List<Photo> mListPhoto;
    public PhotoAdapter(@NonNull FragmentActivity fragmentActivity, List<Photo> photos) {
        super(fragmentActivity);
        mListPhoto = photos;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Photo photo = mListPhoto.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_photo", photo);

        //set Dữ liệu cho fragment
        PhotoFragment photoFragment = new PhotoFragment();
        photoFragment.setArguments(bundle);

        return photoFragment;
    }

    @Override
    public int getItemCount() {
        if(mListPhoto != null)
            return mListPhoto.size();
        return 0;
    }
}
