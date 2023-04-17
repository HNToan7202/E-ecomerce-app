package com.example.cozaexpress.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.cozaexpress.DataLocal.DataLocalManager;
import com.example.cozaexpress.Database.UserDatabase;
import com.example.cozaexpress.Model.Cart;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.iClickListener;
import com.example.cozaexpress.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    Context context;

    List<Product> products;

    iClickListener listener;

    public Double sum = 0.0;


    public CartAdapter(iClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }

    public void loadData(){
        products = UserDatabase.getInstance(context).productDAO().getAll();
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
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDatabase.getInstance(context).productDAO().delete(product);
                Toast.makeText(context.getApplicationContext(),"Đã xoá", Toast.LENGTH_LONG).show();
                products = UserDatabase.getInstance(context).productDAO().getAll();
                setData(products);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgItemCart, btnDelete;
        TextView tvNameItem, tvPrice, tvSoLuong;

        CheckBox cbCheck;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnDelete = itemView.findViewById(R.id.btn_delete_item);
            imgItemCart = itemView.findViewById(R.id.img_product_cart);
            tvNameItem = itemView.findViewById(R.id.tv_name_cart);
            tvPrice = itemView.findViewById(R.id.tv_price_cart);
            tvSoLuong = itemView.findViewById(R.id.tv_count_item);
            cbCheck = itemView.findViewById(R.id.cBCheck);
        }
    }
}
