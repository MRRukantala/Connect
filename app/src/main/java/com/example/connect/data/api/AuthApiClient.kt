package com.example.connect.data.api

import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.auth.ResponseObjectWrapperSementara
import com.example.connect.data.model.request.LoginRequest
import com.example.connect.data.model.request.RegisterRequest
import com.example.connect.data.model.response.LoginResponse
import com.example.connect.data.model.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiClient {
    //AUTH
    @POST("login")
    suspend fun loginAPI(
        @Body loginRequest: LoginRequest
    ): Response<ResponseObjectWrapperSementara<LoginResponse>>

    @POST("register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<ResponseObjectWrapper<RegisterResponse>>
}