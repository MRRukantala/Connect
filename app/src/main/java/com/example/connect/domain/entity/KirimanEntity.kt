package com.example.connect.domain.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class KirimanEntity(
    val id:Int,
    val fotoUser:String,
    val nama:String,
    val konten:String,
    val fotoKonten:String,
    val tglUpdate:String

):Parcelable
