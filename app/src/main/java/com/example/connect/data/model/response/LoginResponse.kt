package com.example.connect.data.model.response

data class LoginResponse(
    val id: Int?,
    val name: String,
    val email: String,
    val email_verified_at: String?,
    val status: String?,
    val level: String,
    val created_at: String,
    val updated_at: String,
    val token: String,
    val token_type: String,
)
