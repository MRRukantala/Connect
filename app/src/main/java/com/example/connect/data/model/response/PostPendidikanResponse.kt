package com.example.connect.data.model.response


import com.example.connect.domain.entity.BasicEntity
import com.example.connect.domain.entity.PendidikanEntity
import com.example.connect.domain.entity.PostPendidikanEntity
import com.google.gson.annotations.SerializedName

data class PostPendidikanResponse(
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("message")
    val message: String?
){
    fun toPostPendidikanEntity() = PostPendidikanEntity(
        status?:true,
        message?:""
    )
}