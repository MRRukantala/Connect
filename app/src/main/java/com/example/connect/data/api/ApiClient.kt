package com.example.connect.data.api

import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.model.request.LoginRequest
import com.example.connect.data.model.request.PendidikanRequest
import com.example.connect.data.model.request.RegisterRequest
import com.example.connect.data.model.response.*
import com.example.connect.presentation.main.ui.home.tablayout.agenda.model.AgendaResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiClient {

    @POST("login")
    suspend fun loginAPI(
        @Body loginRequest: LoginRequest
    ): Response<ResponseObjectWrapper<LoginResponse>>

    @POST("register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<ResponseObjectWrapper<RegisterResponse>>

    @GET("profil/{id}")
    suspend fun getProfile(
        @Path("id_user") id: Int
    ): Response<ResponseObjectWrapper<ProfileResponse>>

    @Multipart
    @POST("profil/{id_user}")
    suspend fun updateMyProfile(
        @Header("Authorization") authorization: String,
        @Part("jenis_kelamin") jenis_kelamin: RequestBody?,
        @Part("nim") nim: RequestBody?,
        @Part("tgl_lahir") tanggal_lahir: RequestBody?,
        @Part("domisili") domisili: RequestBody?,
        @Part("wa") wa: RequestBody?,
        @Part image: MultipartBody.Part?,
        @Path("id_user") id_user: Int,
        @QueryMap _method: Map<String, String>
    ): Response<ResponseObjectWrapper<ProfileResponse>>

    @GET("kiriman-public")
    suspend fun getAllKiriman():
            Response<ResponseListWrapper<KirimanResponse>>

    @GET("api-kiriman/{id}")
    suspend fun getDetailKiriman(
        @Path("id") id: Int
    ): Response<ResponseObjectWrapper<KirimanResponse>>

    // API POST KIRIMAN
    @Multipart
    @POST("api-kiriman/{id}")
    suspend fun postKiriman(
        @Header("Authorization") authorization: String,
        @Part("id_profil") id: RequestBody,
        @Part starImage: MultipartBody.Part?,
        @Part("konten") content: RequestBody
    ): Response<ResponseObjectWrapper<AgendaResponse>>

    // API POST PRODUCT
    @Multipart
    @POST("produk")
    suspend fun postProduct(
        @Header("Authorization") authorization: String,
        @Part image: MultipartBody.Part?,
        @Part("harga") harga: Int?,
        @Part("nama_produk") nama_produk: RequestBody?,
        @Part("deskripsi") description: RequestBody?
    ): Response<ResponseObjectWrapper<com.example.connect.data.model.response.ProductResponse>>


    // API GET ALL AGENDA(AGENDAS)
    @GET("agenda-public")
    suspend fun getAllAgenda(): Response<ResponseListWrapper<com.example.connect.data.model.response.AgendaResponse>>

    // API POST AGENDA
    @Multipart
    @POST("agenda")
    suspend fun postAgenda(
        @Header("Authorization") authorization: String,
        @Part("title") title: RequestBody?,
        @Part("lokasi") lokasi: RequestBody?,
        @Part("tanggal") tanggal: RequestBody?,
        @Part("waktu") waktu: RequestBody?,
        @Part("konten") konten: RequestBody?,
        @Part image: MultipartBody.Part?,
        @Part("status") status: Int?
    ): Response<ResponseObjectWrapper<com.example.connect.data.model.response.AgendaResponse>>

    // API GET ALL PRODUCT
    @GET("produk-public")
    suspend fun getAllProductMarkOI(
        @Header("Authorization") authorization: String
    ): Response<ResponseListWrapper<com.example.connect.data.model.response.ProductResponse>>

    // API GET ALL PRODUK BY ADMIN LEVEL
    @GET("produk-lv/{id}")
    suspend fun getAllProductByAdmin(
        @Header("Authorization") authorization: String,
        @Path("id") id: Int
    ): Response<ResponseListWrapper<com.example.connect.data.model.response.ProductResponse>>

    // API GET PRODUCT BY ID USER
    @GET("produk-id/{id}")
    suspend fun getProductByIdUser(
        @Header("Authorization") authorization: String,
        @Path("id") id: Int
    ): Response<ResponseListWrapper<com.example.connect.data.model.response.ProductResponse>>

    // API GET ALL LAYANAN
    @GET("layanan-public")
    suspend fun getAllLayanan(
        @Header("Authorization") authorization: String
    ): Response<ResponseListWrapper<LayananResponse>>


    //API POST PENDIDIKAN
    @POST("pendidikan")
    suspend fun postPendidikan(
        @Header("Authorization") authorization: String,
        @Body pendidikanRequest: PendidikanRequest
    ): Response<ResponseObjectWrapper<PendidikanResponse>>

    // API DELETE PENDIDIKAN
    @DELETE("pendidikan/{id}")
    suspend fun deletePendidikan(
        @Header("Authorization") authorization: String,
        @Path("id") id: Int,
    ): Response<ResponseObjectWrapper<PendidikanResponse>>

}