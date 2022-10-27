package com.example.connect.domain.entity


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize


@Keep
@Parcelize
data class DeletePendidikanEntity(
    val id: Int,
    val id_user: Int,
    val instansi: String,
    val jenjang: String,
    val fakultas: String,
    val jurusan: String,
    val tahun_masuk: String,
    val tahun_keluar: String,
    val created_at: String,
    val updated_at: String
):Parcelable