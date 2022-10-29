package com.example.connect.data.repository

import androidx.lifecycle.LiveData
import com.example.connect.data.database.SaveProductDataEntity
import com.example.connect.data.database.SavedProductDAO
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val savedProductDAO: SavedProductDAO
) {
    fun insert(savedProductDataEntity: SaveProductDataEntity) {

        savedProductDAO.insert(savedProductDataEntity)

    }

    fun update(savedProductDataEntity: SaveProductDataEntity){
        savedProductDAO.update(savedProductDataEntity)
    }

    fun getKey(key:Long):SaveProductDataEntity{
        return savedProductDAO.get(key)
    }

    fun getByIdUser(key:Int):LiveData<List<SaveProductDataEntity>>{
        return savedProductDAO.getByIdUser(key)

    }

    fun getProductById(key:Long):LiveData<SaveProductDataEntity>{
        return savedProductDAO.getProductById(key)
    }

    fun delete(key:Long){
        savedProductDAO.deleteItem(key)
    }


}