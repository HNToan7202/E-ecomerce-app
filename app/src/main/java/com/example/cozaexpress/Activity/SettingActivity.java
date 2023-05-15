package com.example.cozaexpress.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cozaexpress.DataLocal.DataLocalManager;
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Model.ImageData;
import com.example.cozaexpress.Model.ImageUpload;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.R;
import com.example.cozaexpress.Utils.CheckInternetConnection;
import com.example.cozaexpress.Utils.RealPathUtil;
import com.example.cozaexpress.api.APIService;
import com.google.android.material.snackbar.Snackbar;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.apache.log4j.chainsaw.Main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity {
    private ImageView changeprofilepic;

    private Uri mainImageURI = null;

    private EditText edtname,edtemail,edtmobile;

    CircleImageView primage;
    boolean IMAGE_STATUS = false;

    private Button updateProfileBtn;

    private String name,email,photo,mobile,newemail;

    private String check;

    private Uri mUri;

    ImageData imageData;

    String imgURL;

    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        AnhXa();

        changeprofilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                Dexter.withContext(SettingActivity.this)
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

        updateProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* */

                if (validateName() && validateEmail() && validateNumber()) {

                    final KProgressHUD progressDialog = KProgressHUD.create(SettingActivity.this)
                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                            .setLabel("Please wait")
                            .setCancellable(false)
                            .setAnimationSpeed(2)
                            .setDimAmount(0.5f)
                            .show();

                    name = edtname.getText().toString();
                    email = edtemail.getText().toString();
                    mobile = edtmobile.getText().toString();


                    //UploadUser(user);
                    user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
                    user.setFullName(name);
                    user.setEmail(email);
                    user.setPhone(mobile);

                    // If Profile Picture is NOT CHANGED
                    if(mainImageURI==null) {
                        // update data only
                        //  Toast.makeText(UpdateDataActivity.this, "Img Not Updated", Toast.LENGTH_SHORT).show();}
                        APIService.apiService.updateUser(user).enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                progressDialog.dismiss();
                                //mProgressDialog.dismiss();
                                //UploadData();
                                if(response.body()!=null){
                                    Toasty.success(SettingActivity.this, "Profile Updated Successfully! Please Login again.", 2000).show();
                                    //SharedPrefManager.getInstance(getApplicationContext()).setUser(response.body());
                                    SharedPrefManager.getInstance(getApplicationContext()).logout();
                                    Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                                else{
                                    Toasty.error(SettingActivity.this, "Something went wrong! Please try again.", 2000).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                progressDialog.dismiss();
                                Log.d("TAG", t.getMessage());
                                Toast.makeText(getApplicationContext(), "Thất Bại", Toast.LENGTH_LONG).show();
                            }
                        });

                    }else {
                        String strRealPath = RealPathUtil.getRealPath(getApplicationContext(), mainImageURI); //lấy đường dẫn thực  của ảnh
                        Log.e("TAG", strRealPath);
                        File file = new File(strRealPath);

                        RequestBody requestBodyAvt = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part partAvatar = MultipartBody.Part.createFormData("image", file.getName(),requestBodyAvt);
                        APIService.apiService.uploadImages(partAvatar).enqueue(new Callback<ImageData>() {
                            @Override
                            public void onResponse(Call<ImageData> call, Response<ImageData> response) {

                                imageData = response.body();

                                imgURL = APIService.BASE_URL+"images/"+ imageData.getName();
                                Log.d("IMG", "onResponse: "+ imgURL);

                                user.setAvatar(imgURL);

                                APIService.apiService.updateUser(user).enqueue(new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        progressDialog.dismiss();
                                        //mProgressDialog.dismiss();
                                        //UploadData();
                                        if(response.body()!=null){
                                            Toasty.success(SettingActivity.this, "Profile Updated Successfully! Please Login again.", 2000).show();
                                            //SharedPrefManager.getInstance(getApplicationContext()).setUser(response.body());
                                            SharedPrefManager.getInstance(getApplicationContext()).logout();
                                            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }
                                        else{
                                            Toasty.error(SettingActivity.this, "Something went wrong! Please try again.", 2000).show();
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {
                                        progressDialog.dismiss();
                                        Log.d("TAG", t.getMessage());
                                        Toast.makeText(getApplicationContext(), "Thất Bại", Toast.LENGTH_LONG).show();
                                    }
                                });
                                //image.setText(APIService.BASE_URL+"images/"+ imageData.getName());
                            }

                            @Override
                            public void onFailure(Call<ImageData> call, Throwable t) {
                                Log.d("TAG", t.getMessage());
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Thất Bại", Toast.LENGTH_LONG).show();

                            }
                        });
                    }

                }
                else {

                    Toasty.warning(SettingActivity.this,"Incorrect Details Entered",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private boolean validateName() {

        check = edtname.getText().toString();

        return !(check.length() < 4 || check.length() > 20);

    }

    private boolean validateNumber() {

        check = edtmobile.getText().toString();
        Log.e("inside number",check.length()+" ");
        if (check.length()>10) {
            return false;
        }else if(check.length()<10){
            return false;
        }
        return true;
    }

    private boolean validateEmail() {

        check = edtemail.getText().toString();

        if (check.length() < 4 || check.length() > 40) {
            return false;
        } else if (!check.matches("^[A-za-z0-9.@]+")) {
            return false;
        } else if (!check.contains("@") || !check.contains(".")) {
            return false;
        }

        return true;
    }

    TextWatcher nameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (check.length() < 4 || check.length() > 20) {
                edtname.setError("Tên phải từ 4 đến 20 ký tự");
            }
        }

    };

    //TextWatcher for Email -----------------------------------------------------

    TextWatcher emailWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (check.length() < 4 || check.length() > 40) {
                edtemail.setError("Địa chỉ email phải có từ 4 đến 40 ký tự");
            } else if (!check.matches("^[A-za-z0-9.@]+")) {
                edtemail.setError("Chỉ cho phép . và @");
            } else if (!check.contains("@") || !check.contains(".")) {
                edtemail.setError("Email không hợp lệ");
            }

        }

    };

    //TextWatcher for Mobile -----------------------------------------------------

    TextWatcher numberWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //none
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //none
        }

        @Override
        public void afterTextChanged(Editable s) {

            check = s.toString();

            if (check.length()>10) {
                edtmobile.setError("Số điện thoại không được quá 10 số");
            }else if(check.length()<10){
                edtmobile.setError("Số điện thoại phải đủ 10 số");
            }
        }

    };



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mainImageURI = result.getUri();
                primage.setImageURI(mainImageURI);

                IMAGE_STATUS = true;

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }
    }

    private void AnhXa() {
        edtname = findViewById(R.id.name);
        edtemail = findViewById(R.id.email);
        edtmobile = findViewById(R.id.number);
        changeprofilepic = findViewById(R.id.changeprofilepic);
        primage = findViewById(R.id.profilepic);
        updateProfileBtn = findViewById(R.id.btn_update_setting);

        edtname.addTextChangedListener(nameWatcher);
        edtemail.addTextChangedListener(emailWatcher);
        edtmobile.addTextChangedListener(numberWatcher);

        getValues();
    }

    private void getValues() {
        User user = SharedPrefManager.getInstance(this).getUser();
        edtname.setText(user.getFullName());
        edtemail.setText(user.getEmail());
        edtmobile.setText(user.getPhone());
        Glide.with(this).load(user.getAvatar()).into(primage);
    }

    private void bringImagePicker() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1,1)
                .start(SettingActivity.this);

    }

}