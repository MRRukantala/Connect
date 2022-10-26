package com.example.connect.data.model.request

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class PendidikanRequest(
    @SerializedName("instansi") val instansi: String,
    @SerializedName("jenjang")val jenjang: String,
    @SerializedName("fakultas")val fakultas: String,
    @SerializedName("jurusan")val jurusan: String,
    @SerializedName("tahun_masuk")val tahun_masuk: String,
    @SerializedName("tahun_keluar")val tahun_keluar: String
)
