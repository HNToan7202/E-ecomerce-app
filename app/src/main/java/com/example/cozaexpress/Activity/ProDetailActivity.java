package com.example.cozaexpress.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.room.Database;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.cozaexpress.Adapter.ProDetailAdapter;
import com.example.cozaexpress.DataLocal.DataLocalManager;
import com.example.cozaexpress.Database.UserDatabase;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProDetailActivity extends AppCompatActivity {

    ImageView imgProduct;
    TextView tvPrice, tvDesciption, tvGiaChuaGiam, tvNameSp, tvHangSP;
    List<Product> productList = new ArrayList<>();

    ImageView btnBackToHome;

    ImageView btnPopup, btnCart, btnBack;

    AppCompatButton btnThemVaoGio, btnMuaNgay;

    ProDetailAdapter proDetailAdapter;

    String id;

    int idDialog;

    Product product;

    @BindView(R.id.add_to_wishlist)
    LottieAnimationView addToWishlist;

    @BindView(R.id.wishlist_red)
    ImageView wishlist_red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getString("idProduct");

        if(id != null){
            getProductById(id);
        }

        Product product = (Product) bundle.getSerializable("product");

        AnhXa();

        if(product != null){
            Glide.with(getApplicationContext())
                    .load(product.getListimage())
                    .into(imgProduct);
            tvGiaChuaGiam.setText(product.getPrice().toString());
            tvPrice.setText(product.getPromotionaprice().toString());
            tvDesciption.setText(product.getDesciption());
            tvNameSp.setText(product.getName());
            Log.d("TAG", product.getName());
        }
        registerForContextMenu(btnPopup);
        btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopupMenu();
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void getProductById(String id) {
        APIService.apiService.getProductById(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                product = response.body();
                Glide.with(getApplicationContext())
                        .load(product.getListimage())
                        .into(imgProduct);
                tvGiaChuaGiam.setText(String.format( "%,.0f",product.getPrice())+"đ");
                tvPrice.setText(String.format( "%,.0f",product.getPromotionaprice())+"đ");
                tvDesciption.setText(product.getDesciption());
                tvNameSp.setText(product.getName());
            }
            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }

    public void shareProduct(View view) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Found amazing " + product.getName() + "on CozaExpress App";
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public void addToWishList(View view) {
    }


    private void AnhXa() {
        btnBack = findViewById(R.id.btn_back_to_prodetail);
        btnCart = findViewById(R.id.btn_cart);
        btnPopup = findViewById(R.id.btnMoreOption);
        imgProduct = findViewById(R.id.img_product_detail);
        tvPrice = findViewById(R.id.tvGiaDaGiam);
        tvGiaChuaGiam = findViewById(R.id.tv_Gia_Da_Giam);
        tvGiaChuaGiam.setPaintFlags(tvGiaChuaGiam.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tvDesciption = findViewById(R.id.tvmota);
        tvNameSp = findViewById(R.id.tvnamedetail);
        tvHangSP = findViewById(R.id.tvHang_SP_detail);

        btnBackToHome = findViewById(R.id.btn_back_to_prodetail);
        btnThemVaoGio = findViewById(R.id.btn_add_to_cart);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnThemVaoGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog1();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSetting:
                break;
            case R.id.menuShare:
                break;
            case R.id.menuLogout:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSetting:
//lệnh
                Toast.makeText(ProDetailActivity.this,"Bạn đang chọn Setting",Toast.LENGTH_LONG).show();
                break;
            case R.id.menuShare:
                break;
            case R.id.menuLogout:
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void ShowPopupMenu(){
        PopupMenu popupMenu = new PopupMenu(this,btnPopup);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup,popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuSetting://lệnh
                        Toast.makeText(ProDetailActivity.this,"Bạn đang chọn Setting",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menuShare:
                        break;
                    case R.id.menuLogout:
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void XacNhanXoa( final int vitri){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Thông báo");
        alert.setMessage("Bạn có muốn đăng xuất không");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    //lệnh nút có
            }
        });
        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //lệnh nút không
            }
        });
        alert.show();
    }

    private void DiaLog1(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        dialog.setCanceledOnTouchOutside(false);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        //ánh xạ
//        EditText editText1 = (EditText)
//                dialog.findViewById(R.id.editTextTextPersonName);
//        //viết code sự kiện
        //bắt sự kiện Dialog
        Button btnXacNhan = dialog.findViewById(R.id.btn_add_to_cart2);

        ImageView btnClose = dialog.findViewById(R.id.btnClose);

        EditText edtCount = dialog.findViewById(R.id.edtCount);

        TextView tvPrice = dialog.findViewById(R.id.tv_price_dialog);

        TextView tvPriceReal = dialog.findViewById(R.id.tv_price_real);

        ImageView imgae = dialog.findViewById(R.id.img_hinh_sp);

        tvPrice.setText(String.format( "%,.0f",product.getPromotionaprice())+"đ");

        tvPriceReal.setText(String.format( "%,.0f",product.getPrice())+"đ");

        Glide.with(getApplicationContext())
                .load(product.getListimage())
                .into(imgae);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //productList = DataLocalManager.getListProduct();
                int soluong = Integer.parseInt(edtCount.getText().toString());
                product.setQuantity(soluong);
                //productList.add(product);
                UserDatabase.getInstance(getApplicationContext()).productDAO().insertProduct(product);
                Toast.makeText(ProDetailActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_LONG).show();

//                Product product1 = UserDatabase.getInstance(getApplicationContext()).productDAO().checkProduct(product.getId());
//
//                if(product1.getId() == product.getId()){
//                   product.setQuantity(product.getQuantity() + soluong);
//                    UserDatabase.getInstance(getApplicationContext()).productDAO().update(product);
//                    Toast.makeText(ProDetailActivity.this, "Đã Cập Nhật Giỏ Hàng", Toast.LENGTH_LONG).show();
//                }else {
//                    product.setQuantity(soluong);
//                    UserDatabase.getInstance(getApplicationContext()).productDAO().insertProduct(product);
//                    Toast.makeText(ProDetailActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_LONG).show();
//                }

                dialog.dismiss();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show(); //hủy gọi dialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_popup,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_popup,menu);
        menu.setHeaderTitle("Context Menu");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}