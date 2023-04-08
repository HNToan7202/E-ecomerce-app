package com.example.cozaexpress.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.Activity.ProDetailActivity;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.R;


import java.util.List;

public class LastProductAdapter extends RecyclerView.Adapter<LastProductAdapter.MyViewHolder> {
    Context context;
    List<Product> array;

    public LastProductAdapter(Context context, List<Product> array){
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product lastProduct = array.get(position);
        holder.tenSp.setText(lastProduct.getName());
        holder.id = lastProduct.getId();
        Glide.with(context)
                .load(lastProduct.getListimage())
                .into(holder.images);
        holder.tvGia.setText(lastProduct.getPrice().toString());
    }

    @Override
    public int getItemCount() {
        return array == null ? 0: array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView tenSp;
        public TextView tvGia;

        private String id;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            images = (ImageView) itemView.findViewById(R.id.imgProduct);
            tenSp = (TextView) itemView.findViewById(R.id.tvTenSp);
            tvGia = itemView.findViewById(R.id.tvGia);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("idProduct", id);
                    Intent intent = new Intent(context, ProDetailActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

        }
    }



}