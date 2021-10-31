package com.example.connect.main.ui.product.model

import android.os.Parcelable
import com.example.connect.login.data.model.DataUser
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

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


