package com.example.connect.domain.entity

data class AgendaEntity(
    val id:Int,
    val gambar: String,
    val judul: String,
    val konten: String,
    val tanggal: String,
    val waktu: String,
    val lokasi: String
)