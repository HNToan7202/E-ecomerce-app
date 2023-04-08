package com.example.cozaexpress.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.Adapter.ProDetailAdapter;
import com.example.cozaexpress.DataLocal.DataLocalManager;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import java.util.ArrayList;
import java.util.List;

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
            tvNameSp.setText(product.getName());
            Glide.with(getApplicationContext())
                    .load(product.getListimage())
                    .into(imgProduct);
            tvGiaChuaGiam.setText(product.getPrice().toString());
            tvPrice.setText(product.getPromotionaprice().toString());
            tvDesciption.setText(product.getDesciption());
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
                tvGiaChuaGiam.setText(product.getPrice().toString());
                tvPrice.setText(product.getPromotionaprice().toString());
                tvDesciption.setText(product.getDesciption());
            }
            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }
    private void AnhXa() {
        btnBack = findViewById(R.id.btn_back_to_prodetail);
        btnCart = findViewById(R.id.btn_cart);
        btnPopup = findViewById(R.id.btnMoreOption);
        imgProduct = findViewById(R.id.img_product_detail);
        tvPrice = findViewById(R.id.tvGiaDaGiam);
        tvGiaChuaGiam = findViewById(R.id.tv_Gia_Da_Giam);
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
        //ánh xạ
//        EditText editText1 = (EditText)
//                dialog.findViewById(R.id.editTextTextPersonName);
//        //viết code sự kiện
        //bắt sự kiện Dialog
        Button btnXacNhan = dialog.findViewById(R.id.btn_add_to_cart2);

//        Button btnThem = dialog.findViewById(R.id.btnCong);
//        Button btnTru = dialog.findViewById(R.id.btnTru);
//        TextView tvCount = dialog.findViewById(R.id.tvCount);
        ImageView btnClose = dialog.findViewById(R.id.btnClose);

//        if(idDialog == 1)
//        {
//            btnTru.setVisibility(Button.INVISIBLE);
//        }
//        else {
//            btnTru.setVisibility(Button.VISIBLE);
//        }
//        btnThem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                idDialog++;
//                tvCount.setText(idDialog);
//            }
//        });
//
//        btnTru.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                idDialog--;
//                tvCount.setText(idDialog);
//            }
//        });

        EditText edtCount = dialog.findViewById(R.id.edtCount);
        edtCount.setText("1");

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productList = DataLocalManager.getListProduct();
                int soluong = Integer.parseInt(edtCount.getText().toString());
                product.setQuantity(soluong);
                productList.add(product);
                DataLocalManager.setListProduct(productList);
//                DataLocalManager.setListProduct(productList);
//                productList = DataLocalManager.getListProduct();
//                productList.add(product);
//                DataLocalManager.setListProduct(productList);
                Toast.makeText(ProDetailActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_LONG).show();
                List<Product> temp = DataLocalManager.getListProduct();
                //Toast.makeText(ProDetailActivity.this, temp.get(0).getName(), Toast.LENGTH_LONG).show();

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