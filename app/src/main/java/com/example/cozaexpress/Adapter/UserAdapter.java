package com.example.cozaexpress.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.Model.iClickListener;
import com.example.cozaexpress.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private List<User> userList;

    private iClickListener listener;

    public UserAdapter(iClickListener listener) {
        this.listener = listener;
    }

    public UserAdapter() {
    }

    public void setData(List<User> list){
        this.userList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserAdapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserHolder holder, int position) {

        User user = userList.get(position);
        if (user == null){
            return;
        }
        holder.username.setText(user.getUsername());
        holder.password.setText(user.getPassword());
        holder.btnCapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.updateUser(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(userList!=null){
            return userList.size();
        }
        return 0;
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView password;

        Button btnCapnhat;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            username =itemView.findViewById(R.id.tvUserNameItem);
            password = itemView.findViewById(R.id.tvPassItem);
            btnCapnhat = itemView.findViewById(R.id.btnCapnhat);
        }
    }
}
