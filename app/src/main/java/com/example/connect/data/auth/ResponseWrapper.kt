package com.example.connect.data.auth

import com.example.connect.domain.entity.BasicEntity
import com.google.gson.annotations.SerializedName

data class ResponseListWrapper<T>(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<T>? = null
)

data class ResponseListWrapper2<T>(
    @SerializedName("status") val status: Boolean,
    @SerializedName("data") val data: List<T>? = null
)

data class ResponseObjectWrapper<T>(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: T? = null
)



