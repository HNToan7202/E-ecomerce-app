package com.example.cozaexpress.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.DataLocal.DataLocalManager;
import com.example.cozaexpress.Database.UserDatabase;
import com.example.cozaexpress.Model.Cart;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.iClickListener;
import com.example.cozaexpress.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    Context context;

    List<Product> products;

    iClickListener listener;

    public CartAdapter(iClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }

    public void loadData(){
        products = UserDatabase.getInstance(context).productDAO().getAll();
    }


    public CartAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, null);
        CartAdapter.MyViewHolder myViewHolder = new CartAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvNameItem.setText(product.getName());
        int gia = (int) (product.getPromotionaprice()* product.getQuantity());
        holder.tvPrice.setText(gia+"");
        holder.tvSoLuong.setText("x"+product.getQuantity().toString());
        Glide.with(context).load(product.getListimage()).into(holder.imgItemCart);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDatabase.getInstance(context).productDAO().delete(product);
                Toast.makeText(context.getApplicationContext(),"Đã xoá", Toast.LENGTH_LONG).show();
                products = UserDatabase.getInstance(context).productDAO().getAll();
                setData(products);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgItemCart, btnDelete;
        TextView tvNameItem, tvPrice, tvSoLuong;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            imgItemCart = itemView.findViewById(R.id.img_item_cart);
            tvNameItem = itemView.findViewById(R.id.tv_name_item_cart);
            tvPrice = itemView.findViewById(R.id.tvPrice_Item);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);

        }
    }
}
