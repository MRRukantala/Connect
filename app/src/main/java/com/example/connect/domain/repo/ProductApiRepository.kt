package com.example.connect.domain.repo

import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseListWrapperSementara
import com.example.connect.data.model.response.ProductResponse
import com.example.connect.domain.entity.DetailProductEntity
import com.example.connect.domain.entity.ProductEntity
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.Flow

interface ProductApiRepository {
    suspend fun getAllProductByAdmin(id:Int): Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>>

    suspend fun getAllProductMarkOI(): Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>>

    suspend fun getProductByIdUser(id:Int): Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>>

    suspend fun getDetailProduct(id:Int):Flow<Result<List<DetailProductEntity>, ResponseListWrapperSementara<ProductResponse>>>
}