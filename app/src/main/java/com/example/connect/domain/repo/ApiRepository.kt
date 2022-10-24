package com.example.connect.domain.repo

import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseListWrapperSementara
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.auth.ResponseObjectWrapperSementara
import com.example.connect.data.model.request.AgendaRequest
import com.example.connect.data.model.request.LoginRequest
import com.example.connect.data.model.response.*
import com.example.connect.domain.entity.*
import kotlinx.coroutines.flow.Flow
import com.example.connect.utilites.base.Result

interface ApiRepository {
    //diperbaiki
    suspend fun loginApi(loginRequest: LoginRequest): Flow<Result<LoginEntity, ResponseObjectWrapperSementara<LoginResponse>>>

    suspend fun getAllAgenda(): Flow<Result<List<AgendaEntity>, ResponseListWrapper<AgendaResponse>>>

    suspend fun getAgendaByIdUser(id: Int): Flow<Result<List<AgendaEntity>, ResponseListWrapper<AgendaResponse>>>

    suspend fun getDetailAgenda(id: Int): Flow<Result<AgendaEntity, ResponseObjectWrapper<AgendaResponse>>>

    suspend fun getAllKiriman(): Flow<Result<List<KirimanEntity>, ResponseListWrapperSementara<KirimanResponse>>>

    suspend fun getKirimanByIdUser(
        id: Int
    ): Flow<Result<List<KirimanEntity>, ResponseListWrapper<KirimanResponse>>>

    suspend fun getDetailKiriman(id: Int): Flow<Result<KirimanEntity, ResponseObjectWrapper<KirimanResponse>>>

    suspend fun getAllLayanan(): Flow<Result<List<LayananEntity>, ResponseListWrapper<LayananResponse>>>

    suspend fun getDetailLayanan(id: Int): Flow<Result<LayananEntity, ResponseObjectWrapper<LayananResponse>>>

    suspend fun getProfile(id: Int): Flow<Result<ProfileEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun getAllProductByAdmin(id:Int): Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>>

    suspend fun getAllProductMarkOI(): Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>>

    suspend fun getProductByIdUser(id:Int):Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>>

    suspend fun postAgenda(agendaRequest: AgendaRequest): Flow<Result<PostAgendaEntity, ResponseObjectWrapper<AgendaResponse>>>

    //belom diperbaiki
    suspend fun register(): Flow<Result<SementaraEntity, ResponseObjectWrapper<RegisterResponse>>>

    suspend fun updateMyProfile(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun postKiriman(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun postProduct(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>



    suspend fun postPendidikan(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>

    suspend fun deletePendidikan(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>>


}