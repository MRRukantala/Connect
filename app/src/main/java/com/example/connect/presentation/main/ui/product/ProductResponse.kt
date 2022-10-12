package com.example.connect.presentation.main.ui.product.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

class ProductResponse(
    val data: List<ProductModel>
//    val message: String?
)

@Parcelize
data class ProductModel(
    val id: Int,
    val id_user: Int,
    val name : String,
    val level: String,
    @Json(name = "photo") val photo: String,
    @Json(name = "gambar") val gambar: String,
    val harga: Int,
    val nama_produk: String,
    val deskripsi: String,
    val wa: String? ,
    val created_at: String,
    val updated_at: String
) : Parcelable


