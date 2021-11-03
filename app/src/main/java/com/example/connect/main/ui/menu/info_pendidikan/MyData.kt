package com.example.connect.main.ui.menu.info_pendidikan

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class MyData(
    val data: List<MySubData>
)

@Parcelize
data class MySubData(
    val id: Int,
    val id_user: Int,
    val name: String?,
    val nim: String? = "Belum Ditambahkan",
    val tgl_lahir: String? = "Belum Ditambahkan",
    val wa: String? = "Belum Ditambahkan",
    val domisili: String? = "Belum Ditambahkan",
    @Json(name = "photo") val photo: String?,
    val jenis_kelamin: String? = "Belum Ditambahkan",
    val pendidikan: List<MySubPendidikan>?
) : Parcelable

@Parcelize
data class MySubPendidikan(
    val id: Int,
    val instansi: String,
    val jenjang: String,
    val fakultas: String,
    val jurusan: String,
    val tahun_masuk: String,
    val tahun_keluar: String
) : Parcelable

data class message(
    val message: String
)