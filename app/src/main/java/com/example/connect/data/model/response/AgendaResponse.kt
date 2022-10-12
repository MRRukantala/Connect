package com.example.connect.data.model.response

import com.squareup.moshi.Json

data class AgendaResponse(
    val id: Int,
    val id_user: Int,
    val name: String,
    @Json(name = "photo") val photo: String,
    val title: String,
    val lokasi: String,
    val konten: String,
    val waktu: String,
    val tanggal: String,
    val status: String,
    val created_at: String?,
    val updated_at: String?
)
