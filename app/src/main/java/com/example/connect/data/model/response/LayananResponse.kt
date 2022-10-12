package com.example.connect.data.model.response

import com.squareup.moshi.Json

data class LayananResponse(
    val id: Int,
    val id_user: Int,
    @Json(name = "gambar") val gambar: String,
    val judul: String,
    val konten: String,
    val created_at: String,
    val updated_at: String
)
