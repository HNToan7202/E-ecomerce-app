package com.example.cozaexpress.api;

import com.example.cozaexpress.Model.Category;
import com.example.cozaexpress.Model.ImageData;
import com.example.cozaexpress.Model.Order;
import com.example.cozaexpress.Model.OrderItem;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.ResponseOrder;
import com.example.cozaexpress.Model.Review;
import com.example.cozaexpress.Model.User;
import com.example.cozaexpress.Model.Wishlist;
import com.example.cozaexpress.Model.WishlistRequest;
import com.example.cozaexpress.Model.WishlistResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
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
//    public static final String BASE_URL="https://ecomserver1.up.railway.app/";

    public static final String BASE_URL="http://192.168.1.2:8080/";


    Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();
    APIService apiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
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
}

