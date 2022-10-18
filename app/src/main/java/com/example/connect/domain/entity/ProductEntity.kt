package com.example.connect.domain.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class ProductEntity(
    val id: Int,
    val nama: String,
    val harga: Int,
    val gambar: String,
    val tanggal: String
) : Parcelable
