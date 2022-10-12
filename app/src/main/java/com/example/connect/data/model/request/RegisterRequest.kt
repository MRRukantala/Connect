package com.example.connect.data.model.request

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class RegisterRequest(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("level") val level: Int = 2
)
