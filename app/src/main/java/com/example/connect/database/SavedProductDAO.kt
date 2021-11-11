package com.example.connect.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SavedProductDAO {

    @Insert
    fun insert(savedProductData: SaveProductData)

    @Update
    fun update(savedProductData: SaveProductData)

    @Query("SELECT * FROM saved_product_data where idSaveProductData = :key")
    fun get(key: Long): SaveProductData

//    @Query("DELETE  from saved_product_data where idSaveProductData = :key")
//    fun deleteById(key: Long): SaveProductData

    @Query("SELECT * FROM saved_product_data where id_user = :key ")
    fun getByIdUser(key: Int): LiveData<List<SaveProductData>>

    @Query("SELECT * FROM saved_product_data WHERE idSaveProductData = :key")
    fun getProductById(key: Long) : LiveData<SaveProductData>

    @Query("DELETE FROM saved_product_data WHERE idSaveProductData = :key")
    suspend fun deleteItem(key: Long)
}