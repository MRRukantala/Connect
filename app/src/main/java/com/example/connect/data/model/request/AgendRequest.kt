package com.example.connect.data.model.request

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part

data class AgendaRequest(
    @Part("title") val title: RequestBody?,
    @Part("lokasi") val lokasi: RequestBody?,
    @Part("tanggal") val tanggal: RequestBody?,
    @Part("waktu") val waktu: RequestBody?,
    @Part("konten") val konten: RequestBody?,
    @Part("image") val image: MultipartBody.Part?,
    @Part("status") val status: RequestBody?
)
