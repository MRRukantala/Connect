package com.example.connect.data.repository

import com.example.connect.data.api.ProductApiClient
import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseListWrapperSementara
import com.example.connect.data.model.response.ProductResponse
import com.example.connect.domain.entity.DetailProductEntity
import com.example.connect.domain.entity.ProductEntity
import com.example.connect.domain.repo.ProductApiRepository
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoreRepositoryInteractor @Inject constructor(private val apiClient: ProductApiClient) :
    ProductApiRepository {
    override suspend fun getAllProductByAdmin(id: Int): Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>> {
        return flow {
            val response = apiClient.getAllProductByAdmin(id)
            delay(800)
            if (response.isSuccessful) {
                val body = response.body()?.data
                val data = mutableListOf<ProductEntity>()
                body?.forEach { data.add(it.toProductEntity()) }
                emit(Result.Success(data))
            } else {

            }
        }
    }

    override suspend fun getAllProductMarkOI(): Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>> {
        return flow {
            val response = apiClient.getAllProductMarkOI()
            delay(800)
            if (response.isSuccessful) {
                val body = response.body()?.data
                val data = mutableListOf<ProductEntity>()
                body?.forEach { data.add(it.toProductEntity()) }
                emit(Result.Success(data))
            } else {

            }
        }
    }

    override suspend fun getProductByIdUser(id: Int): Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>> {
        return flow {
            val response = apiClient.getProductByIdUser(id)
            delay(800)
            if (response.isSuccessful) {
                val body = response.body()?.data
                val data = mutableListOf<ProductEntity>()
                body?.forEach { data.add(it.toProductEntity()) }
                emit(Result.Success(data))
            } else {
            }
        }
    }

    override suspend fun getDetailProduct(id: Int): Flow<Result<List<DetailProductEntity>, ResponseListWrapperSementara<ProductResponse>>> {
        return flow {
            val response = apiClient.getDetailProduct(id)
            if (response.isSuccessful) {
                delay(800)
                val body = response.body()?.data
                val data = mutableListOf<DetailProductEntity>()
                body?.forEach {
                    data.add(it.toDetailProductEntity())
                }
                emit(Result.Success(data))
            } else {
                response.message()
            }
        }
    }
}