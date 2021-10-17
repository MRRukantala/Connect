package com.example.connect.utilites

import com.example.connect.main.ui.home.agenda.model.AgendaResponse
import com.example.connect.main.ui.home.news.model.Post
import com.example.connect.main.ui.home.news.model.PostResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// Base URL
private val BASE_URL = "https://umconnect.cahyapro.com/public/api/"


// Get Path Kiriman
const val getKiriman = "kiriman-public"
const val getDetailKiriman = "api-kiriman/{id}"

// Get Path Agenda
const val getAgenda = "agenda-public"


val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory()).baseUrl(BASE_URL).build()

interface Utilities {
    // API GET ALL KIRIMAN(POSTS)
    @GET(getKiriman)
    fun getAllKiriman(): Deferred<PostResponse>

    // API GET DETAIL KIRIMAN(POST)
    @GET(getDetailKiriman)
    fun getDetailKiriman(@Path("id") id: Int): Deferred<Post>

    // API POST KIRIMAN


    // API GET ALL AGENDA(AGENDAS)
    @GET(getAgenda)
    fun getAllAgenda(): Deferred<AgendaResponse>


}

object MarkOIApi {
    val retrofitService: Utilities by lazy {
        retrofit.create(Utilities::class.java)
    }
}