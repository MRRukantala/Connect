package com.example.connect.login.data.model

data class DataUser(
    val id: Int,
    val name: String,
    val email: String,
    val email_verified_at: String,
    val status: String,
    val level: String
)

data class UserResponse(
    val token: String,
    val token_type: String,
    val user:   DataUser,
)