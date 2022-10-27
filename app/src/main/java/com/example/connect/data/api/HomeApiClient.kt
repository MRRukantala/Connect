package com.example.connect.data.api

import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseListWrapperSementara
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.model.response.AgendaResponse
import com.example.connect.data.model.response.KirimanResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApiClient {
    // API GET ALL AGENDA(AGENDAS)
    @GET("agenda-public")
    suspend fun getAllAgenda(): Response<ResponseListWrapper<AgendaResponse>>

    @GET("agenda-id/{id}")
    suspend fun getAgendaByIdUser(
        @Path("id") id: Int
    ): Response<ResponseListWrapper<AgendaResponse>>

    @GET("agenda-public/{id}")
    suspend fun getDetailAgenda(
        @Path("id") id: Int
    ): Response<ResponseObjectWrapper<AgendaResponse>>

    // API POST AGENDA


    //news
    @GET("kiriman-public")
    suspend fun getAllKiriman():
            Response<ResponseListWrapperSementara<KirimanResponse>>

    @GET("kiriman-id/{id}")
    suspend fun getKirimanByIdUser(
        @Path("id") id: Int
    ): Response<ResponseListWrapper<KirimanResponse>>

    @GET("api-kiriman/{id}")
    suspend fun getDetailKiriman(
        @Path("id") id: Int
    ): Response<ResponseListWrapper<KirimanResponse>>


}