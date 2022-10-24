package com.example.connect.domain.entity


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class PostKirimanEntity(
    val id_user: Int,
    val gambar: String,
    val konten: String,
    val updated_at: String,
    val created_at: String,
    val id: Int
):Parcelable