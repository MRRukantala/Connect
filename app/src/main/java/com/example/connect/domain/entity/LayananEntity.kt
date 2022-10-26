package com.example.connect.domain.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
@Keep
@Parcelize
data class LayananEntity(
    val id: Int,
    val gambar: String,
    val judul: String,
    val konten: String
):Parcelable