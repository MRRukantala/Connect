package com.example.connect.domain.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
class ProfileEntity(
    val id: Int,
    val name: String,
    val nomerHp: String,
    val dommisili: String,
    val tglLahir: String,
    val jenisKelamin: String,
    val foto: String,
    val nim: String,
    val listPendidikan: List<PendidikanEntity>
) : Parcelable