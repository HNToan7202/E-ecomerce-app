//package com.example.cozaexpress.Activity;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.AppCompatButton;
//import androidx.viewpager2.widget.ViewPager2;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.example.cozaexpress.Adapter.ProDetailAdapter;
//import com.example.cozaexpress.DataLocal.DataLocalManager;
//import com.example.cozaexpress.Model.Product;
//import com.example.cozaexpress.R;
//import com.example.cozaexpress.api.APIService;
//import com.google.android.material.tabs.TabLayout;
//import com.google.android.material.tabs.TabLayoutMediator;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class ProductDetailActivity extends AppCompatActivity {
//
//    ImageView imgProduct;
//
//    TextView tvPrice, tvRealPrice, tvHangSP, tvDesciption;
//
//    List<Product> productList;
//
//    TabLayout mTablayout;
//
//    ImageView btnBackToHome;
//
//    ViewPager2 mViewPager;
//
//    AppCompatButton btnThemVaoGio, btnMuaNgay;
//
//    ProDetailAdapter proDetailAdapter;
//
//    String id;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_product_detail);
//        AnhXa();
//
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        Product product = (Product) bundle.getSerializable("product");
//
//
//
////        if (product != null) {
////            Glide.with(getApplicationContext())
////                    .load(product.getListimage())
////                    .into(imgProduct);
////            tvPrice.setText(product.getPrice().toString());
////            tvDesciption.setText(product.getDesciption());
////        }
//
//        id = bundle.getString("idProduct");
//        //getProductById(id);
//    }
//
//    private void AnhXa() {
//        btnBackToHome = findViewById(R.id.btn_back_to_pro_detail);
//        mTablayout = findViewById(R.id.tab_pro_detail);
//        mViewPager = findViewById(R.id.view_pager_pro_detail);
//
//        btnThemVaoGio = findViewById(R.id.btnAddToCart);
//        btnMuaNgay = findViewById(R.id.btnMuaNgay);
//
//        proDetailAdapter = new ProDetailAdapter(this);
//        mViewPager.setAdapter(proDetailAdapter);
//        mViewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
//        new TabLayoutMediator(mTablayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                switch (position){
//                    case 0:
//                        tab.setText("Tổng Quan");
//                        break;
//                    case 1:
//                        tab.setText("Mô Tả");
//                        break;
//                    case 2:
//                        tab.setText("Thông Số");
//                        break;
//                    case 3:
//                        tab.setText("HDSD");
//                        break;
//                    case 4:
//                        tab.setText("Đánh Giá");
//                        break;
//                }
//            }
//        }).attach();
//
//        btnBackToHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//    }
//
//}