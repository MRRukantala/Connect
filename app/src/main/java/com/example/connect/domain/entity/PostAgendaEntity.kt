package com.example.connect.domain.entity

data class PostAgendaEntity(
    val id: Int,
    val id_user: Int,
    val name: String,
    val photo: String,
    val title: String,
    val lokasi: String,
    val tanggal: String,
    val waktu: String,
    val konten: String,
    val status: String,
    val updated_at: String,
    val created_at: String
)