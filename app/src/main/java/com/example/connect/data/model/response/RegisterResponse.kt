package com.example.connect.data.model.response

data class RegisterResponse(
    val id: Int?,
    val email: String,
    val level: String,
    val status: Int?,
    val created_at: String,
    val updated_at: String,
    val token: String,
    val token_type: String,
)
