package com.example.connect.data.model.response


import com.example.connect.domain.entity.PostKirimanEntity
import com.google.gson.annotations.SerializedName

data class PostKirimanResponse(
    @SerializedName("id_user")
    val idUser: Int? = 0,
    @SerializedName("gambar")
    val gambar: String? = "",
    @SerializedName("konten")
    val konten: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("id")
    val id: Int? = 0
) {
    fun toPostKirimanEntity() = PostKirimanEntity(
        idUser ?: 0, gambar ?: "", konten ?: "", updatedAt ?: "", createdAt ?: "", id ?: 0
    )
}