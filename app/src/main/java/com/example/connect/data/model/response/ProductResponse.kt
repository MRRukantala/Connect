package com.example.connect.data.model.response

import com.squareup.moshi.Json

data class ProductResponse(
    val id: Int,
    val id_user: Int,
    val name : String,
    val level: String,
    @Json(name = "photo") val photo: String,
    @Json(name = "gambar") val gambar: String,
    val harga: Int,
    val nama_produk: String,
    val deskripsi: String,
    val wa: String?,
    val created_at: String,
    val updated_at: String
)
