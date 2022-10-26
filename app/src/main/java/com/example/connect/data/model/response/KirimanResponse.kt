package com.example.connect.data.model.response


import com.example.connect.domain.entity.KirimanEntity
import com.google.gson.annotations.SerializedName

data class KirimanResponse(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("id_user")
    val idUser: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("photo")
    val photo: String? = "",
    @SerializedName("gambar")
    val gambar: String? = "",
    @SerializedName("konten")
    val konten: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = ""
) {
    fun toKirimanEntity() = KirimanEntity(
        id ?: 0,
        photo.orEmpty(),
        name.orEmpty(),
        konten.orEmpty(),
        gambar.orEmpty(),
        createdAt.orEmpty()
    )
}