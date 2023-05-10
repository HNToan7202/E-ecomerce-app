package com.example.cozaexpress.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cozaexpress.Model.Product;

import java.util.List;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM product")
    List<Product> getAll();

    @Insert
    void insertProduct(Product product);

    @Query("SELECT * FROM product WHERE id = :id")
    Product checkProduct(String id);

    @Delete
    void delete(Product product);

    @Query("DELETE FROM Product")
    void deleteAll();;

    @Update
    void update(Product product);


}
