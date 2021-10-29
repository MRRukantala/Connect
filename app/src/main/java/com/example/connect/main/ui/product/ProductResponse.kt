package com.example.connect.main.ui.product.model

import android.os.Parcelable
import com.example.connect.login.data.model.DataUser
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

class ProductResponse(
    val data: dataResponse,
    val message: String? = null
)

class dataResponse(
    val data: List<ProductModel>
)

@Parcelize
data class ProductModel(
    val id: Int,
    val id_user: Int,
    @Json(name = "gambar") val gambar: String,
    val harga: Int,
    val nama_produk: String,
    val deskripsi: String,
    val created_at: String,
    val updated_at: String,
    val user: DataUser?
) : Parcelable


