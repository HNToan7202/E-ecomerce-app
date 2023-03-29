package com.example.cozaexpress.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.cozaexpress.Adapter.PhotoAdapter;
import com.example.cozaexpress.Model.Photo;
import com.example.cozaexpress.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {


    //Hàm trả về view
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    PhotoAdapter photoAdapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        AnhXa();
        return view;
    }

    private void AnhXa() {
        viewPager = view.findViewById(R.id.viewPager_Home);
        circleIndicator =view.findViewById(R.id.indicator);
        photoAdapter = new PhotoAdapter(getContext(), getListPhoto());
        viewPager.setAdapter(photoAdapter);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
    }

    private List<Photo> getListPhoto() {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo(R.drawable.hinh1));
        photos.add(new Photo(R.drawable.hinh2));
        photos.add(new Photo(R.drawable.hinh3));
        photos.add(new Photo(R.drawable.hinh4));
        return photos;
    }
}
