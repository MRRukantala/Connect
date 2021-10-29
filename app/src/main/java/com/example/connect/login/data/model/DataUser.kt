package com.example.connect.login.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataUser(
    val id: Int?,
    val name: String,
    val email: String,
    val email_verified_at: String?,
    val status: String?,
    val level: String,
    val created_at: String,
    val updated_at: String
) : Parcelable

@Parcelize
data class response(
    val token: String,
    val token_type: String,
    val user: DataUser,
) : Parcelable

data class UserResponse(
    val data: response,
    var message: String?
)