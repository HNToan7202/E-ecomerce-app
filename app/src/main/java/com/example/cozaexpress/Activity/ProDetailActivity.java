package com.example.cozaexpress.Activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.example.cozaexpress.Adapter.LastProductAdapter;
import com.example.cozaexpress.Adapter.PhotoAdapter;
import com.example.cozaexpress.Adapter.ProDetailAdapter;
import com.example.cozaexpress.Adapter.ReviewAdapter;
import com.example.cozaexpress.DAO.WishListModel;
import com.example.cozaexpress.DAO.WishlistDAO;
import com.example.cozaexpress.DataLocal.DataLocalManager;
import com.example.cozaexpress.DataLocal.SharedPrefManager;
import com.example.cozaexpress.Database.ProductDatabase;
import com.example.cozaexpress.Database.WishlistDatabase;
import com.example.cozaexpress.Model.GenericProductModel;
import com.example.cozaexpress.Model.Photo;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.Review;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.Model.UserWishlist;
import com.example.cozaexpress.Model.Wishlist;
import com.example.cozaexpress.Model.WishlistRequest;
import com.example.cozaexpress.Model.WishlistResponse;
import com.example.cozaexpress.R;
import com.example.cozaexpress.UserSession.UserSession;
import com.example.cozaexpress.api.APIService;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.nex3z.notificationbadge.NotificationBadge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProDetailActivity extends AppCompatActivity {

    ImageView imgProduct;
    TextView tvPrice, tvDesciption, tvGiaChuaGiam, tvNameSp, tvHangSP, wishList, tvAvgRating, tvDiscount;
    List<Product> productList = new ArrayList<>();

    ImageView btnBackToHome;

    ImageView btnPopup, btnCart, btnBack;

    AppCompatButton btnThemVaoGio, btnMuaNgay;

    ProDetailAdapter proDetailAdapter;

    RecyclerView rvFeedback;

    TextView totalrating;

    TextView tvBarcode;

    String id;

    int idDialog;

    Product product;

    EditText edtDanhGia;

    public static final String DETAIL_REVIEW = "detail_review";


    EditText quantity;

    private ImageView like,likeFull;
    NotificationBadge badge;

    ViewPager2 mViewPager2;

    CircleIndicator3 mCircleIndicator3;


    List<Review> mListReview;

    LinearLayout action_rating;

    @BindView(R.id.add_to_wishlist)
    LottieAnimationView addToWishlist;

    FirebaseFirestore firestore;

    @BindView(R.id.wishlist_red)
    ImageView wishlist_red;

    GenericProductModel model;

    TextView tvSeeAll;

    private String usermobile, useremail;
    private String username;

    private UserSession session;

    RecyclerView rc_prodctsame;

    FrameLayout onStartCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_detail);


        //Nhận từ tìm kiếm
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getString("idProduct");
        badge = findViewById(R.id.menu_sl);
        //Khởi tạo username
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        if(user != null){
            username = user.getUsername();
            usermobile = user.getFullName();
            useremail = user.getEmail();
        }

        //Tìm sản phẩm theo id
        if(id != null){
            getProductById(id);
        }

        AnhXa();

        registerForContextMenu(btnPopup);
        btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopupMenu();
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        wishlist_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final KProgressHUD progressDialog=  KProgressHUD.create(ProDetailActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
                User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
                WishlistRequest wishlistRequest = new WishlistRequest();
                wishlistRequest.setProduct(product);
                wishlistRequest.setUser(user);

                APIService.apiService.removeProductFromWishlist(wishlistRequest).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                            progressDialog.dismiss();
                            String message = response.body();
                        addToWishlist.setVisibility(View.VISIBLE);
                        addToWishlist.cancelAnimation();
                        addToWishlist.clearAnimation();
                        addToWishlist.setProgress(0);
                        addToWishlist = findViewById(R.id.add_to_wishlist);
                        wishlist_red.setVisibility(View.GONE);
                            Toasty.success(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        progressDialog.dismiss();
                        addToWishlist.setVisibility(View.VISIBLE);
                        addToWishlist.cancelAnimation();
                        addToWishlist.clearAnimation();
                        addToWishlist.setProgress(0);
                        addToWishlist = findViewById(R.id.add_to_wishlist);
                        wishlist_red.setVisibility(View.GONE);

                    }
                });

            }
        });


        Product product = (Product) bundle.getSerializable("product");
        if(product != null){
            GetProductByBarcode(product);
            checkExistWishlist();
        }

    }
    public void addToWishList(View view) {
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        if(user!= null){


        final KProgressHUD progressDialog=  KProgressHUD.create(ProDetailActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        Wishlist wishlist1 = new Wishlist();

        wishlist1.setUser(user);
        List<Product> products1 = new ArrayList<>();
        products1.add(product);
        wishlist1.setProducts(products1);
        String message = "Toan";
        WishlistResponse wishlistResponse = new WishlistResponse(message, user, product);

        APIService.apiService.addWishlist(wishlist1).enqueue(new Callback<WishlistResponse>() {
            @Override
            public void onResponse(Call<WishlistResponse> call, Response<WishlistResponse> response) {

                progressDialog.dismiss();
                if(response.isSuccessful()){
//                    WishlistResponse wishlistResponse1 = response.body();
//                    Product productWishlist = response.body().getProduct();
//                    WishListModel wishListModel = new WishListModel();

//                    List<Product> productList1 = new ArrayList<>();
//                    productList1.add(productWishlist);
//                    wishListModel.setId(UUID.randomUUID().toString().split("-")[0]);
//                    wishListModel.convertProductToString(products1);
                    //WishlistDatabase.getInstance(getApplicationContext()).wishlistDAO().insertProduct(wishListModel);

                    Toasty.success(ProDetailActivity.this,"Added to your Wishlist",2000).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            addToWishlist.playAnimation();
                            addToWishlist.setVisibility(View.GONE);
                            wishlist_red.setVisibility(View.VISIBLE);
                        }
                    }, 2000);
                }
                else {
                    Toasty.error(ProDetailActivity.this,"Error",2000).show();
                }

            }

            @Override
            public void onFailure(Call<WishlistResponse> call, Throwable t) {

            }
        });
        }
        else {
            Toast.makeText(getApplicationContext(),"Bạn cần đăng nhập", Toast.LENGTH_LONG).show();
        }

//        APIService.apiService.insertWishlist(wishlist1).enqueue(new Callback<Wishlist>() {
//            @Override
//            public void onResponse(Call<Wishlist> call, Response<Wishlist> response) {
//                progressDialog.dismiss();
//                Wishlist wishlist = response.body();
//
//
//                WishListModel wishListModel = new WishListModel();
//                wishListModel.setId(wishlist.getId());
//                //wishListModel.setUserId(wishlist.getUser().getId());
//                wishListModel.convertProductToString(wishlist.getProducts());
//
//                WishlistDatabase.getInstance(getApplicationContext()).wishlistDAO().insertProduct(wishListModel);
//
//                Toasty.success(ProDetailActivity.this,"Added to your Wishlist",2000).show();
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        addToWishlist.clearAnimation();
//                        addToWishlist.setVisibility(View.GONE);
//                        wishlist_red.setVisibility(View.VISIBLE);
//
//                    }
//                },500);
//                //WishlistDatabase.getInstance(getApplicationContext()).wishlistDAO().insertProduct(product);
//                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
//
//
//            }
//            @Override
//            public void onFailure(Call<Wishlist> call, Throwable t) {
//                Toasty.error(ProDetailActivity.this,"Failed to add.",2000).show();
//            }
//        });



    }
    public void similarProduct(View view) {
        finish();
    }

    private void checkExistWishlist() {

        APIService.apiService.getWishlistByUser(SharedPrefManager.getInstance(getApplicationContext()).getUser()).enqueue(new Callback<UserWishlist>() {
            @Override
            public void onResponse(Call<UserWishlist> call, Response<UserWishlist> response) {
                if(response.isSuccessful()){
                    UserWishlist userWishlist = response.body();
                    List<Product> productList = userWishlist.getList();
                    for(Product product1 : productList){
                        if(product1.getId().equals(product.getId())){
                            addToWishlist.setVisibility(View.GONE);
                            wishlist_red.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<UserWishlist> call, Throwable t) {

            }
        });
    }


    private Product getProductObject() {
        if(product != null)
            return product;
        return null;
    }


    private void GetProductByBarcode(Product product) {
        List<Photo> photoList = new ArrayList<>();
        photoList = product.getListPhoto();
        PhotoAdapter photoAdapter = new PhotoAdapter(ProDetailActivity.this, photoList);
        mViewPager2.setAdapter(photoAdapter);
        mCircleIndicator3.setViewPager(mViewPager2);
        tvGiaChuaGiam.setText(product.getPrice().toString());
        tvPrice.setText(product.getPromotionaprice().toString());
        tvDesciption.setText(product.getDesciption());
        tvNameSp.setText(product.getName());
        checkExistWishlist();
        Log.d("TAG", product.getName());
        getProductSame(product.getId());
    }


    private void GetReviews() {

        //Lấy danh sách đánh giá
        APIService.apiService.getReviewByProduct(product).enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                mListReview = response.body();
                if(mListReview.size() > 0){
                    action_rating.setVisibility(View.VISIBLE);
                    totalrating.setText("("+mListReview.size()+")");
                }

                ReviewAdapter reviewAdapter = new ReviewAdapter(ProDetailActivity.this, mListReview);
                rvFeedback.setHasFixedSize(false);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                rvFeedback.setLayoutManager(layoutManager);
                rvFeedback.setAdapter(reviewAdapter);
                reviewAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                Log.e("TAG", "onFailure: "+ t.getMessage() );
                Toast.makeText(getApplicationContext(), "Lỗi"+ t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void SubmitDanGia() {
        Review review = new Review();
        review.setContent(edtDanhGia.getText().toString());
        review.setProduct(product);
        Random random = new Random();
        int randomNumber = random.nextInt(5) + 1;
        review.setRating(randomNumber);
        APIService.apiService.insertReview(review).enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                if(response.isSuccessful()){
                    Review review1 = response.body();
                    Toast.makeText(getApplicationContext(), "Thành Công", Toast.LENGTH_LONG).show();
                    GetReviews();
                    edtDanhGia.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Thất Bại", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Review> call, Throwable t) {
                Log.e("ERR", t.getMessage());
            }
        });
    }

    private void getProductById(String id) {
        APIService.apiService.getProductById(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                product = response.body();
                tvGiaChuaGiam.setText(String.format( "%,.0f",product.getPrice())+"đ");
                tvPrice.setText(String.format( "%,.0f",product.getPromotionaprice())+"đ");
                tvDesciption.setText(product.getDesciption());
                tvNameSp.setText(product.getName());
                tvAvgRating.setText(product.getRating().toString());
                if(product.getPrice() == product.getPromotionaprice()){
                    tvGiaChuaGiam.setVisibility(View.INVISIBLE);
                    tvDiscount.setVisibility(View.INVISIBLE);
                }
                Double discount = (product.getPrice()-product.getPromotionaprice() )/ product.getPrice();
                int discountPercentage = (int) (discount * 100); // Chuyển đổi thành phần trăm (25)
                String discountText = String.format("%d%% off", discountPercentage); // Định dạng chuỗi "xx% off"
                tvDiscount.setText(discountText); // Gán chuỗi vào TextView
                checkExistWishlist();
                List<Photo> photoList = new ArrayList<>();
                photoList = product.getListPhoto();
                PhotoAdapter photoAdapter = new PhotoAdapter(ProDetailActivity.this, photoList);
                mViewPager2.setAdapter(photoAdapter);
                mCircleIndicator3.setViewPager(mViewPager2);
                GetReviews();
                getProductSame(id);
            }
            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });


    }

    public void shareProduct(View view) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Found amazing " + product.getName() + "on CozaExpress App";
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }


    private void AnhXa() {
        tvBarcode = findViewById(R.id.tvBarcode);
        tvSeeAll = findViewById(R.id.tvSeeAll);
        totalrating = findViewById(R.id.total_rating);
        action_rating = findViewById(R.id.action_rating);
        rc_prodctsame = findViewById(R.id.rc_productsame);
        tvAvgRating = findViewById(R.id.tvAvgRating);
        rvFeedback = (RecyclerView) findViewById(R.id.rvFeedback);
        firestore = FirebaseFirestore.getInstance();
        onStartCart = findViewById(R.id.onStartCart);
        btnBack = findViewById(R.id.btn_back_to_prodetail);
        btnCart = findViewById(R.id.btn_cart);
        btnPopup = findViewById(R.id.btnMoreOption);
        tvPrice = findViewById(R.id.tvGiaDaGiam);
        tvGiaChuaGiam = findViewById(R.id.tv_GiaChuaGiam);
        tvGiaChuaGiam.setPaintFlags(tvGiaChuaGiam.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tvDesciption = findViewById(R.id.tvmota);
        tvNameSp = findViewById(R.id.tvnamedetail);
        tvHangSP = findViewById(R.id.tvHang_SP_detail);

        addToWishlist = findViewById(R.id.add_to_wishlist);
        wishlist_red = findViewById(R.id.wishlist_red);

        btnBackToHome = findViewById(R.id.btn_back_to_prodetail);
        btnThemVaoGio = findViewById(R.id.btn_add_to_cart);

        mViewPager2 = findViewById(R.id.view_page_2);
        mCircleIndicator3 = findViewById(R.id.indicator_3);

        tvDiscount = findViewById(R.id.tvDiscount);

        tvSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProDetailActivity.this, ViewAllReviewsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(DETAIL_REVIEW, (Serializable) mListReview);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



        onStartCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProDetailActivity.this, CartActivity.class));
            }
        });


        //wishList
        wishList = findViewById(R.id.text_action3);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnThemVaoGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLog1();
            }
        });
    }

    private void getProductSame(String id) {
        APIService.apiService.getListProductSame(id).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productsame = response.body();
                LastProductAdapter productadap = new LastProductAdapter(ProDetailActivity.this,productsame);
                rc_prodctsame.setHasFixedSize(false);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ProDetailActivity.this,2);
                rc_prodctsame.setLayoutManager(layoutManager);
                rc_prodctsame.setAdapter(productadap);
                productadap.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSetting:
                break;
            case R.id.menuShare:
                break;
            case R.id.menuLogout:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSetting:
//lệnh
                Toast.makeText(ProDetailActivity.this,"Bạn đang chọn Setting",Toast.LENGTH_LONG).show();
                break;
            case R.id.menuShare:
                break;
            case R.id.menuLogout:
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void ShowPopupMenu(){
        PopupMenu popupMenu = new PopupMenu(this,btnPopup);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup,popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuSetting://lệnh
                        Toast.makeText(ProDetailActivity.this,"Bạn đang chọn Setting",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menuShare:
                        break;
                    case R.id.menuLogout:
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void XacNhanXoa( final int vitri){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Thông báo");
        alert.setMessage("Bạn có muốn đăng xuất không");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    //lệnh nút có
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

    private void DiaLog1(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        dialog.setCanceledOnTouchOutside(false);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        //ánh xạ
//        EditText editText1 = (EditText)
//                dialog.findViewById(R.id.editTextTextPersonName);
//        //viết code sự kiện
        //bắt sự kiện Dialog
        Button btnXacNhan = dialog.findViewById(R.id.btn_add_to_cart2);

        ImageView btnClose = dialog.findViewById(R.id.btnClose);

        Button decrement = dialog.findViewById(R.id.decrementQuantity);
        Button increment = dialog.findViewById(R.id.incrementQuantity);
        quantity = dialog.findViewById(R.id.quantityProductPage);
        quantity.setText("1");
        TextView tvPrice = dialog.findViewById(R.id.tv_price_dialog);

        TextView tvPriceReal = dialog.findViewById(R.id.tv_price_real);

        ImageView imgae = dialog.findViewById(R.id.img_hinh_sp);

        tvPrice.setText(String.format( "%,.0f",product.getPromotionaprice())+"đ");

        tvPriceReal.setText(String.format( "%,.0f",product.getPrice())+"đ");

        Glide.with(getApplicationContext())
                .load(product.getListPhoto().get(0).getResources())
                .into(imgae);
        decrement.setEnabled(false);

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantityNum = Integer.parseInt(quantity.getText().toString());
                quantityNum ++;
                quantity.setText(String.valueOf(quantityNum));
                if(quantityNum > 1){
                    decrement.setEnabled(true);
                }
                if(quantityNum == product.getQuantity()){
                    increment.setEnabled(false);
                }
            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantityNum = Integer.parseInt(quantity.getText().toString());
                quantityNum --;
                if(quantityNum == 1){
                    decrement.setEnabled(false);
                }
                if(quantityNum < product.getQuantity()){
                    increment.setEnabled(true);
                }
                quantity.setText(String.valueOf(quantityNum));
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //productList = DataLocalManager.getListProduct();
                int soluong = Integer.parseInt(quantity.getText().toString());
                product.setQuantity(soluong);
                //productList.add(product);

                //Chỗ này xử lý số lượng sản phẩm
                //Nếu sản phẩm đã có trong giỏ hàng thì cập nhật số lượng
                Product product1 = ProductDatabase.getInstance(getApplicationContext()).productDAO().checkProduct(product.getId());
                if(product1 != null){
                    product.setQuantity(product.getQuantity() + soluong);
                    ProductDatabase.getInstance(getApplicationContext()).productDAO().update(product);
                    Toast.makeText(ProDetailActivity.this, "Đã Cập Nhật Giỏ Hàng", Toast.LENGTH_LONG).show();
                }
                else {
                    //Chưa có thì thêm mới
                    product.setQuantity(soluong);
                    ProductDatabase.getInstance(getApplicationContext()).productDAO().insertProduct(product);
                    Toast.makeText(ProDetailActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_LONG).show();

                }
                badge.setText(ProductDatabase.getInstance(getApplicationContext()).productDAO().getAll().size()+"");
                dialog.dismiss();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show(); //hủy gọi dialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_popup,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_popup,menu);
        menu.setHeaderTitle("Context Menu");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}