package com.example.connect.main.ui.dashboard.modelproductumum

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductUmum(
    val id: Int,
    val id_user: Int,
    @Json(name = "gambar") val gambar: String,
    val harga: Int,
    val nama_produk: String,
    val deskripsi: String,
    val created_at: String,
    val updated_at: String
) : Parcelable
