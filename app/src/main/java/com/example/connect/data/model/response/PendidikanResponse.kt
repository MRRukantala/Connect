package com.example.connect.data.model.response

import com.example.connect.domain.entity.PendidikanEntity
import com.google.gson.annotations.SerializedName

data class PendidikanResponse(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("instansi")
    val instansi: String? = "",
    @SerializedName("jenjang")
    val jenjang: String? = "",
    @SerializedName("fakultas")
    val fakultas: String? = "",
    @SerializedName("jurusan")
    val jurusan: String? = "",
    @SerializedName("tahun_masuk")
    val tahunMasuk: String? = "",
    @SerializedName("tahun_keluar")
    val tahunKeluar: String? = ""
) {
    fun toPendidikanEntity() = PendidikanEntity(

        id ?: 0, jenjang.orEmpty(), jurusan.orEmpty(), instansi.orEmpty(),
        tahunMasuk.orEmpty(), tahunKeluar.orEmpty()

    )
}