package com.example.connect.data.model.response

import com.squareup.moshi.Json

data class ProfileResponse(
    val id: Int,
    val id_user: Int,
    val name: String?,
    val nim: String?,
    val tgl_lahir: String?,
    val wa: String?,
    val domisili: String?,
    @Json(name = "photo") val photo: String?,
    val jenis_kelamin: String?,
    val pendidikan:PendidikanResponse
)

