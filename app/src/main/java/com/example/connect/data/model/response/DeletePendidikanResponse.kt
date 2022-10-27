package com.example.connect.data.model.response


import com.example.connect.domain.entity.DeletePendidikanEntity
import com.example.connect.domain.entity.PendidikanEntity
import com.google.gson.annotations.SerializedName

data class DeletePendidikanResponse(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("id_user")
    val idUser: Int? = 0,
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
    val tahunKeluar: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = ""
) {
    fun toDeletePendidikanEntity() = DeletePendidikanEntity(
        id ?: 0,
        idUser ?: 0,
        instansi ?: "",
        jenjang ?: "",
        fakultas ?: "",
        jurusan ?: "",
        tahunMasuk ?: "",
        tahunKeluar ?: "",
        createdAt ?: "",
        updatedAt ?: ""
    )
}