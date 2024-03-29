package com.example.cozaexpress.api;

import com.example.cozaexpress.Model.Category;
import com.example.cozaexpress.Model.ChangePasswordRequest;
import com.example.cozaexpress.Model.Delivery;
import com.example.cozaexpress.Model.ForgotPasswordRequest;
import com.example.cozaexpress.Model.ForgotPasswordRes;
import com.example.cozaexpress.Model.ImageData;
import com.example.cozaexpress.Model.Order;
import com.example.cozaexpress.Model.OrderItem;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.ResetPasswordRequest;
import com.example.cozaexpress.Model.ResponseOrder;
import com.example.cozaexpress.Model.Review;
import com.example.cozaexpress.Model.StatusOrder;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.Model.UserWishlist;
import com.example.cozaexpress.Model.Wishlist;
import com.example.cozaexpress.Model.WishlistRequest;
import com.example.cozaexpress.Model.WishlistResponse;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {
    //public static final String BASE_URL="http://app.iotstar.vn/shoppingapp/";
    public static final String BASE_URL="https://moza.azurewebsites.net/";

//    public static final String BASE_URL="http://192.168.1.88:8080/";

    Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();
    APIService apiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build().create(APIService.class);

    @POST("user/get")
    @FormUrlEncoded
    Call<User> loginWithLocal(@Field("username") String username, @Field("password") String password);

    @POST("user")
    @FormUrlEncoded
    Call<User> signUp( @Field("username") String username, @Field("password") String password);

    @GET("categories/list")
    Call<List<Category>> getCategories();

    @GET("products/list")
    Call<List<Product>> getProducts();

    @POST("products/get")
    @FormUrlEncoded
    Call<List<Product>> getProductByBarcode(@Field("barcode") String barcode);

    @POST("products/getId")
    @FormUrlEncoded
    Call<Product> getProductById(@Field("id") String id);

    @POST("images")
    @Multipart
    Call<ImageData> uploadImages(@Part MultipartBody.Part image);

    @POST("reviews/add")
    Call<Review> insertReview(@Body Review review);

    @POST("reviews/product")
    Call<List<Review>> getReviewByProduct(@Body Product product);

    @POST("reviews/users")
    Call<List<Review>> getReviewByUser(@Body User user);

    @POST("products/category")
    Call<List<Product>> getProductByCate(@Body Category category);

    @POST("products/insert")
    Call<Product> insertProduct(@Body Product product);

    @POST("user/addWishlist")
    Call<WishlistResponse> addWishlist(@Body Wishlist wishlist);

    @POST("products/categoryName")
    @FormUrlEncoded
    Call<List<Product>> ListProductByCate(@Field("name") Object name);

    @POST("order/add")
    Call<Order> createOrder(@Body Order order);

    @POST("orderItem/addOrderItem")
    Call<List<OrderItem>> addOrderItem(@Body List<OrderItem> orderItems) ;

   @POST("orderItem/add")
    Call<OrderItem> addOrderItems(@Body OrderItem orderItem);

   @POST("order/userOders")
    Call<ResponseOrder> getOrderByUser(@Body User user);

   @POST("orderItem/getByOrder")
    Call<List<OrderItem>> getOrderItemByOrder(@Body Order order);

   @POST("user/deleteWishlist")
    Call<String> removeProductFromWishlist(@Body WishlistRequest wishlistRequest);

        @POST("user/userwishlist")
    Call<UserWishlist> getWishlistByUser(@Body User user);

   @POST("user/forgotPassword")
   @FormUrlEncoded
    Call<String> forgotPassword(@Field("email") String email);

   @POST("user/checkMail")
   @FormUrlEncoded
   Call<ForgotPasswordRes> checkMail(@Field("email") String email);

   @POST("user/resetPassword")
   Call<String> resetPassword(@Body ResetPasswordRequest request);

   @POST("user/checkOTP")
   @FormUrlEncoded
    Call<String> checkOTP(@Field("mail") String mail, @Field("otp") String otp);

    @POST("orderItem/get")
    @FormUrlEncoded
    Call<List<OrderItem>> getOrderItemList(@Field("id")String id);

    @POST("/order/changeStatus")
    @FormUrlEncoded
    Call<Order> changeStatuss(@Field("id")String id,@Field("status") StatusOrder status);

    @POST("order/getList")
    @FormUrlEncoded
    Call<List<Order>> getOrderList(@Field("status") StatusOrder Status);

    @POST("user/updateUser")
    Call<User> updateUser(@Body User user);

    @POST("user/add")
    Call<User> addUser(@Body User user);

    @GET("products/gettop5")
    Call<List<Product>> getTop5BanChay();

    @GET("products/gettop5discount")
    Call<List<Product>> getTop5discount();

    @POST("delivery/getByUser")
    Call<List<Delivery>> getDeliveryByUser(@Body User user);

    @GET("products/byCreatedAt")
    Call<List<Product>> getProductsByCreatedAt();

    @POST("reviews/rating")
    @FormUrlEncoded
    Call<Double> getRatingProduct(@Field("id") String id);

    @POST("orderItem/user/notreview")
    Call<List<OrderItem>> getOrderItemNotReview(@Body User user);

    @POST("orderItem/user/notreview")
    Call<List<OrderItem>> getOrderByUserNotReview(@Body User user);

    @POST("products/getById")
    @FormUrlEncoded
    Call<List<Product>> getListProductSame(@Field("id") String id);

    @POST("user/changePass")
    Call<ChangePasswordRequest> changePassword(@Body ChangePasswordRequest changePasswordRequest);

}

