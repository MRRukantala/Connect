package com.example.connect.utilites

import com.example.connect.presentation.login.data.model.UserResponse
import com.example.connect.presentation.main.ui.home.tablayout.agenda.model.AgendaResponse
import com.example.connect.presentation.main.ui.home.tablayout.news.add.AddedResponses
import com.example.connect.presentation.main.ui.home.tablayout.news.model.Post
import com.example.connect.presentation.main.ui.home.tablayout.news.model.PostResponse
import com.example.connect.presentation.main.ui.layanan.Layanan
import com.example.connect.presentation.main.ui.menu.info_pendidikan.MyData
import com.example.connect.presentation.main.ui.menu.info_pendidikan.message
import com.example.connect.presentation.main.ui.product.model.ProductResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

// Base URL
private val BASE_URL = "https://umconnect.cahyapro.com/public/api/"

// Path Image
val GET_PATH_IMAGE = "https://umconnect.cahyapro.com/storage/"


// Get Path POST Pendidikan
const val pendidikan = "pendidikan"

// Get Path Login
const val login = "login"

// Get Path Signup
const val register = "register"

// Get Path MyProfile
const val profile = "profil/{id}"

// Get Path Product MarkOI
const val productMarkOI = "produk-public"
const val productByIdUser = "produk-id/{id}"
const val level = "produk-lv/{id}"

// Get Path Product MarkOI
const val layanan = "layanan-public"
const val postProduct = "produk"

// Get Path Kiriman
const val getKiriman = "kiriman-public"
const val getDetailKiriman = "api-kiriman/{id}"
const val postKiriman = "api-kiriman"
const val getKirimanByIdUser = "kiriman-id/{id}"

// Get Path Agenda
const val getAgenda = "agenda-public"
const val postAgenda= "agenda"


val moshi = Moshi
    .Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

fun myHttpClient(): OkHttpClient {
    val builder = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    return builder.build()
}


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(myHttpClient())
    .baseUrl(BASE_URL).build()

interface Utilities {

    // API LOGIN
    @FormUrlEncoded
    @POST(login)
    fun loginAPI(
        @Field("email") email: String,
        @Field("password") password: String
    ):
            Deferred<UserResponse>

    // API SIGNUP
    @FormUrlEncoded
    @POST(register)
    fun registerAPI(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("level") level: Int = 2
    ): Deferred<com.example.connect.presentation.signup.data.model.UserResponse>


    // GET MY PROFILE
    @GET("profil/{id_user}")
    fun getMyProfile(
        @Header("Authorization") authorization: String,
        @Path("id_user") id: Int
    )
            : Deferred<MyData>

    // UPDATE MY PROFILE
    @Multipart
    @POST("profil/{id_user}")
    fun updateMyProfile(
        @Header("Authorization") authorization: String,
        @Part("jenis_kelamin") jenis_kelamin : RequestBody?,
        @Part("nim") nim : RequestBody?,
        @Part("tgl_lahir") tanggal_lahir : RequestBody?,
        @Part("domisili") domisili: RequestBody?,
        @Part("wa") wa : RequestBody?,
        @Part image: MultipartBody.Part?,
        @Path("id_user") id_user: Int,
        @QueryMap _method : Map<String, String>
    ) : Deferred<message>

    // API GET ALL KIRIMAN(POSTS)
    @GET(getKiriman)
    fun getAllKiriman(): Deferred<PostResponse>

    // API GET DETAIL KIRIMAN(POST)
    @GET(getDetailKiriman)
    fun getDetailKiriman(@Path("id") id: Int): Deferred<Post>

    // API POST KIRIMAN
    @Multipart
    @POST(postKiriman)
    fun postKiriman(
        @Header("Authorization") authorization: String,
        @Part("id_profil") id: RequestBody,
        @Part starImage: MultipartBody.Part?,
        @Part("konten") content: RequestBody
    ): Deferred<AddedResponses>

    // API POST PRODUCT
    @Multipart
    @POST(postProduct)
    fun postProduct(
        @Header("Authorization") authorization: String,
        @Part image: MultipartBody.Part?,
        @Part ("harga") harga: Int?,
        @Part("nama_produk") nama_produk: RequestBody?,
        @Part("deskripsi") description: RequestBody?
    ) : Deferred<message>


    // API GET ALL AGENDA(AGENDAS)
    @GET(getAgenda)
    fun getAllAgenda(): Deferred<AgendaResponse>

    // API POST AGENDA
    @Multipart
    @POST(postAgenda)
    fun postAgenda(
        @Header("Authorization") authorization: String,
        @Part ("title") title : RequestBody?,
        @Part ("lokasi") lokasi : RequestBody?,
        @Part ("tanggal") tanggal : RequestBody?,
        @Part ("waktu") waktu : RequestBody?,
        @Part ("konten") konten : RequestBody?,
        @Part image  : MultipartBody.Part?,
        @Part ("status") status: Int?
//        ,
//        @Part ("id_user") id_user: RequestBody
    ): Deferred<message>

    // API GET ALL PRODUCT
    @GET(productMarkOI)
    fun getAllProductMarkOI(
        @Header("Authorization") authorization: String
    ): Deferred<ProductResponse>

    // API GET ALL PRODUK BY ADMIN LEVEL
    @GET(level)
    fun getAllProductByAdmin(
        @Header("Authorization") authorization: String,
        @Path("id") id: Int
    ) : Deferred<ProductResponse>

    // API GET PRODUCT BY ID USER
    @GET(productByIdUser)
    fun getProductByIdUser(
        @Header("Authorization") authorization: String,
        @Path("id") id: Int
    ): Deferred<ProductResponse>

    // API GET ALL LAYANAN
    @GET(layanan)
    fun getAllLayanan(
        @Header("Authorization") authorization: String
    ): Deferred<Layanan>


    //API POST PENDIDIKAN
    @FormUrlEncoded
    @POST(pendidikan)
    fun postPendidikan(
        @Header("Authorization") authorization: String,
        @Field("instansi") instansi: String,
        @Field("jenjang") jenjang: String,
        @Field("fakultas") fakultas: String,
        @Field("jurusan") jurusan: String,
        @Field("tahun_masuk") tahun_masuk: String,
        @Field("tahun_keluar") tahun_keluar: String
    ) : Deferred<message>

    // API DELETE PENDIDIKAN
    @DELETE("pendidikan/{id}")
    fun deletePendidikan(
        @Header("Authorization") authorization: String,
        @Path("id") id: Int,
    ) : Deferred<message>
}

object MarkOIApi {
    val retrofitService: Utilities by lazy {
        retrofit.create(Utilities::class.java)
    }
}
