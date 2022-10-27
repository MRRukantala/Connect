package com.example.connect.domain.repo

import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseListWrapperSementara
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.model.response.AgendaResponse
import com.example.connect.data.model.response.KirimanResponse
import com.example.connect.domain.entity.AgendaEntity
import com.example.connect.domain.entity.KirimanEntity
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.Flow

interface HomeApiRepository {
    suspend fun getAllAgenda(): Flow<Result<List<AgendaEntity>, ResponseListWrapper<AgendaResponse>>>

    suspend fun getAgendaByIdUser(id: Int): Flow<Result<List<AgendaEntity>, ResponseListWrapper<AgendaResponse>>>

    suspend fun getDetailAgenda(id: Int): Flow<Result<AgendaEntity, ResponseObjectWrapper<AgendaResponse>>>

    suspend fun getAllKiriman(): Flow<Result<List<KirimanEntity>, ResponseListWrapperSementara<KirimanResponse>>>

    suspend fun getKirimanByIdUser(
        id: Int
    ): Flow<Result<List<KirimanEntity>, ResponseListWrapper<KirimanResponse>>>

    suspend fun getDetailKiriman(id: Int): Flow<Result<List<KirimanEntity>, ResponseListWrapper<KirimanResponse>>>
}