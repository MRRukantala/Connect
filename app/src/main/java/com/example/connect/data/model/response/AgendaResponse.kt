package com.example.connect.data.model.response


import com.example.connect.domain.entity.AgendaEntity
import com.example.connect.domain.entity.PostAgendaEntity
import com.google.gson.annotations.SerializedName

data class AgendaResponse(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("id_user")
    val idUser: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("photo")
    val photo: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("lokasi")
    val lokasi: String? = "",
    @SerializedName("konten")
    val konten: String? = "",
    @SerializedName("waktu")
    val waktu: String? = "",
    @SerializedName("tanggal")
    val tanggal: String? = "",
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = ""
) {
    fun toAgendaEntity() = AgendaEntity(
        id ?: 0,
        photo.orEmpty(), name.orEmpty(), konten.orEmpty(), tanggal.orEmpty(),
        waktu.orEmpty(), lokasi.orEmpty()
    )

    fun toPosAgendaEntity() = PostAgendaEntity(
        id ?: 0, idUser ?: 0, name.orEmpty(), photo.orEmpty(),
        title.orEmpty(), lokasi.orEmpty(), tanggal.orEmpty(), waktu.orEmpty(),
        konten.orEmpty(), status.orEmpty(), updatedAt.orEmpty(), createdAt.orEmpty()
    )


}