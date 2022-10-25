package com.example.connect.domain.entity


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class EditProfleEntity(
    val id: Int,
    val id_user: Int,
    val jenis_kelamin: String,
    val nim: String,
    val tgl_lahir: String,
    val domisili: String,
    val wa: String,
    val photo: String,
    val created_at: String,
    val updated_at: String,
    val name: String,
    val email: String,
    val email_verified_at: String,
    val status: String,
    val level: String,

):Parcelable
