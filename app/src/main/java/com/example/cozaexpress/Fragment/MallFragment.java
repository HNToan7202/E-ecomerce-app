package com.example.cozaexpress.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cozaexpress.Adapter.MallViewPagerAdapter;
import com.example.cozaexpress.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MallFragment extends Fragment {

    //Hàm trả về view
    View view;
    TabLayout mTabLayout;
    ViewPager2 mViewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mall, container, false);
        AnhXa();
        return view;
    }

    private void AnhXa() {
        mTabLayout = view.findViewById(R.id.tabLayoutMall);
        mViewPager = view.findViewById(R.id.view_pager_mall);
        MallViewPagerAdapter viewPagerAdapter = new MallViewPagerAdapter(getActivity());
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

        new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Xem Dạo");
                    break;
                case 1:
                    tab.setText("Đã Theo Dõi");
                    break;
            }
        }).attach();
    }

}
