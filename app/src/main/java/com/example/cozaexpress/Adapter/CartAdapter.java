package com.example.cozaexpress.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.Activity.CartActivity;
import com.example.cozaexpress.Activity.MainActivity;
import com.example.cozaexpress.Database.ProductDatabase;
import com.example.cozaexpress.Database.UserDatabase;
import com.example.cozaexpress.Fragment.CartFragment;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.iClickListener;
import com.example.cozaexpress.R;
import com.example.cozaexpress.Utils.Utils;
import com.google.common.eventbus.EventBus;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    Context context;

    List<Product> products;

    iClickListener listener;

    public Double sum = 0.0;

    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemDercrement(int position);
        void onItemIncrement(int position);
        void onCheckBoxClick(int position, boolean isChecked);

//        onItemClick: được gọi khi người dùng nhấn vào sản phẩm trong giỏ hàng để xóa.
//        onItemDecrement: được gọi khi người dùng giảm số lượng sản phẩm.
//        onItemIncrement: được gọi khi người dùng tăng số lượng sản phẩm.
    }


    public CartAdapter(Context context, List<Product> products, OnItemClickListener listener) {
        this.context = context;
        this.products = products;
        this.mListener = listener;
    }

    public void setData(List<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }

    public void loadData(){
        products = ProductDatabase.getInstance(context).productDAO().getAll();
    }

    public CartAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, null);
        CartAdapter.MyViewHolder myViewHolder = new CartAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvNameItem.setText(product.getName());
        holder.tvPrice.setText(String.format( "%,.0f",product.getPromotionaprice())+"đ");
        holder.tvSoLuong.setText(product.getQuantity().toString());
        Glide.with(context).load(product.getListPhoto().get(0).getResources()).into(holder.imgItemCart);
        Double price = product.getPromotionaprice()*product.getQuantity();
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.onItemClick(position);

                }
                ProductDatabase.getInstance(context).productDAO().delete(product);
                Toast.makeText(context.getApplicationContext(),"Đã xoá", Toast.LENGTH_LONG).show();
                products = ProductDatabase.getInstance(context).productDAO().getAll();
                setData(products);
            }
        });

        holder.cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(mListener != null){
                    mListener.onCheckBoxClick(position, b);
                }
            }
        });

        holder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onItemIncrement(position);
                }
            }
        });
        holder.decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onItemDercrement(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgItemCart, btnDelete, increment, decrement;
        TextView tvNameItem, tvPrice, tvSoLuong;
        CheckBox cbCheck;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            increment = itemView.findViewById(R.id.increase_product);
            decrement = itemView.findViewById(R.id.decrease_product);
            btnDelete = itemView.findViewById(R.id.btn_delete_item);
            imgItemCart = itemView.findViewById(R.id.img_product_cart);
            tvNameItem = itemView.findViewById(R.id.tv_name_cart);
            tvPrice = itemView.findViewById(R.id.tv_price_cart);
            tvSoLuong = itemView.findViewById(R.id.tv_count_item);
            cbCheck = itemView.findViewById(R.id.cBCheck);
        }
    }
}
