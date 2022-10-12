package com.example.connect.presentation.main.ui.home.tablayout.agenda.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

class AgendaResponse(
    val data: List<Agenda>,
    val message: String? = null
)

@Parcelize
data class Agenda(
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
) : Parcelable