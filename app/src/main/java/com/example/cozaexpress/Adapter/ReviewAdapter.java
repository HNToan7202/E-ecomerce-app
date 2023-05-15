package com.example.cozaexpress.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.Model.Photo;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.Review;
import com.example.cozaexpress.R;

import java.text.SimpleDateFormat;
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
        //holder.imgFeedback1.setText(review.getNameUser());
        holder.tvNameUser.setText(review.getUser().getFullName());
        holder.tvDateFeedback.setText(review.getCreateat());
        holder.tvFeedback.setText(review.getContent());

        if(review.getListimage() != null && review.getListimage().length() > 5)
        {
            List<Photo> photoList = review.getListPhoto();

            switch (photoList.size()){
                case 1:
                    Glide.with(context)
                            .load(photoList.get(0).getResources())
                            .into(holder.imgFeedback1);
                    break;
                case 2:
                    Glide.with(context)
                            .load(photoList.get(0).getResources())
                            .into(holder.imgFeedback1);
                    Glide.with(context)
                            .load(photoList.get(1).getResources())
                            .into(holder.imgFeedback2);
                    break;
                case 3:
                    Glide.with(context)
                            .load(photoList.get(0).getResources())
                            .into(holder.imgFeedback1);
                    Glide.with(context)
                            .load(photoList.get(1).getResources())
                            .into(holder.imgFeedback2);
                    Glide.with(context)
                            .load(photoList.get(2).getResources())
                            .into(holder.imgFeedback3);
                    break;
            }
        }
        else {
            holder.imgFeedBack.setVisibility(View.GONE);
        }

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
        LinearLayout imgFeedBack;

        TextView tvNameUser, tvDateFeedback, tvFeedback, tvLike;
        ImageView imgFeedback1, imgFeedback2, imgFeedback3, imgLike;
        RatingBar rate;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        boolean isLiked = false;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            anhXa();
            setOnClick();

        }

        private void setOnClick() {
            imgLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isLiked) {
                        imgLike.setBackgroundResource(R.drawable.img_icon_like);
                        int like = Integer.parseInt(tvLike.getText().toString());
                        like = like - 1;
                        tvLike.setText(String.valueOf(like));
                        isLiked = false;
                    } else {
                        imgLike.setBackgroundResource(R.drawable.img_icon_liked);
                        int like = Integer.parseInt(tvLike.getText().toString());
                        like = like + 1;
                        tvLike.setText(String.valueOf(like));
                        isLiked = true;
                    }
                }
            });
        }

        private void anhXa() {
            imgFeedBack = itemView.findViewById(R.id.imgFeedBack);
            tvNameUser = itemView.findViewById(R.id.tvNameUser);
            tvDateFeedback = itemView.findViewById(R.id.tvDateFeedback);
            tvFeedback = itemView.findViewById(R.id.tvFeedback);
            tvLike = itemView.findViewById(R.id.tvLike);
            imgFeedback1 = itemView.findViewById(R.id.imgFeedback1);
            imgFeedback2 = itemView.findViewById(R.id.imgFeedback2);
            imgFeedback3 = itemView.findViewById(R.id.imgFeedback3);
            imgLike = itemView.findViewById(R.id.imgLike);
            rate = itemView.findViewById(R.id.rate);
        }

    }

}
