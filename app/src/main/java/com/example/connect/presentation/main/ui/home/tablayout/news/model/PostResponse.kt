package com.example.connect.presentation.main.ui.home.tablayout.news.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class PostResponse(
    val data: List<Post>,
    val message: String? = null
)

@Parcelize
data class Post(
    val id: Int,
    val id_user: Int,
    val name: String,
    @Json(name = "photo") val photo : String,
    @Json(name = "gambar") val gambar: String,
    val konten: String,
    val created_at: String,
    val updated_at: String
) : Parcelable