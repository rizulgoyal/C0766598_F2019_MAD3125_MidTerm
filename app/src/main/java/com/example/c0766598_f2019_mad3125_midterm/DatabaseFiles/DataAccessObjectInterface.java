package com.example.c0766598_f2019_mad3125_midterm.DatabaseFiles;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c0766598_f2019_mad3125_midterm.ModelClasses.Bill;
import com.example.c0766598_f2019_mad3125_midterm.ModelClasses.Customer;

import java.util.List;

@Dao
public interface DataAccessObjectInterface
{
    @Insert
    void insert(Customer customer);

    @Delete
    void delete(Customer customer);

    @Update
    void update(Customer customer);

    @Query("Select * from customer")
    LiveData<List<Customer>> getUserDetails();

    @Query("Select * from customer where id = :id LIMIT 1")
    LiveData<Customer> getbillDetails(Integer id);


    @Query("Select count(id) from customer")
    Integer count();

    @Query("Select * from customer")
    List<Customer> getdefaultUserDetails();
}
