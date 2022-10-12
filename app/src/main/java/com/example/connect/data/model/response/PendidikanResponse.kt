package com.example.connect.data.model.response

data class PendidikanResponse(
    val id:Int,
    val instansi: String,
    val jenjang: String,
    val fakultas: String,
    val jurusan: String,
    val tahun_masuk: String,
    val tahun_keluar: String
)
