package com.example.connect.data.model.request

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part

data class KirimanRequest(
    @Part("id_profil") val id: RequestBody,
    @Part("image") val starImage: MultipartBody.Part?,
    @Part("konten") val content: RequestBody
)
