package com.example.connect.data.api

import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseListWrapperSementara
import com.example.connect.data.model.response.PostProductResponse
import com.example.connect.data.model.response.ProductResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ProductApiClient {
    // API GET ALL PRODUCT
    @GET("produk-public")
    suspend fun getAllProductMarkOI(
    ): Response<ResponseListWrapper<ProductResponse>>

    // API GET ALL PRODUK BY ADMIN LEVEL
    @GET("produk-lv/{id}")
    suspend fun getAllProductByAdmin(
        @Path("id") id: Int
    ): Response<ResponseListWrapper<ProductResponse>>

    // API GET PRODUCT BY ID USER
    @GET("produk-id/{id}")
    suspend fun getProductByIdUser(
        @Path("id") id: Int
    ): Response<ResponseListWrapper<ProductResponse>>

    @GET("produk-public/{id}")
    suspend fun getDetailProduct(
        @Path("id") id: Int
    ): Response<ResponseListWrapperSementara<ProductResponse>>

}