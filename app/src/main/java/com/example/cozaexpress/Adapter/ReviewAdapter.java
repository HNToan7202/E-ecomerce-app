package com.example.cozaexpress.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.Review;
import com.example.cozaexpress.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder>{
    Context context;

    List<Review> reviewList;

    public ReviewAdapter(Context context, List<Review> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }


    @NonNull
    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review,null);
        ReviewAdapter.MyViewHolder myViewHolder = new ReviewAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.MyViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.tvDanhGia.setText(review.getContent());
        holder.tvSao.setText(review.getRating().toString());
    }

    @Override
    public int getItemCount() {
        if(reviewList != null)
        {
            return reviewList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvDanhGia, tvSao;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDanhGia = itemView.findViewById(R.id.tv_rate_reivew);
            tvSao = itemView.findViewById(R.id.tv_sao_review);
        }
    }
}
