package com.example.cozaexpress.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.DataLocal.DataLocalManager;
import com.example.cozaexpress.Model.ImageData;
import com.example.cozaexpress.Model.ImageUpload;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.Utils.RealPathUtil;
import com.example.cozaexpress.api.APIService;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity {
    ImageView btnBackAccount;

    AppCompatButton btnCapNhat;

    ImageView imgProfle;

    int MY_REQUEST_CODE = 10;

    private Uri mUri;

    ProgressDialog mProgressDialog;

    Call<String> response;


    public static final String TAG = SettingActivity.class.getName();

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Log.d(TAG, "onActivityResult");
            if(result.getResultCode() == Activity.RESULT_OK){
                Intent data = result.getData();
                if(data == null){
                    return;
                }
                Uri uri = data.getData();
                mUri = uri;
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imgProfle.setImageBitmap(bitmap);
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btnBackAccount = findViewById(R.id.btnBackAccount);
        imgProfle = findViewById(R.id.img_user_profile);
//        btnLogOut = findViewById(R.id.btnLogOut);

        User user = DataLocalManager.getUser();
        Glide.with(getApplicationContext()).load(user.getAvatar()).into(imgProfle);


        //init progess
        mProgressDialog = new ProgressDialog(this );
        mProgressDialog.setMessage("Vui lòng đợi ...");
        btnBackAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCapNhat = findViewById(R.id.btn_update_profile);
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUri != null){
                    callAPIUpload();
                }
            }
        });

        imgProfle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });

    }

    private void callAPIUpload() {
        mProgressDialog.show();

        String strRealPath = RealPathUtil.getRealPath(this, mUri); //lấy đường dẫn thực

        Log.e("TAG", strRealPath);
        File file = new File(strRealPath);

        RequestBody requestBodyAvt = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part partAvatar = MultipartBody.Part.createFormData("image", file.getName(),requestBodyAvt);
//       APIService.apiService.uploadImages(partAvatar).enqueue(new Callback<ImageUpload>() {
//           @Override
//           public void onResponse(Call<ImageUpload> call, Response<ImageUpload> response) {
//               mProgressDialog.dismiss();
//
//               ImageUpload imageUpload = response.body();
//               imageUpload.getAvatar();
//               Toast.makeText(SettingActivity.this, "Thành Công"+ imageUpload.getAvatar(), Toast.LENGTH_LONG).show();
//           }
//           @Override
//           public void onFailure(Call<ImageUpload> call, Throwable t) {
//               mProgressDialog.dismiss();
//               Toast.makeText(SettingActivity.this, "Thất Bại", Toast.LENGTH_LONG).show();
//
//           }
//       });

        APIService.apiService.uploadImages(partAvatar).enqueue(new Callback<ImageData>() {
            @Override
            public void onResponse(Call<ImageData> call, Response<ImageData> response) {
                mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Thành Công", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ImageData> call, Throwable t) {
                Log.d("TAG", t.getMessage());
                mProgressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Thất Bại", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void onClickRequestPermission() {

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            //ko cần cấp phép
            openGallery();
            return;
        }

        //xin cấp phép thành công
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            openGallery();// mở gallery
        }else{
            //nếu ko tạo cục bộ và xin cấp phép
            String[] permission = {
                    Manifest.permission.READ_EXTERNAL_STORAGE
            };
            requestPermissions(permission, MY_REQUEST_CODE);
        }
    }

    //Lắng nghe người dùng từ chối hoặc đồng ý
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == MY_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //nếu ko thoả -> người dùng từ chối
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }
}