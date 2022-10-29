package com.example.connect.data.repository

import com.example.connect.data.api.HomeApiClient
import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseListWrapperSementara
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.model.response.AgendaResponse
import com.example.connect.data.model.response.KirimanResponse
import com.example.connect.domain.entity.AgendaEntity
import com.example.connect.domain.entity.KirimanEntity
import com.example.connect.domain.repo.HomeApiRepository
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryInteractor @Inject constructor(
    private val apiClient: HomeApiClient
) : HomeApiRepository {
    override suspend fun getAllAgenda(): Flow<Result<List<AgendaEntity>, ResponseListWrapper<AgendaResponse>>> {
        return flow {
            val response = apiClient.getAllAgenda()
            delay(800)
            if (response.isSuccessful) {
                val body = response.body()?.data
                val data = mutableListOf<AgendaEntity>()
                body?.forEach { data.add(it.toAgendaEntity()) }
                emit(Result.Success(data))
            } else {
            }
        }
    }

    override suspend fun getAgendaByIdUser(id: Int): Flow<Result<List<AgendaEntity>, ResponseListWrapper<AgendaResponse>>> {
        return flow {
            delay(800)
            val response = apiClient.getAgendaByIdUser(id)
            if (response.isSuccessful) {
                val body = response.body()?.data
                val data = mutableListOf<AgendaEntity>()
                body?.forEach { data.add(it.toAgendaEntity()) }
                emit(Result.Success(data))
            } else {
            }
        }
    }

    override suspend fun getDetailAgenda(id: Int): Flow<Result<AgendaEntity, ResponseObjectWrapper<AgendaResponse>>> {
        return flow {
            val response = apiClient.getDetailAgenda(id)
            delay(800)
            if (response.isSuccessful) {
                val body = response.body()?.data
                emit(Result.Success(body?.toAgendaEntity() as AgendaEntity))
            } else {
                response.message()
            }
        }
    }

    override suspend fun getAllKiriman(): Flow<Result<ArrayList<KirimanEntity>, ResponseListWrapperSementara<KirimanResponse>>> {
        return flow {
            val response = apiClient.getAllKiriman()
            delay(800)
            if (response.isSuccessful) {
                val body = response.body()?.data
                val data = arrayListOf<KirimanEntity>()
                body?.forEach {
                    data.add(it.toKirimanEntity())
                }

                emit(Result.Success(data))
            } else {
            }
        }
    }

    override suspend fun getKirimanByIdUser(id: Int): Flow<Result<ArrayList<KirimanEntity>, ResponseListWrapper<KirimanResponse>>> {
        return flow {
            val response = apiClient.getKirimanByIdUser(id)
            delay(800)
            if (response.isSuccessful) {
                val body = response.body()?.data
                val data = arrayListOf<KirimanEntity>()
                body?.forEach { data.add(it.toKirimanEntity()) }
                emit(Result.Success(data))
            } else {
            }
        }
    }

    override suspend fun getDetailKiriman(id: Int): Flow<Result<List<KirimanEntity>, ResponseListWrapper<KirimanResponse>>> {
        return flow {
            delay(800)
            val response = apiClient.getDetailKiriman(id)
            if (response.isSuccessful) {
                val body = response.body()?.data
                val data = mutableListOf<KirimanEntity>()
                body?.forEach { data.add(it.toKirimanEntity()) }
                emit(Result.Success(data))
            } else {
                response.message()
            }
        }
    }

}