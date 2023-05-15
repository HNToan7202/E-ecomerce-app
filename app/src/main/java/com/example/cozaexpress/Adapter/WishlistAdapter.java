package com.example.cozaexpress.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.Activity.ProDetailActivity;
import com.example.cozaexpress.Model.Photo;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.R;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.MyViewHolder>{

    Context mContext;
    List<Product> productList;

    public WishlistAdapter(Context mContext, List<Product> productList) {
        this.mContext = mContext;
        this.productList = productList;
    }

    @NonNull
    @Override
    public WishlistAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, null);
        WishlistAdapter.MyViewHolder myViewHolder = new WishlistAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistAdapter.MyViewHolder holder, int position) {
        Product lastProduct = productList.get(position);
        holder.tenSp.setText(lastProduct.getName());
        holder.id = lastProduct.getId();
        holder.tvRating.setText(lastProduct.getRating().toString());
        holder.count.setText(lastProduct.getSold().toString());
        holder.tvGiaChuaGiam.setText(String.format( "%,.0f",lastProduct.getPrice())+ "đ");
        holder.tvGiaChuaGiam.setPaintFlags(holder.tvGiaChuaGiam.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Photo img = lastProduct.getListPhoto().get(0);
        Glide.with(mContext)
                .load(img.getResources())
                .into(holder.images);
        holder.tvGia.setText(String.format( "%,.0f",lastProduct.getPromotionaprice())+ "đ");
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0: productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView images;
        public TextView tenSp;
        public TextView tvGia, tvGiaChuaGiam;
        TextView tvRating;
        private String id;
        TextView count;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            images = (ImageView) itemView.findViewById(R.id.imgProduct);
            tenSp = (TextView) itemView.findViewById(R.id.tvTenSp);
            tvGia = itemView.findViewById(R.id.tvGia);
            tvRating = itemView.findViewById(R.id.tvSao);
            count = itemView.findViewById(R.id.tvLuotMua);
            tvGiaChuaGiam = itemView.findViewById(R.id.tvGiaChuaGiam);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("idProduct", id);
                    Intent intent = new Intent(mContext, ProDetailActivity.class);
                    intent.putExtras(bundle);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity (intent);
                }
            });

        }
    }
}
