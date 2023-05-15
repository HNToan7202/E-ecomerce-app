package com.example.cozaexpress.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cozaexpress.Activity.ProDetailActivity;
import com.example.cozaexpress.Activity.SearchActivity;
import com.example.cozaexpress.Adapter.CategoryAdapter;
import com.example.cozaexpress.Adapter.LastProductAdapter;
import com.example.cozaexpress.Adapter.PhotoAdapter;
import com.example.cozaexpress.Adapter.ProductAdapter;
import com.example.cozaexpress.Adapter.ProductSaleAdapter;
import com.example.cozaexpress.Adapter.StatusSignInAdapter;
import com.example.cozaexpress.Model.Category;
import com.example.cozaexpress.Model.Photo;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    //SwipeRefreshLayout swipeRefreshLayout;

    //Hàm trả về view
    ViewPager2 viewPager;
    CircleIndicator3 circleIndicator;
    PhotoAdapter photoAdapter;
    View view;

    RecyclerView rcCate;

    List<Photo> mListPhoto;

    private Timer mTimer;

    Button btnSearch;

    CategoryAdapter categoryAdapter;

    List<Category> categoryList;

    LastProductAdapter productAdapter;

    List<Product> productList, productByBarcode;

    RecyclerView rcProduct;

    ImageView btnSCan;

    TextView tvAutoSlide;

    ScrollView scrollView;

    LinearLayout linearLayout;

    ImageView imgprofile;

    ViewPager2 viewPager2_xuhuong_muasam;

    ProductSaleAdapter productSaleAdapter;
    RecyclerView rc_flashsale;

    TabLayout tabLayoutXH;

    ProductAdapter producFlashSaleAdapter;

    List<Product> listFlashSale;

    TextView tvCountdown;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        AnhXa();
        getCategories();
        getProducts();
        SetFlashSale();
        return view;
    }

    private void SetFlashSale() {

        long flashSaleDurationInMillis = 600000; // Thời gian flash sale (vd: 10 phút) 600000
        long intervalInMillis = 1000; // Khoảng thời gian giữa các tick (vd: 1 giây)

        CountDownTimer countDownTimer = new CountDownTimer(flashSaleDurationInMillis, intervalInMillis) {
            @Override
            public void onTick(long millisUntilFinished) {
                long hours = (millisUntilFinished / (60 * 60 * 1000)) % 24;
                long minutes = (millisUntilFinished / (60 * 1000)) % 60;
                long seconds = (millisUntilFinished / 1000) % 60;
                String countdownText = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
                tvCountdown.setText(countdownText);
            }
            @Override
            public void onFinish() {
                // Thực hiện các hành động sau khi flash sale kết thúc
            }
        };

// Bắt đầu đếm ngược
        countDownTimer.start();
    }

    private void getProducts() {
        APIService.apiService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productList = response.body();
                    productAdapter = new LastProductAdapter(getContext(),productList);
                    rcProduct.setHasFixedSize(false);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
                    rcProduct.setLayoutManager(layoutManager);
                    rcProduct.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();
                    //Toast.makeText(getContext(), "Đã Load Product", Toast.LENGTH_LONG).show();
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

    private void getCategories() {
        productSaleAdapter = new ProductSaleAdapter(getActivity());
        viewPager2_xuhuong_muasam.setAdapter(productSaleAdapter);
        new TabLayoutMediator(tabLayoutXH, viewPager2_xuhuong_muasam, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Xu hướng");
                    break;
                case 1:
                    tab.setText("Mẫu mới");
                    break;
            }
        }).attach();

    }

    private void AnhXa() {
        rc_flashsale = view.findViewById(R.id.rc_xuhuong_flashsale);
        tvCountdown = view.findViewById(R.id.txtGio);

        getFlashSale();

        tabLayoutXH = view.findViewById(R.id.tabLayoutXH);

//        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh_home);
//        swipeRefreshLayout.setOnRefreshListener(this);

        viewPager2_xuhuong_muasam = view.findViewById(R.id.viewPager2_xuhuong_muasam);

        btnSCan = view.findViewById(R.id.scanbtn2);
        rcProduct = view.findViewById(R.id.rc_prodcut);

        rcCate = view.findViewById(R.id.rc_category);

        viewPager = view.findViewById(R.id.viewPager_Home);
        circleIndicator =view.findViewById(R.id.indicator);

        mListPhoto = getListPhoto();

        photoAdapter = new PhotoAdapter(getActivity(), mListPhoto);

        viewPager.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager);

        btnSearch = view.findViewById(R.id.btnSearch);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        btnSCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });
        autoSlideImage();
    }

    private void getFlashSale() {
        APIService.apiService.getTop5discount().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                listFlashSale = response.body();
                producFlashSaleAdapter = new ProductAdapter(getContext(), listFlashSale);
                rc_flashsale.setHasFixedSize(false);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
                rc_flashsale.setLayoutManager(layoutManager);
                rc_flashsale.setAdapter(producFlashSaleAdapter);
                producFlashSaleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }


    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volunm up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);


    }

    private List<Photo> getListPhoto() {
        List<Photo> photos = new ArrayList<>();
        Photo pt1 = new Photo("https://ecomserver1.up.railway.app/images/mau-phong-khach-nha-xinh-banner-27722.jpg");
        Photo pt2 = new Photo("https://ecomserver1.up.railway.app/images/mau-phong-ngu-nx-banner-27722.jpg");
        Photo pt3 = new Photo("https://ecomserver1.up.railway.app/images/mau-phong-khach-nha-xinh-banner-27722.jpg");
        photos.add(pt1);
        photos.add(pt2);
        photos.add(pt3);
        return photos;
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() != null){
            String barCode = result.getContents();

            getProductByBarcode(barCode);
            //Toast.makeText(getContext(), result.getContents(), Toast.LENGTH_LONG).show();
//            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//            builder.setTitle("result");
//            builder.setMessage(result.getContents());
//            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//            }).show();

        }
    });

    private void getProductByBarcode(String barCode) {
        APIService.apiService.getProductByBarcode(barCode).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productByBarcode = response.body();
                if(productByBarcode != null){
                    //scantext.setText(productByBarcode.get(0).getName());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("product", productByBarcode.get(0));
                    Intent intent = new Intent(getContext(), ProDetailActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }


    private void autoSlideImage(){

        if (mListPhoto == null || mListPhoto.isEmpty() ||viewPager == null) {
            return;
        }
        //Init Timer
        if(mTimer == null){
            mTimer = new Timer();
        }
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = mListPhoto.size() - 1;
                        if(currentItem < totalItem){
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        }
                        else {
                            viewPager.setCurrentItem(0);
                        }

                    }

                });
            }
        }, 500, 3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }
}
