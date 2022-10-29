package com.example.connect.domain.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class DetailProductEntity (
    val id: Int,
    val idUser: Int,
    val deskripsi:String,
    val nama: String,
    val harga: Int,
    val gambar: String,
    val tanggal: String
):Parcelable