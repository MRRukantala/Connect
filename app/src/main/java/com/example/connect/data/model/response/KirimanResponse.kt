package com.example.connect.data.model.response

import com.squareup.moshi.Json

data class KirimanResponse(
    val id: Int,
    val id_user: Int,
    val name: String,
    @Json(name = "photo") val photo : String,
    @Json(name = "gambar") val gambar: String,
    val konten: String,
    val created_at: String,
    val updated_at: String
)
