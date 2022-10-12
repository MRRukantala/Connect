package com.example.connect.data.model.request

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part
import retrofit2.http.Path

data class ProfileRequest(
    @Part("jenis_kelamin") val jenis_kelamin: RequestBody?,
    @Part("nim") val nim: RequestBody?,
    @Part("tgl_lahir") val tanggal_lahir: RequestBody?,
    @Part("domisili") val domisili: RequestBody?,
    @Part("wa") val wa: RequestBody?,
    @Part("image") val image: MultipartBody.Part?,
    @Path("id_user") val id_user: Int,
)
