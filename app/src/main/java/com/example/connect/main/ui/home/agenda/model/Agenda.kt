package com.example.connect.main.ui.home.agenda.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.sql.Date
import java.sql.Time

@Parcelize
data class Agenda(
    val id: Int,
    val id_user: Int,
    val title: String,
    val lokasi: String,
    val tanggal: String,
    val waktu: String,
    val konten: String,
    @Json(name = "photo") val photo: String,
    val status: String,
    val created_at: String,
    val updated_at: String
) : Parcelable
