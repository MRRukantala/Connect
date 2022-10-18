package com.example.connect.data.model.response


import com.example.connect.domain.entity.LoginEntity
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String? = "",
    @SerializedName("token_type")
    val tokenType: String? = "",
    @SerializedName("user")
    val user: UserLogin? = UserLogin(
        0, "", "", "", "", "", "", ""
    )
) {
    fun toLoginEntity() = LoginEntity(
        token.orEmpty(), tokenType.orEmpty(), user ?: UserLogin(
            0, "", "", "", "", "", "", ""
        )
    )
}

data class UserLogin(
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