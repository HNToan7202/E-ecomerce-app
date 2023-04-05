package com.example.cozaexpress.api;

import com.example.cozaexpress.Model.Category;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    //public static final String BASE_URL="http://app.iotstar.vn/shoppingapp/";
    public static final String BASE_URL="https://ecomserver.up.railway.app/";
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
}

