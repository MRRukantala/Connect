package com.example.connect.domain.usecase

import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.auth.ResponseObjectWrapperSementara
import com.example.connect.data.model.request.LoginRequest
import com.example.connect.data.model.request.RegisterRequest
import com.example.connect.data.model.response.BasicResponse
import com.example.connect.data.model.response.LoginResponse
import com.example.connect.data.model.response.RegisterResponse
import com.example.connect.domain.entity.LoginEntity
import com.example.connect.domain.entity.RegisterEntity
import com.example.connect.domain.repo.AuthApiRepository
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val repository: AuthApiRepository) {
    suspend fun login(loginRequest: LoginRequest):
            Flow<Result<LoginEntity, BasicResponse>> {
        return repository.loginApi(loginRequest)
    }

    suspend fun register(registerRequest: RegisterRequest): Flow<Result<RegisterEntity, ResponseObjectWrapper<RegisterResponse>>> {
        return repository.register(registerRequest)
    }
}