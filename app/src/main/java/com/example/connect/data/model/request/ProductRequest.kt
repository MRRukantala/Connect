package com.example.connect.data.model.request

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part

data class ProductRequest(
    @Part("gambar") val image: MultipartBody.Part?,
    @Part("harga") val harga: Int?,
    @Part("nama_produk") val namaProduk: RequestBody?,
    @Part("deskripsi") val description: RequestBody?
)
