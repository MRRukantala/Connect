package com.example.connect.data.model.response


import com.example.connect.domain.entity.RegisterEntity
import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("token")
    val token: String? = "",
    @SerializedName("token_type")
    val tokenType: String? = "",
    @SerializedName("user")
    val user: User? = User(0, "", "", "", "", "")
) {
    fun toRegisterEntity() = RegisterEntity(
        token.orEmpty(), tokenType.orEmpty(), user ?: User(
            0, "", "", "", "", ""
        )
    )
}

data class User(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("level")
    val level: String? = "",
    @SerializedName("status")
    val status: Any? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = ""
)