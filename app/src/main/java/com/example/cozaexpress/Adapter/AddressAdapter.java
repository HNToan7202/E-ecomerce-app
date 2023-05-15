package com.example.cozaexpress.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cozaexpress.Model.Delivery;
import com.example.cozaexpress.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder>{
    List<Delivery> deliveries;
    Context mContext;

    public AddressAdapter(List<Delivery> deliveries, Context mContext) {
        this.deliveries = deliveries;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AddressAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_address,null);
        AddressAdapter.MyViewHolder myViewHolder = new AddressAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.MyViewHolder holder, int position) {
        Delivery delivery = deliveries.get(position);
        holder.tvName.setText(delivery.getUser().getFullName());
        holder.tvPhone.setText(delivery.getUser().getPhone());
        holder.tvAddress.setText(delivery.getAddress());

    }

    @Override
    public int getItemCount() {
        return deliveries != null ? deliveries.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone, tvAddress;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAddress =itemView.findViewById(R.id.tvNameAddress);
            tvPhone = itemView.findViewById(R.id.tvPhone_Address);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        }
    }
}
