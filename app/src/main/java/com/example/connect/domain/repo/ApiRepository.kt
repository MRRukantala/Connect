package com.example.connect.domain.repo

import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.model.response.LoginResponse
import com.example.connect.data.model.response.ProfileResponse
import com.example.connect.data.model.response.RegisterResponse
import com.example.connect.domain.entity.SementaraEntity
import kotlinx.coroutines.flow.Flow
import com.example.connect.utilites.base.Result

interface ApiRepository {
    suspend fun loginApi(): Flow<Result<SementaraEntity, ResponseObjectWrapper<LoginResponse>>>

    suspend fun register(): Flow<Result<SementaraEntity, ResponseObjectWrapper<RegisterResponse>>>

    suspend fun getProfile(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun updateMyProfile(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun getAllKiriman(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun getDetailKiriman(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun postKiriman(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun postProduct(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun getAllAgenda(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun postAgenda(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun getAllProductMarkOI(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun getAllProductByAdmin(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun getProductByIdUser(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun getAllLayanan(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun postPendidikan(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun deletePendidikan(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>


}