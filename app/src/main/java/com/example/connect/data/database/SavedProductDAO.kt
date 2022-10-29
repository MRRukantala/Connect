package com.example.connect.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SavedProductDAO {

    @Insert
    fun insert(savedProductData: SaveProductDataEntity)

    @Update
    fun update(savedProductData: SaveProductDataEntity)

    @Query("SELECT * FROM saved_product_data where idSaveProductData = :key")
    fun get(key: Long): SaveProductDataEntity

    @Query("SELECT * FROM saved_product_data where id_user = :key ")
    fun getByIdUser(key: Int): LiveData<List<SaveProductDataEntity>>

    @Query("SELECT * FROM saved_product_data WHERE idSaveProductData = :key")
    fun getProductById(key: Long) : LiveData<SaveProductDataEntity>

    @Query("DELETE FROM saved_product_data WHERE idSaveProductData = :key")
    fun deleteItem(key: Long)
}