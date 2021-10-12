package com.example.connect.utilites

import com.example.connect.main.ui.home.news.model.PostResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// Base URL
private val BASE_URL = "https://desa-pintar.id/ivlab/public/api/"

// Get Path Kiriman
const val getKiriman = "kiriman"


val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory()).baseUrl(BASE_URL).build()

interface Utilities {
    // API GET ALL KIRIMAN(POSTS)
    @GET("getProduk")
    fun getAllKiriman(): Deferred<PostResponse>
}

object MarkOIApi {
    val retrofitService: Utilities by lazy {
        retrofit.create(Utilities::class.java)
    }
}