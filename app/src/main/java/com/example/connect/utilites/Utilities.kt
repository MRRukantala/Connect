package com.example.connect.utilites

import com.example.connect.login.data.model.UserResponse
import com.example.connect.main.ui.product.model.ProductResponse
import com.example.connect.main.ui.home.tablayout.agenda.model.AgendaResponse
import com.example.connect.main.ui.home.tablayout.news.add.AddedResponses
import com.example.connect.main.ui.home.tablayout.news.model.Post
import com.example.connect.main.ui.home.tablayout.news.model.PostResponse
import com.example.connect.main.ui.layanan.Layanan
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.io.File

// Base URL
private val BASE_URL = "https://umconnect.cahyapro.com/public/api/"

// Path Image
val GET_PATH_IMAGE = "https://umconnect.cahyapro.com/storage/"


// Get Path Login
const val login = "login"

// Get Path Signup
const val register = "register"

// Get Path Product MarkOI
const val productMarkOI = "produk-public"
const val productByIdUser = "produk-id/{id}"

// Get Path Product MarkOI
const val layanan = "layanan-public"

// Get Path Kiriman
const val getKiriman = "kiriman-public"
const val getDetailKiriman = "api-kiriman/{id}"
const val postKiriman = "api-kiriman"

// Get Path Agenda
const val getAgenda = "agenda-public"


val moshi = Moshi
    .Builder()
    .add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory()).baseUrl(BASE_URL).build()

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
    ): Deferred<com.example.connect.signup.data.model.UserResponse>



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
        @Part ("gambar" ) gambar: RequestBody,
        @Part("content") content: RequestBody
        ) : Deferred<AddedResponses>


    // API GET ALL AGENDA(AGENDAS)
    @GET(getAgenda)
    fun getAllAgenda(): Deferred<AgendaResponse>

    // API GET ALL PRODUCT
    @GET(productMarkOI)
    fun getAllProductMarkOI(
        @Header("Authorization") authorization: String
    ) : Deferred<ProductResponse>

    // API GET PRODUCT BY ID USER
    @GET(productByIdUser)
    fun getProductByIdUser(
        @Path("id") id : Int
    ) : Deferred<ProductResponse>

    // API GET ALL LAYANAN
    @GET(layanan)
    fun getAllLayanan(
        @Header("Authorization") authorization: String
    ) : Deferred<Layanan>

}

object MarkOIApi {
    val retrofitService: Utilities by lazy {
        retrofit.create(Utilities::class.java)
    }
}
