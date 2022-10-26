package com.example.connect.data.model.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.example.connect.domain.entity.LayananEntity

@Keep
data class DetailArtikelResponse(
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
    val updatedAt: String? = "",
    @SerializedName("user")
    val user: UserArtikel? = UserArtikel(0, "", "", "", "", "", "", "")
) {
    fun toLayananEntity() = LayananEntity(id ?: 0, gambar ?: "", judul ?: "", konten ?: "")
}

data class UserArtikel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("level")
    val level: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)