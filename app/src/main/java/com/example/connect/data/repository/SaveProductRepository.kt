package com.example.connect.data.repository

import com.example.connect.data.database.SaveProductData
import com.example.connect.data.database.SavedProductDAO
import javax.inject.Inject

class SaveProductRepository @Inject constructor(
    private val savedProductDAO: SavedProductDAO
) {
    fun insert(saveProductData: SaveProductData){
        savedProductDAO.insert(saveProductData)
    }

    fun update(saveProductData: SaveProductData){
        savedProductDAO.update(saveProductData)
    }
}