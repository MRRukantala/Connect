package com.example.connect.domain.entity


import android.os.Parcelable
import androidx.annotation.Keep
import com.example.connect.data.model.response.User
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class RegisterEntity(
    @SerializedName("token")
    val token: String,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("user")
    val user: User
)