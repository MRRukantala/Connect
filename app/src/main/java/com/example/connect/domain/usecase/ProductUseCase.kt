package com.example.connect.domain.usecase

import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseListWrapperSementara
import com.example.connect.data.model.response.ProductResponse
import com.example.connect.domain.entity.DetailProductEntity
import com.example.connect.domain.entity.ProductEntity
import com.example.connect.domain.repo.ProductApiRepository
import com.example.connect.domain.repo.ProfileApiRepository
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductUseCase @Inject constructor(private val repository: ProductApiRepository){

    suspend fun getProductByAdmin(id:Int):
            Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>> {
        return repository.getAllProductByAdmin(id)
    }

    suspend fun getAllProduct():
            Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>> {
        return repository.getAllProductMarkOI()
    }

    suspend fun getAllProductByUser(id:Int):
            Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>> {
        return repository.getProductByIdUser(id)
    }

    suspend fun detailProduct(id:Int): Flow<Result<List<DetailProductEntity>, ResponseListWrapperSementara<ProductResponse>>>{
        return repository.getDetailProduct(id)
    }
}