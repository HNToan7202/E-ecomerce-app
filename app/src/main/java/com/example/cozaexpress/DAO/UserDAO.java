package com.example.cozaexpress.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cozaexpress.Model.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id IN(:userIds)")
    List<User> loadAllByIds(String[] userIds);

    @Insert
    void insertAll(User... user);

    @Update
    void update(User...user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user WHERE username = :username")
    List<User> checkUser(String username);

}
