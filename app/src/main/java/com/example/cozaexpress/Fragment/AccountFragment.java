package com.example.cozaexpress.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.Activity.HelpActivity;
import com.example.cozaexpress.Activity.LoginActivity;
import com.example.cozaexpress.Activity.PolicyActivity;
import com.example.cozaexpress.Activity.ProfileActivity;
import com.example.cozaexpress.Activity.ReviewsActivity;
import com.example.cozaexpress.Activity.ShipperActivity;
import com.example.cozaexpress.Activity.SignInActivity;
import com.example.cozaexpress.Activity.StatusOrderActivity;
import com.example.cozaexpress.Activity.ViewCurrentActivity;
import com.example.cozaexpress.Activity.WhishlistActivity;
import com.example.cozaexpress.DataLocal.DataLocalManager;
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Model.StatusOrder;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.Activity.SettingActivity;

public class AccountFragment extends Fragment implements View.OnClickListener {
    View view;

    TextView tvViewAllOrder;

    ImageView btnSetting, wishList;

    AppCompatButton btnLogOut, btnLogin;

    ImageView imgProfile;

    TextView tvUserName;

    ConstraintLayout constraint_profile;

    RelativeLayout empty_profile;

    LinearLayout action_cho_xac_nhan, action_dang_giao, action_bi_huy, action_thanh_cong,action_rate, action_viewcurrent, action_profile, action_address;

    LinearLayout action_policy, action_help, action_message;


    //Hàm trả về view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        AnhXa();

        return view;
    }

    private void AnhXa() {

        tvViewAllOrder = view.findViewById(R.id.tvViewAllOrder);
        action_cho_xac_nhan = view.findViewById(R.id.action_cho_xac_nhan);
        action_dang_giao = view.findViewById(R.id.action_dang_giao);
        action_bi_huy = view.findViewById(R.id.action_da_huy);
        action_thanh_cong = view.findViewById(R.id.action_thanh_cong);
        action_rate = view.findViewById(R.id.action_rate);
        action_viewcurrent = view.findViewById(R.id.action_viewcurrent);
        action_profile = view.findViewById(R.id.action_profile);
        action_address = view.findViewById(R.id.action_address);

        imgProfile = view.findViewById(R.id.img_profile_account);
        tvUserName = view.findViewById(R.id.tvName);
        User user = SharedPrefManager.getInstance(getContext()).getUser();
        wishList = view.findViewById(R.id.wishList);
        btnLogin = view.findViewById(R.id.btnLogin);

        //Chính sách mua hàng
        action_policy = view.findViewById(R.id.action_policy);
        action_policy.setOnClickListener(this);

        //Trợ giúp
        action_help = view.findViewById(R.id.action_help);
        action_help.setOnClickListener(this);

        //Chat với fanpage
        action_message = view.findViewById(R.id.action_message);
        action_message.setOnClickListener(this);


        constraint_profile = view.findViewById(R.id.layout_profile);
        empty_profile = view.findViewById(R.id.empty_profile);

        tvViewAllOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), StatusOrderActivity.class);
                startActivity(i);
            }
        });

        action_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = SharedPrefManager.getInstance(getContext()).getUser();
                if(user!= null){
                    Intent i = new Intent(getContext(), ShipperActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getContext(),"Bạn cần đăng nhập để xem",Toast.LENGTH_LONG).show();
                }

            }
        });
        action_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = SharedPrefManager.getInstance(getContext()).getUser();
                if(user!= null){
                    Intent i = new Intent(getContext(), ProfileActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getContext(),"Bạn cần đăng nhập để xem",Toast.LENGTH_LONG).show();
                }

            }
        });
        action_viewcurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ViewCurrentActivity.class);
                startActivity(i);
            }
        });
        action_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ReviewsActivity.class);
                startActivity(i);
            }
        });

        action_cho_xac_nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = SharedPrefManager.getInstance(getContext()).getUser();
                if(user!= null){
                    Intent i = new Intent(getContext(), StatusOrderActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getContext(),"Bạn cần đăng nhập để xem",Toast.LENGTH_LONG).show();
                }

            }
        });

        action_dang_giao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = SharedPrefManager.getInstance(getContext()).getUser();
                if(user!= null){
                    Intent i = new Intent(getContext(), StatusOrderActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("status", String.valueOf(StatusOrder.DANGGIAO));
                    i.putExtras(bundle);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getContext(),"Bạn cần đăng nhập để xem",Toast.LENGTH_LONG).show();
                }

            }
        });

        action_thanh_cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = SharedPrefManager.getInstance(getContext()).getUser();
                if(user!= null){
                    Intent i = new Intent(getContext(), StatusOrderActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("status", String.valueOf(StatusOrder.DANGGIAO));
                    i.putExtras(bundle);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getContext(),"Bạn cần đăng nhập để xem",Toast.LENGTH_LONG).show();
                }

            }
        });

        action_bi_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = SharedPrefManager.getInstance(getContext()).getUser();
                if(user!= null){
                    Intent i = new Intent(getContext(), StatusOrderActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("status", String.valueOf(StatusOrder.DANGGIAO));
                    i.putExtras(bundle);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getContext(),"Bạn cần đăng nhập để xem",Toast.LENGTH_LONG).show();
                }

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
                startActivity(new Intent(getContext(), SignInActivity.class));
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

    private void openFacebookMessenger(Context context) {
        String packageName = "com.facebook.orca"; // Gói của ứng dụng Facebook Messenger

        try {
            // Kiểm tra xem ứng dụng Messenger đã được cài đặt hay chưa
            PackageManager packageManager = context.getPackageManager();
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);

            // Nếu đã cài đặt, mở ứng dụng Messenger
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://m.me/hoangtoan.nguyen.370")); // Đặt URI cho chat với người dùng bất kỳ
            intent.setPackage(packageName);
            context.startActivity(intent);
        } catch (PackageManager.NameNotFoundException e) {
            // Nếu ứng dụng Messenger chưa được cài đặt, hãy thông báo cho người dùng
            Toast.makeText(context, "Ứng dụng Facebook Messenger chưa được cài đặt", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {
            switch (view.getId()){
                    case R.id.action_policy:
                        startActivity(new Intent(getContext(), PolicyActivity.class));
                        break;
                    case R.id.action_help:
                        startActivity(new Intent(getContext(), HelpActivity.class));
                        break;
                    case R.id.action_message:
                        openFacebookMessenger(getContext());
                        break;

            }
    }
}
