package com.example.cozaexpress.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cozaexpress.Model.Product;
import com.example.cozaexpress.Model.Wishlist;

import java.util.List;

@Dao
public interface WishlistDAO {

    @Query("SELECT * FROM wishlistmodel")
    List<WishListModel> getAll();

    @Insert
    void insertProduct(WishListModel wishlist);

//    @Query("SELECT * FROM wishlist WHERE id = :id")
//    Product checkProduct(String id);

    @Delete
    void delete(WishListModel wishlist);

//    @Query("DELETE FROM Wishlist")
//    void deleteAll();;

    @Update
    void update(WishListModel wishlist);

}
