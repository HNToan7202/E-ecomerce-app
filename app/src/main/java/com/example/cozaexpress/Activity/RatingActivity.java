package com.example.cozaexpress.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Model.ImageData;
import com.example.cozaexpress.Model.OrderItem;
import com.example.cozaexpress.Model.Review;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.Utils.RealPathUtil;
import com.example.cozaexpress.api.APIService;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingActivity extends AppCompatActivity {

    private ImageView changeprofilepic, getChangeprofilepic1, getChangeprofilepic2;

    private Uri mainImageURI1 = null;

    boolean IMAGE_STATUS1 = false;

    Button btnChangePic;

    int count = 0;

    RatingBar rate_danhgia;

    LinearLayout lnSubmitDanhGia;

    EditText edtCommentDanhGia;

    List<MultipartBody.Part> mPartList = new ArrayList<>();

    String img= "";

    String listimg = "";

    TextView tvMaDonHang;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        OrderItem orderItem = (OrderItem) bundle.getSerializable("OrderItem");
        tvMaDonHang = findViewById(R.id.tvMaDonHang);
        if(orderItem != null)
        {
            tvMaDonHang.setText(orderItem.getOrder().getId());
        }

        changeprofilepic = findViewById(R.id.flthemHinhAnh1);
        getChangeprofilepic1 = findViewById(R.id.flthemHinhAnh2);
        getChangeprofilepic2 = findViewById(R.id.flthemHinhAnh);

        changeprofilepic.setImageResource(R.drawable.account);
        getChangeprofilepic1.setImageResource(R.drawable.account);
        getChangeprofilepic2.setImageResource(R.drawable.account);

        rate_danhgia = findViewById(R.id.rate_danhgia);

        lnSubmitDanhGia = findViewById(R.id.lnSubmitDanhGia);
        btnChangePic = findViewById(R.id.btn_change_pic);
        edtCommentDanhGia = findViewById(R.id.edtCommentDanhGia);


        lnSubmitDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = edtCommentDanhGia.getText().toString();
                User user = SharedPrefManager.getInstance(RatingActivity.this).getUser();
                Review review = new Review();
                review.setRating((int) rate_danhgia.getRating());
                review.setContent(comment);
                review.setUser(user);
                review.setProduct(orderItem.getProduct());
                String newString = listimg.substring(0, listimg.length() - 1);
                review.setListimage(newString);

                    APIService.apiService.insertReview(review).enqueue(new Callback<Review>() {
                        @Override
                        public void onResponse(Call<Review> call, Response<Review> response) {
                            Toast.makeText(RatingActivity.this, "Đánh giá thành công", Toast.LENGTH_SHORT).show();
                            uploadImg();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Review> call, Throwable t) {
                            Toast.makeText(RatingActivity.this, "Đánh giá thất bại", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
        });




        btnChangePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                Dexter.withContext(RatingActivity.this)
                        .withPermissions(android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                // check if all permissions are granted
                                if (report.areAllPermissionsGranted()) {
                                    // do you work now
                                    bringImagePicker();
                                }

                                // check for permanent denial of any permission
                                if (report.isAnyPermissionPermanentlyDenied()) {
                                    // permission is denied permenantly, navigate user to app settings
                                    Snackbar.make(view, "Kindly grant Required Permission", Snackbar.LENGTH_LONG)
                                            .setAction("Allow", null).show();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        })
                        .onSameThread()
                        .check();

            }
        });



    }
    public void uploadImg(){
        for (MultipartBody.Part part : mPartList) {
            Log.e("img", "hinh: "+ part.body().toString() );
            APIService.apiService.uploadImages(part).enqueue(new Callback<ImageData>() {
                @Override
                public void onResponse(Call<ImageData> call, Response<ImageData> response) {
                    ImageData imageData = response.body();
                    img = imageData.getName();


                    Toast.makeText(RatingActivity.this, "Upload Image Success", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(Call<ImageData> call, Throwable t) {
                    Log.e("TAG", "onFailure: "+ t.getMessage() );
                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mainImageURI1 = result.getUri();


                String strRealPath = RealPathUtil.getRealPath(getApplicationContext(), mainImageURI1); //lấy đường dẫn thực  của ảnh
                Log.e("TAG", strRealPath);
                File file = new File(strRealPath);
                img = APIService.BASE_URL + "images/" + file.getName();

                listimg = listimg + img + ",";

                Log.d("img", "listimg: "+listimg);

                Log.d("img", "onActivityResult: "+ APIService.BASE_URL + "images/" + file.getName());



                RequestBody requestBodyAvt = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part partAvatar = MultipartBody.Part.createFormData("image", file.getName(), requestBodyAvt);

                mPartList.add(partAvatar);

                switch (count){
                    case 0:
                        changeprofilepic.setImageURI(mainImageURI1);
                        mPartList.add(partAvatar);

                        count++;
                        break;
                    case 1:
                        getChangeprofilepic1.setImageURI(mainImageURI1);
                        mPartList.add(partAvatar);
                        count++;
                        break;
                    case 2:
                        getChangeprofilepic2.setImageURI(mainImageURI1);
                        mPartList.add(partAvatar);
                        count++;
                        break;
                }
                Log.e("TAG", "UpdateData: "+ mPartList.get(0).toString());
                IMAGE_STATUS1 = true;


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }
    }

    private void bringImagePicker() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1,1)
                .start(RatingActivity.this);

    }
}