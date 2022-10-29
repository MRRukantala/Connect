package com.example.connect.data.api

import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.model.request.PendidikanRequest
import com.example.connect.data.model.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ProfileApiClient {
    //Profile
    @GET("profil/{id_user}")
    suspend fun getProfile(
        @Path("id_user") id: Int
    ): Response<ResponseListWrapper<ProfileResponse>>

    @Multipart
    @POST("profil/{id_user}")
    suspend fun updateMyProfile(
        @Part("jenis_kelamin") jenis_kelamin: RequestBody?,
        @Part("nim") nim: RequestBody?,
        @Part("tgl_lahir") tanggal_lahir: RequestBody?,
        @Part("domisili") domisili: RequestBody?,
        @Part("wa") wa: RequestBody?,
        @Part image: MultipartBody.Part?,
        @Path("id_user") id_user: Int,
        @QueryMap _method: Map<String, String>
    ): Response<ResponseObjectWrapper<EditProfileResponse>>

    @POST("pendidikan/{id}")
    suspend fun putPendidikan(
        @Body pendidikanRequest: PendidikanRequest,
        @Path("id") id: Int,
        @QueryMap _method: Map<String, String>
        ): Response<PostPendidikanResponse>

    @Multipart
    @POST("agenda")
    suspend fun postAgenda(
        @Part("title") title: RequestBody?,
        @Part("lokasi") lokasi: RequestBody?,
        @Part("tanggal") tanggal: RequestBody?,
        @Part("waktu") waktu: RequestBody?,
        @Part("konten") konten: RequestBody?,
        @Part image: MultipartBody.Part?,
        @Part("status") status: RequestBody?
    ): Response<ResponseListWrapper<AgendaResponse>>

    // API POST KIRIMAN
    @Multipart
    @POST("api-kiriman")
    suspend fun postKiriman(
        @Part starImage: MultipartBody.Part?,
        @Part("konten") content: RequestBody
    ): Response<ResponseObjectWrapper<PostKirimanResponse>>


    //API POST PENDIDIKAN
    @POST("pendidikan")
    suspend fun postPendidikan(
        @Body pendidikanRequest: PendidikanRequest
    ): Response<PostPendidikanResponse>


    // API DELETE PENDIDIKAN
    @DELETE("pendidikan/{id}")
    suspend fun deletePendidikan(
        @Path("id") id: Int,
    ): Response<ResponseObjectWrapper<DeletePendidikanResponse>>

    // API POST PRODUCT
    @Multipart
    @POST("produk")
    suspend fun postProduct(
        @Part image: MultipartBody.Part?,
        @Part("harga") harga: Int?,
        @Part("nama_produk") namaProduk: RequestBody?,
        @Part("deskripsi") description: RequestBody?
    ): Response<ResponseListWrapper<PostProductResponse>>

}