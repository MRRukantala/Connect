package com.example.connect.domain.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class PendidikanEntity(
    val id: Int,
    val jurusan: String,
    val instansi: String,
    val tahunMasuk: String,
    val tahunLulus: String

) : Parcelable
