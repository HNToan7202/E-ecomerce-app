package com.example.cozaexpress.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.Activity.LoginActivity;
import com.example.cozaexpress.Activity.ProfileActivity;
import com.example.cozaexpress.Activity.StatusOrderActivity;
import com.example.cozaexpress.Activity.WhishlistActivity;
import com.example.cozaexpress.DataLocal.DataLocalManager;
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.Activity.SettingActivity;

public class AccountFragment extends Fragment {
    View view;

    ImageView btnSetting, wishList;

    AppCompatButton btnLogOut, btnLogin;

    ImageView imgProfile;

    TextView tvUserName;

    ConstraintLayout constraint_profile;

    RelativeLayout empty_profile;

    LinearLayout action_cho_xac_nhan, action_dang_giao, action_bi_huy, action_thanh_cong;


    //Hàm trả về view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        AnhXa();

        return view;
    }

    private void AnhXa() {

        action_cho_xac_nhan = view.findViewById(R.id.action_cho_xac_nhan);

        imgProfile = view.findViewById(R.id.img_profile_account);
        tvUserName = view.findViewById(R.id.tvName);
        User user = SharedPrefManager.getInstance(getContext()).getUser();
        wishList = view.findViewById(R.id.wishList);
        btnLogin = view.findViewById(R.id.btnLogin);

        constraint_profile = view.findViewById(R.id.layout_profile);
        empty_profile = view.findViewById(R.id.empty_profile);

        action_cho_xac_nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), StatusOrderActivity.class);
                startActivity(i);
            }
        });

        if(user!= null){
            Log.d("FULL", user.getFullName());
            tvUserName.setText(user.getUsername());
            Glide.with(getContext()).load(user.getAvatar()).into(imgProfile);
        }

        btnLogOut = view.findViewById(R.id.btnLogOut);

        if(SharedPrefManager.getInstance(getContext()).isLoggedIn()){
            btnLogOut.setVisibility(View.VISIBLE);
            constraint_profile.setVisibility(View.VISIBLE);
            empty_profile.setVisibility(View.GONE);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });


        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XacNhanXoa();
            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ProfileActivity.class));
            }
        });
        wishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), WhishlistActivity.class);
                startActivity(i);
            }
        });
    }

    private void XacNhanXoa(){
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Thông báo");
        alert.setMessage("Bạn có muốn đăng xuất không");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //lệnh nút có
                SharedPrefManager.getInstance(getContext()).logout();
                constraint_profile.setVisibility(View.GONE);
                empty_profile.setVisibility(View.VISIBLE);
                btnLogOut.setVisibility(View.INVISIBLE);
                //DataLocalManager.loggout();

            }
        });
        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //lệnh nút không
            }
        });
        alert.show();
    }
    
}
