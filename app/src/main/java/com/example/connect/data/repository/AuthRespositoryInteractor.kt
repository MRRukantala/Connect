package com.example.connect.data.repository

import com.example.connect.data.api.AuthApiClient
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.auth.ResponseObjectWrapperSementara
import com.example.connect.data.model.request.LoginRequest
import com.example.connect.data.model.request.RegisterRequest
import com.example.connect.data.model.response.BasicResponse
import com.example.connect.data.model.response.LoginResponse
import com.example.connect.data.model.response.RegisterResponse
import com.example.connect.data.model.response.isNotSuccesfull
import com.example.connect.domain.entity.LoginEntity
import com.example.connect.domain.entity.RegisterEntity
import com.example.connect.domain.repo.AuthApiRepository
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRespositoryInteractor @Inject constructor(
    private val apiClient: AuthApiClient
) : AuthApiRepository{
    override suspend fun loginApi(loginRequest: LoginRequest): Flow<Result<LoginEntity, BasicResponse>> {
        return flow {
            delay(1000)
            val response = apiClient.loginAPI(loginRequest)
            if (response.isSuccessful) {
                val loginEntity = response.body()?.data?.toLoginEntity()
                emit(Result.Success(loginEntity!!))
            } else {
                emit(Result.Error(isNotSuccesfull(response.errorBody()!!)))
            }
        }
    }

    override suspend fun register(registerRequest: RegisterRequest): Flow<Result<RegisterEntity, ResponseObjectWrapper<RegisterResponse>>> {
        return flow {
            val response = apiClient.register(registerRequest)
            delay(800)
            if (response.isSuccessful) {
                val registerEntity = response.body()?.data?.toRegisterEntity()
                emit(Result.Success(registerEntity!!))
            } else {
            }
        }
    }
}