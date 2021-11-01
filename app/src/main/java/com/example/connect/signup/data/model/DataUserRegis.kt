package com.example.connect.signup.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class DataUserRegis(
    val id: Int?,
//    val name: String,
    val email: String,
    val level: String,
    val status: Int?,
    val created_at: String,
    val updated_at: String,
) : Parcelable

@Parcelize
data class response(
    val token: String,
    val token_type: String,
    val user: DataUserRegis,
) : Parcelable


data class UserResponse(
    val data: response,
    var message: String?
)

