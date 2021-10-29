package com.example.connect.main.ui.home.tablayout.agenda.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

class AgendaResponse (
    val data: List<Agenda>,
    val message: String? = null
)

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