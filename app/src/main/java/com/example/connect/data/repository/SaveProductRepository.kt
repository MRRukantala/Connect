package com.example.connect.data.repository

import com.example.connect.data.database.SaveProductDataEntity
import com.example.connect.data.database.SavedProductDAO
import javax.inject.Inject

class SaveProductRepository @Inject constructor(
    private val savedProductDAO: SavedProductDAO
) {
    fun insert(saveProductDataEntity: SaveProductDataEntity){
        savedProductDAO.insert(saveProductDataEntity)
    }

    fun update(saveProductDataEntity: SaveProductDataEntity){
        savedProductDAO.update(saveProductDataEntity)
    }


}