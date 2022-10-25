package com.example.connect.data.model.response


import com.example.connect.domain.entity.EditProfleEntity
import com.google.gson.annotations.SerializedName

data class EditProfileResponse(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("id_user")
    val idUser: Int? = 0,
    @SerializedName("jenis_kelamin")
    val jenisKelamin: String? = "",
    @SerializedName("nim")
    val nim: String? = "",
    @SerializedName("tgl_lahir")
    val tglLahir: String? = "",
    @SerializedName("domisili")
    val domisili: String? = "",
    @SerializedName("wa")
    val wa: String? = "",
    @SerializedName("photo")
    val photo: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("user")
    val user: UserProfile? = UserProfile(
        0, "", "", "", "", "",
        "", ""
    )
) {
    fun toEditProfileEntity() = EditProfleEntity(
        id ?: 0,
        idUser ?: 0,
        jenisKelamin ?: "",
        nim ?: "",
        tglLahir ?: "",
        domisili ?: "",
        wa ?: "",
        photo ?: "",
        createdAt ?: "",
        updatedAt ?: "",
        user?.name ?: "",
        user?.email ?: "",
        user?.emailVerifiedAt ?: "",
        user?.status ?: "",
        user?.level ?: ""
    )
}

data class UserProfile(
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