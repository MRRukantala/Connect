package com.example.connect.main.ui.home.news.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Post(
    val id: Int,
    val id_user: Int,
    @Json(name = "gambar") val gambar: String,
    val konten: String,
    val created_at: String,
    val updated_at: String
) : Parcelable
