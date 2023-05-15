package com.example.cozaexpress.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.example.cozaexpress.Activity.CheckoutActivity;
import com.example.cozaexpress.Activity.MainActivity;
import com.example.cozaexpress.Adapter.CartAdapter;
//import com.example.cozaexpress.Database.ProductDatabase;
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Database.ProductDatabase;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.Utils.Utils;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {

    ViewPager viewPagerCart;

    View view;

    TabLayout tabLayout;

    RecyclerView rcCart, btnDelete;

    TextView total;

    List<Product> products;

    ConstraintLayout constraintLayout;

    private LottieAnimationView emptycart;

    public static final String GET_CART_ITEMS = "get_cart_items";

    public static final String GET_SUM   = "get_sum";

    public static final String GET_BUNDLE   = "get_BUNDLE";

    Double sum = 0.0;

    AppCompatButton btnContinue;

    CartAdapter cartAdapter;
    private Double loaddata(Double data){
        return data;
    }

    Double tongTiensp = 0.0;

    CheckBox checkAll;

    private boolean isAllChecked = false;

    List<Product> listPayment = new ArrayList<>();

    private void setData(List<Product> products) {
        cartAdapter.setData(products);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(products == null){
            loaddata(sum);
        }
    }

    private void tinhTongTien(){
        for(int i = 0;i < Utils.manggiohang.size();i++){
            tongTiensp += Utils.manggiohang.get(i).getPromotionaprice()*Utils.manggiohang.get(i).getQuantity();
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            total.setText(decimalFormat.format(tongTiensp) + "đ");
        }
    }



    //Hàm trả về view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        //AnhXa();
        rcCart = view.findViewById(R.id.rc_item_cart);
        total = view.findViewById(R.id.tvTotal_cart);
        emptycart = view.findViewById(R.id.empty_cart);
        btnContinue = view.findViewById(R.id.btn_continue);
        constraintLayout = view.findViewById(R.id.constraintLayoutPayment);

        products = ProductDatabase.getInstance(getContext()).productDAO().getAll();

        if(products.size() == 0){
            emptycart.setVisibility(View.VISIBLE);
            constraintLayout.setVisibility(View.INVISIBLE);
        }


//        for(int i = 0 ; i <products.size();i++){
//            sum += products.get(i).getPromotionaprice()*products.get(i).getQuantity();
//        }
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = SharedPrefManager.getInstance(getContext()).getUser();
                if(user!=null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(GET_CART_ITEMS, (Serializable) listPayment);
                    bundle.putDouble(GET_SUM, sum);
                    Intent intent1 = new Intent(getContext(), CheckoutActivity.class);
                    intent1.putExtra(GET_BUNDLE,bundle);
                    startActivity(intent1);
                }
                else{
                    Toast.makeText(getContext(),"Bạn cần đăng nhập để thanh toán",Toast.LENGTH_LONG).show();
                }

            }
        });

        total.setText(String.format( "%,.0f" +"đ",sum));

        Double price = 0.0;

        cartAdapter = new CartAdapter(getContext(), products, new CartAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int position) {
                // Xóa sản phẩm khỏi giỏ hàng
                Product productToRemove = products.get(position);
                ProductDatabase.getInstance(getContext()).productDAO().delete(productToRemove);

                // Cập nhật giá trị tổng tiền
                sum -= productToRemove.getPromotionaprice() * productToRemove.getQuantity();
                total.setText(String.format("%,.0f" + "đ", sum));

                // Kiểm tra nếu giỏ hàng trống thì hiển thị thông báo
                if (sum == 0) {
                    emptycart.setVisibility(View.VISIBLE);
                    constraintLayout.setVisibility(View.INVISIBLE);
                }

                // Cập nhật lại danh sách sản phẩm trong giỏ hàng
                products = ProductDatabase.getInstance(getContext()).productDAO().getAll();
                setData(products);
            }

            @Override
            public void onItemDercrement(int position) {
                // Giảm số lượng sản phẩm
                Product productToDercrement = products.get(position);
                int quantity = productToDercrement.getQuantity() - 1;

                // Nếu số lượng sản phẩm bằng 0 thì xóa sản phẩm khỏi giỏ hàng
                if (quantity == 0) {
                    ProductDatabase.getInstance(getContext()).productDAO().delete(productToDercrement);
                    products.remove(productToDercrement);
                } else {
                    productToDercrement.setQuantity(quantity);
                    ProductDatabase.getInstance(getContext()).productDAO().update(productToDercrement);
                }

                // Cập nhật giá trị tổng tiền
                sum -= productToDercrement.getPromotionaprice();
                total.setText(String.format("%,.0f" + "đ", sum));

                // Kiểm tra nếu giỏ hàng trống thì hiển thị thông báo
                if (sum == 0) {
                    emptycart.setVisibility(View.VISIBLE);
                    constraintLayout.setVisibility(View.INVISIBLE);
                }

                // Cập nhật lại danh sách sản phẩm trong giỏ hàng
                products = ProductDatabase.getInstance(getContext()).productDAO().getAll();
                setData(products);

            }

            @Override
            public void onItemIncrement(int position) {
                // Tăng số lượng sản phẩm
                Product productToIncrement = products.get(position);
                int quantity = productToIncrement.getQuantity() + 1;
                productToIncrement.setQuantity(quantity);
                ProductDatabase.getInstance(getContext()).productDAO().update(productToIncrement);

                // Cập nhật giá trị tổng tiền
                sum += productToIncrement.getPromotionaprice();
                total.setText(String.format("%,.0f" + "đ", sum));

                // Cập nhật lại danh sách sản phẩm trong giỏ hàng
                products = ProductDatabase.getInstance(getContext()).productDAO().getAll();
                setData(products);
            }

            @Override
            public void onCheckBoxClick(int position, boolean isChecked) {
                if(isChecked){
                    sum += products.get(position).getPromotionaprice()*products.get(position).getQuantity();
                    listPayment.add(products.get(position));
                    total.setText(String.format("%,.0f" + "đ", sum));
                }else{
                    sum -= products.get(position).getPromotionaprice()*products.get(position).getQuantity();
                    total.setText(String.format("%,.0f" + "đ", sum));
                    listPayment.remove(products.get(position));
                }
            }

        });
        cartAdapter.setData(products);
        rcCart.setHasFixedSize(false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rcCart.setLayoutManager(layoutManager);
        rcCart.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
        return view;
    }
}
