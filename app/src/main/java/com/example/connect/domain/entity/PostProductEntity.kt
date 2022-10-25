package com.example.connect.domain.entity


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class PostProductEntity(
    val id: Int,
    val id_user: Int,
    val name: String,
    val photo: String,
    val gambar: String,
    val harga: Int,
    val nama_produk: String,
    val deskripsi: String,
    val updated_at: String,
    val created_at: String
):Parcelable