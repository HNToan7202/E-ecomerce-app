package com.example.cozaexpress.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cozaexpress.Adapter.CategoryAdapter;
import com.example.cozaexpress.Adapter.LastProductAdapter;
import com.example.cozaexpress.Adapter.MallViewPagerAdapter;
import com.example.cozaexpress.Adapter.ProductAdapter;
import com.example.cozaexpress.Model.Category;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.R;
import com.example.cozaexpress.Utils.LinePagerIndicatorDecoration;
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
    CategoryAdapter categoryAdapter;
    List<Category> categoryList;
    ProductAdapter productAdapter;
    Button btnAddProductForCate,btnManagerCate;
    RecyclerView rcCate;
    List<Product> productList;

    RecyclerView rcProduct;
    //Hàm trả về view
    androidx.appcompat.widget.SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mall, container, false);
        AnhXa();
        GetCateNew();
        getProducts();
        return view;
    }

    private void getProductsByCate(List<Product> productList) {
        productAdapter = new ProductAdapter(getContext(),productList);
        rcProduct.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        rcProduct.setLayoutManager(layoutManager);
        rcProduct.setAdapter(productAdapter);
        rcProduct.addItemDecoration(new LinePagerIndicatorDecoration());
        searchView =view.findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterListener(newText);
                return false;
            }

            private void filterListener(String newText) {
                List<Product> list = new ArrayList<>();
                for (Product product: productList){
                    if(product.getName().toLowerCase().contains(newText.toLowerCase())){
                        list.add(product);
                    }
                }
                if(list.isEmpty()){
                    Toast.makeText(getContext(), "Không có", Toast.LENGTH_SHORT).show();
                }
                else{
                    productAdapter.setListenterList(list);
                }
            }
        });
    }

    private void AnhXa() {

        rcCate = view.findViewById(R.id.rc_category);
        rcProduct = view.findViewById(R.id.rc_category_product);

        productAdapter = new ProductAdapter(getContext(),productList);
        rcProduct.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        rcProduct.setLayoutManager(layoutManager);
        rcProduct.setAdapter(productAdapter);
        rcProduct.addItemDecoration(new LinePagerIndicatorDecoration());
        searchView =view.findViewById(R.id.searchView);
        searchView.clearFocus();

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                filterListener(newText);
//                return false;
//            }
//
//            private void filterListener(String newText) {
//                List<Product> list = new ArrayList<>();
//                if(list != null){
//                    for (Product product: productList){
//                        if(product.getName().toLowerCase().contains(newText.toLowerCase())){
//                            list.add(product);
//                        }
//                    }
//                    if(list.isEmpty()){
//                        Toast.makeText(getContext(), "Không có", Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        productAdapter.setListenterList(list);
//                    }
//                }
//
//            }
//        });

    }

    private void getProducts() {
        if(productList == null){
            APIService.apiService.getProducts().enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    if(response.isSuccessful()) {
                        productList = response.body();
                    }
                    else {
                        int statusCode = response.code();
                    }
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    //Toast.makeText(getContext(), "Load Thất Bại", Toast.LENGTH_LONG).show();
                    Log.e("TAG", t.getMessage());
                }
            });
        }

        if(productList != null){
            productAdapter = new ProductAdapter(getContext(),productList);
            rcProduct.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
            rcProduct.setLayoutManager(layoutManager);
            rcProduct.setAdapter(productAdapter);
            rcProduct.addItemDecoration(new LinePagerIndicatorDecoration());
            searchView =view.findViewById(R.id.searchView);
            searchView.clearFocus();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    filterListener(newText);
                    return false;
                }
            });
        }

        //Toast.makeText(getContext(), "Đã Load Product", Toast.LENGTH_LONG).show();
    }



    private void filterListener(String newText) {
        List<Product> list = new ArrayList<>();
        for (Product product: productList){
            if(product.getName().toLowerCase().contains(newText.toLowerCase())){
                list.add(product);
            }
        }
        if(list.isEmpty()){
            Toast.makeText(getContext(), "Không có", Toast.LENGTH_SHORT).show();
        }
        else{
            productAdapter.setListenterList(list);
        }

    }

    public void GetCateNew(){
        APIService.apiService.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    categoryList = response.body();
                    categoryAdapter = new CategoryAdapter(getContext(), categoryList, (position, nameCate) -> APIService.apiService.ListProductByCate(nameCate).enqueue(new Callback<List<Product>>() {

                        @Override
                        public void onResponse(Call<List<Product>> call1, Response<List<Product>> response1) {
                            if(response1.isSuccessful()){
                                List<Product> productList = (List<Product>) response1.body();
                                getProductsByCate(productList);
                            }
                            else{
                                Toast.makeText(getContext(), "loi server" , Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Product>> call1, Throwable t) {
                            Log.e("TAG",t.getMessage());
                            Toast.makeText(getContext(), "Fail" , Toast.LENGTH_SHORT).show();

                        }
                    }));
                    rcCate.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    rcCate.setLayoutManager(layoutManager);
                    rcCate.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();

                }
                else{
                    Toast.makeText(getContext(), "Success NO ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getActivity(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }




}
