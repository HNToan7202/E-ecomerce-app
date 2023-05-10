package com.example.cozaexpress.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.Model.Category;
import com.example.cozaexpress.R;

import java.util.List;

public class Category1Adapter extends RecyclerView.Adapter<Category1Adapter.MyViewHolder>{

    private static final int VIEW_TYPE_COUNT = 1; // Số lượng view type
    Context context;
    List<Category> array;
    private OnItemClickListener listener;

    private int selectedItem = -1;

    Category category;

    public Category1Adapter(Context context, List<Category> array) {
        this.context = context;
        this.array =array;
    }
    public Category1Adapter(Context context, List<Category> array,OnItemClickListener listener) {
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
                .inflate(R.layout.item_catrgory_product,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        category = array.get(position);
        holder.tenSp.setText(category.getName());
        // Set background color based on selected item
        if (position == selectedItem) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.selected_item_color));
            holder.tenSp.setTextColor(ContextCompat.getColor(context, R.color.white));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.grey_light));
            holder.tenSp.setTextColor(ContextCompat.getColor(context, R.color.black));
        }

        holder.itemView.setOnClickListener(v -> {
            // Update selected item index
            selectedItem = position;

            // Call click listener
            if (listener != null) {
                listener.onItemClick(position, category.getName());
            }
            Toast.makeText(context, "Bạn đã chọn category" + holder.tenSp.getText().toString(), Toast.LENGTH_SHORT).show();
            if (listener != null) {
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position, holder.tenSp.getText().toString().trim());
                }
            }
            // Notify adapter of data changes
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return array == null ? 0 : array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tenSp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setLayoutParams(new RecyclerView.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT, 60));

            tenSp = (TextView) itemView.findViewById(R.id.tvName_category);
        }
    }

}

