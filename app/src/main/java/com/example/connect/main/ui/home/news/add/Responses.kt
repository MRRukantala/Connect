package com.example.connect.main.ui.home.news.add

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class AddedResponses(
    val id_user: Int,
    val gambar: String,
    val konten: String,
    val updated_at: String,
    val created_at: String,
    val id: Int
) : Parcelable
