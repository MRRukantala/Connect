package com.example.connect.main.ui.layanan

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class Layanan(
    val data: List<DataLayanan>,
    val message: String?
)

@Parcelize
data class DataLayanan(
    val id: Int,
    val id_user: Int,
    @Json(name = "gambar") val gambar: String,
    val judul: String,
    val konten: String,
    val created_at: String,
    val updated_at: String
) : Parcelable