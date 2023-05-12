package com.example.cozaexpress.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cozaexpress.Fragment.DanhGiaFragment;
import com.example.cozaexpress.Fragment.LoginFragment;
import com.example.cozaexpress.Fragment.MoTaFragment;
import com.example.cozaexpress.Fragment.ThongSoFragment;
import com.example.cozaexpress.Fragment.TongQuangFragment;

public class ProDetailAdapter extends FragmentStateAdapter {
    public ProDetailAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 1:
                return new MoTaFragment();
            case 2:
                return new ThongSoFragment();
            case 3:
                return new LoginFragment();
            case 4:
                return new DanhGiaFragment();
            case 0 :
            default:
                return new TongQuangFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

//    private void getProductById(String id) {
//        APIService.apiService.getProductById(id).enqueue(new Callback<Product>() {
//            @Override
//            public void onResponse(Call<Product> call, Response<Product> response) {
//                Product product = response.body();
//                Glide.with(getApplicationContext())
//                        .load(product.getListimage())
//                        .into(imgProduct);
//                tvPrice.setText(product.getPrice().toString());
//                tvDesciption.setText(product.getDesciption());
//            }
//            @Override
//            public void onFailure(Call<Product> call, Throwable t) {
//
//            }
//        });
//    }
}
