//package com.example.cozaexpress.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.viewpager.widget.PagerAdapter;
//
//import com.bumptech.glide.Glide;
//import com.example.cozaexpress.Model.Photo;
//import com.example.cozaexpress.R;
//import com.squareup.picasso.Picasso;
//
//import java.util.List;
//
//public class BannerAdapter extends PagerAdapter {
//
//    Context context;
//    List<Photo> listPhoto;
//
//    @Override
//    public int getCount() {
//        return listPhoto.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        //LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.row_banner, container,false);
//        ImageView imageView = view.findViewById(R.id.imgBackgroundBanner);
//        //thêm nội dung thì gọi thêm textview
//        Picasso.get()
//                .load(listPhoto.get(position).getHinhAnh()).into(imageView);
//        return view;
//
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View) object);
//    }
//}
