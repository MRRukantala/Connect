package com.example.connect.data.auth

import com.google.gson.annotations.SerializedName

data class ResponseListWrapperSementara<T>(
    @SerializedName("status") val status: Boolean,
    @SerializedName("data") val data: List<T>? = null
)

data class ResponseObjectWrapperSementara<T>(
    @SerializedName("data") val data: T? = null,
    @SerializedName("message") val message: String,
)