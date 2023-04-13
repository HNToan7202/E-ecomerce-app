package com.example.cozaexpress.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.Fragment.HomeFragment;
import com.example.cozaexpress.Model.Photo;
import com.example.cozaexpress.R;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mListPhoto;

    public PhotoAdapter(Context mContext, List<String> mListPhoto) {
        this.mContext = mContext;
        this.mListPhoto = mListPhoto;
    }

    @Override
    public int getCount() {
        if(mListPhoto != null)
            return mListPhoto.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo, container, false);
        ImageView imgPhoto = view.findViewById(R.id.img_photo);

        String photo = mListPhoto.get(position);

        if(photo != null){
            Glide.with(mContext).load(photo).into(imgPhoto);
        }
        //add view to view group
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //remove view
        container.removeView((View) object);
    }
}
