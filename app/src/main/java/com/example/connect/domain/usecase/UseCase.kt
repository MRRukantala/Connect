package com.example.connect.domain.usecase

import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.model.response.RegisterResponse
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.repo.ApiRepository
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCase @Inject constructor(
    private val repository: ApiRepository
) {
    suspend fun register(): Flow<Result<SementaraEntity, ResponseObjectWrapper<RegisterResponse>>>{
        return repository.register()
    }
}