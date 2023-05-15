package com.example.cozaexpress.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.Activity.OrderItemActivity;
import com.example.cozaexpress.Activity.ProDetailActivity;
import com.example.cozaexpress.Activity.RatingActivity;
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Model.OrderItem;
import com.example.cozaexpress.Model.Review;
import com.example.cozaexpress.Model.StatusOrder;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.api.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.MyViewHolder>{

    Context context;
    List<OrderItem> array;

    List<Review> rev;

    public OrderItemAdapter(Context context, List<OrderItem> array) {
        this.context = context;
        this.array =array;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_product,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OrderItem orderitem = array.get(position);
        holder.id= orderitem.getProduct().getId();
        holder.tenSp.setText(orderitem.getProduct().getName());
        holder.SalePrice.setText(String.format( "%,.0f",orderitem.getProduct().getPromotionaprice())+ "đ");

        StatusOrder statusOrder = orderitem.getOrder().getStatusOrder();

        User user = SharedPrefManager.getInstance(context).getUser();

        APIService.apiService.getReviewByUser(user).enqueue(new Callback<List<Review>>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if(response.isSuccessful()){
                    rev = response.body();
                    for (Review review: rev) {
                        if(review.getProduct().getId().equals(orderitem.getProduct().getId())){
                            holder.btndanhgiasp.setVisibility(View.GONE);
                            holder.xemdg.setVisibility(View.VISIBLE);
                            holder.xemdg.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(context, RatingActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("review",review);
                                    intent.putExtras(bundle);
                                    context.startActivity(intent);
                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {

            }
        });


        if(statusOrder == StatusOrder.DAGIAO){
            holder.btndanhgiasp.setVisibility(View.VISIBLE);
        }

        holder.count.setText(orderitem.getCount().toString());
        holder.price.setText(orderitem.getProduct().getPrice().toString());
        holder.price.setPaintFlags(holder.SalePrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Glide.with(context).load(orderitem.getProduct().getListPhoto().get(0).getResources()).into(holder.images);

        holder.btndanhgiasp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Khi bấm vào nút đánh giá sản phẩm thì sẽ chuyển sang màn hình đánh giá sản phẩm
                Intent intent = new Intent(context, RatingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("OrderItem", orderitem);
                //bundle.putString("OrderId", id);
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
        public ImageView images;
        public TextView tenSp,count, price, SalePrice;

        public  String id;

        Button btndanhgiasp, xemdg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            images = (ImageView) itemView.findViewById(R.id.imgOrder);
            tenSp = (TextView) itemView.findViewById(R.id.tvNameProduct);
            count = (TextView) itemView.findViewById(R.id.tvCountOrder);
            price = (TextView) itemView.findViewById(R.id.PriceProductNoSale);
            SalePrice = (TextView) itemView.findViewById(R.id.PriceProductSale);
            btndanhgiasp = (Button) itemView.findViewById(R.id.btndanhgiasp);
            xemdg = (Button) itemView.findViewById(R.id.xemdg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    bundle.putString("ProductId", id);
                    Intent intent = new Intent(context, ProDetailActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
