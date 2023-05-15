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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.Activity.DetailNotificationActivity;
import com.example.cozaexpress.Model.Category;
import com.example.cozaexpress.Model.Order;
import com.example.cozaexpress.R;

import java.util.List;

public class DangGiaoAdapter extends RecyclerView.Adapter<DangGiaoAdapter.MyViewHolder>{

    private static final int VIEW_TYPE_COUNT = 1; // Số lượng view type
    Context context;
    List<Order> array;
    private OnItemClickListener listener;

    private int selectedItem = -1;

    public static final String ORDER = "order";

    Order order;

    public DangGiaoAdapter(Context context, List<Order> array) {
        this.context = context;
        this.array =array;
    }
    public DangGiaoAdapter(Context context, List<Order> array, OnItemClickListener listener) {
        this.context = context;
        this.array =array;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position,String nameCate);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        order = array.get(position);
        holder.tvIdOrder.setText(order.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailNotificationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ORDER, order);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return array == null ? 0 : array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvIdOrder;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdOrder = (TextView) itemView.findViewById(R.id.tvIdOrder);

        }
    }

}
