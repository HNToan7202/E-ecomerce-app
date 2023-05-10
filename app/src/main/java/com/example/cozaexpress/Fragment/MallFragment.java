package com.example.cozaexpress.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cozaexpress.Adapter.CategoryAdapter;
import com.example.cozaexpress.Adapter.MallViewPagerAdapter;
import com.example.cozaexpress.Model.Category;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MallFragment extends Fragment {

    //Hàm trả về view
    View view;
    SearchView searchView;
    RecyclerView rcCate;
    List<Category> categoryList;

    CategoryAdapter categoryAdapter;

    List<Category> list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mall, container, false);
        AnhXa();
        return view;
    }

    private void AnhXa() {
        searchView = view.findViewById(R.id.searchView);
        rcCate = view.findViewById(R.id.rc_Category);
        getCategories();
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterListener(s);
                return true;
            }
        });

    }
    private void filterListener(String text){
        list = new ArrayList<>();
        if(categoryList != null){
            for (Category category: categoryList){
                if(category.getName().toLowerCase().contains(text.toLowerCase())){
                    list.add(category);
                }
            }
            if(list.isEmpty()){
                Toast.makeText(getActivity(), "Không có dữ liệu", Toast.LENGTH_LONG).show();
            }
            else {
                //categoryAdapter.setListenerList(list);
            }
        }
    }

    private void getCategories() {
        APIService.apiService.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    categoryList = response.body();
                    categoryAdapter = new CategoryAdapter(getContext(), categoryList);
                    rcCate.setHasFixedSize(false);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
                    rcCate.setLayoutManager(layoutManager);
                    rcCate.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("CATE", t.getMessage());
            }
        });
    }


}
