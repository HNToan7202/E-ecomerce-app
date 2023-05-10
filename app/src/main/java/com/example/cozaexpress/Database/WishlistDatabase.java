package com.example.cozaexpress.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cozaexpress.DAO.ProductDAO;
import com.example.cozaexpress.DAO.WishListModel;
import com.example.cozaexpress.DAO.WishlistDAO;
import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.Wishlist;

@Database(entities = {WishListModel.class}, version = 1)
public abstract class WishlistDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "wishlist.db";
    private static WishlistDatabase instance;

    public static synchronized WishlistDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), WishlistDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract WishlistDAO wishlistDAO();
}
