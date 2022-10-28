package com.example.connect.data.model.response

import com.example.connect.domain.entity.BasicEntity
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody

data class BasicResponse(
    @SerializedName("status") val status: Boolean?,
    @SerializedName("message") val message: String?
) {
    fun toBasicEntity() = BasicEntity(status ?: false, message.orEmpty())
}

fun isNotSuccesfull(responseReader: ResponseBody): BasicResponse {
    val type = object : TypeToken<BasicResponse>() {}.type
    val err: BasicResponse = Gson().fromJson(responseReader.charStream(), type)
    return err
}