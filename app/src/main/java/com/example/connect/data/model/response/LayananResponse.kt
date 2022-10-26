package com.example.connect.data.model.response

import com.example.connect.domain.entity.LayananEntity
import com.google.gson.annotations.SerializedName


data class LayananResponse(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("id_user")
    val idUser: Int? = 0,
    @SerializedName("gambar")
    val gambar: String? = "",
    @SerializedName("judul")
    val judul: String? = "",
    @SerializedName("konten")
    val konten: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = ""
){
    fun toLayananEntity() = LayananEntity(id?:0, gambar.orEmpty(),
            judul.orEmpty(), konten.orEmpty()
        )
}