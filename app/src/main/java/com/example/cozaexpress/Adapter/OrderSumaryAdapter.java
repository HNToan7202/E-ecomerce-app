package com.example.cozaexpress.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.R;

import java.util.List;

public class OrderSumaryAdapter extends  RecyclerView.Adapter<OrderSumaryAdapter.MyViewHolder>{


    Context context;

    List<Product> products;


    public OrderSumaryAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }


    @NonNull
    @Override
    public OrderSumaryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_summary, null);
        OrderSumaryAdapter.MyViewHolder myViewHolder = new OrderSumaryAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderSumaryAdapter.MyViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvNameItem.setText(product.getName());
        holder.tvPrice.setText(String.format( "%,.0f",product.getPromotionaprice())+"đ");
        holder.tvSoLuong.setText(product.getQuantity().toString());
        Glide.with(context).load(product.getListPhoto().get(0).getResources()).into(holder.imgItemCart);
        Double price = product.getPrice()*product.getQuantity();
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgItemCart;
        TextView tvNameItem, tvPrice, tvSoLuong;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItemCart = itemView.findViewById(R.id.img_product_cart);
            tvNameItem = itemView.findViewById(R.id.tv_name_cart);
            tvPrice = itemView.findViewById(R.id.tv_price_cart);
            tvSoLuong = itemView.findViewById(R.id.tv_count_item);

        }
    }
}
