package com.example.cozaexpress.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cozaexpress.Model.Order;
import com.example.cozaexpress.R;

import java.util.List;

public class StatusCheckAdapter extends RecyclerView.Adapter<StatusCheckAdapter.MyViewHolder>{

    Context mContext;
    List<Order> orderList;

    public StatusCheckAdapter(Context mContext, List<Order> orderList) {
        this.mContext = mContext;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public StatusCheckAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_order, null);
        StatusCheckAdapter.MyViewHolder myViewHolder = new StatusCheckAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StatusCheckAdapter.MyViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.tvStatusOrder.setText(order.getStatusOrder().toString());
        holder.tvPrice.setText("đ"+order.getPrice().toString());
        holder.tvTotalPrice.setText("đ"+order.getPrice().toString());
    }

    @Override
    public int getItemCount() {
        return orderList == null ? 0 : orderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvStatusOrder, tvPrice, tvTotalPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStatusOrder = itemView.findViewById(R.id.tv_status_order);
            tvPrice = itemView.findViewById(R.id.tv_order_price);
            tvTotalPrice = itemView.findViewById(R.id.order_total_price);
        }
    }
}
