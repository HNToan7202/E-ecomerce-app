package com.example.cozaexpress.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.Activity.OrderItemActivity;
import com.example.cozaexpress.Model.Order;
import com.example.cozaexpress.R;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder>{

    Context context;
    List<Order> array;
    public OrderAdapter(Context context, List<Order> array) {
        this.context = context;
        this.array =array;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Order order = array.get(position);
        if(order != null){
            holder.id = order.getId();
            holder.Status =order.getStatusOrder().toString();
//            holder.price.setText(order.getPrice().toString());
            holder.createAt.setText(order.getCreateat());
            Glide.with(context).load(order.getUser().getAvatar()).into(holder.images);
            holder.price.setText(String.format( "%,.0f",order.getPrice())+ "đ");

        }
    }

    @Override
    public int getItemCount() {
        return array == null ? 0 : array.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView price,createAt;
        public ImageView images;
        private String id,Status;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            price = (TextView) itemView.findViewById(R.id.tvPriceProduct);
            createAt = (TextView) itemView.findViewById(R.id.tvDateOrder);
            images =(ImageView) itemView.findViewById(R.id.imgOrderProduct);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("OrderId", id);
                    bundle.putString("Status", Status);
                    Intent intent = new Intent(context, OrderItemActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
