package com.example.connect.data.repository

import android.util.Log
import com.example.connect.data.api.ApiClient
import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseListWrapperSementara
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.auth.ResponseObjectWrapperSementara
import com.example.connect.data.model.request.AgendaRequest
import com.example.connect.data.model.request.KirimanRequest
import com.example.connect.data.model.request.LoginRequest
import com.example.connect.data.model.response.*
import com.example.connect.domain.entity.*
import com.example.connect.domain.repo.ApiRepository
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    private val apiClient: ApiClient
) : ApiRepository {
    //udah diperbaiki
    override suspend fun loginApi(loginRequest: LoginRequest): Flow<Result<LoginEntity, ResponseObjectWrapperSementara<LoginResponse>>> {
        return flow {
            val response = apiClient.loginAPI(loginRequest)
            delay(1500)
            if (response.isSuccessful) {
                val loginEntity = response.body()?.data?.toLoginEntity()
                emit(Result.Success(loginEntity!!))
            } else {

            }
        }
    }

    override suspend fun getAllAgenda(): Flow<Result<List<AgendaEntity>, ResponseListWrapper<AgendaResponse>>> {
        return flow {
            val response = apiClient.getAllAgenda()
            delay(800)
            if (response.isSuccessful) {
                val body = response.body()?.data
                val data = mutableListOf<AgendaEntity>()
                body?.forEach { data.add(it.toAgendaEntity()) }
                emit(Result.Success(data))
                Log.v("VIEWMODEL_AGENDA", response.body()?.data?.get(1).toString())
            } else {
            }
        }
    }

    override suspend fun getAgendaByIdUser(id: Int): Flow<Result<List<AgendaEntity>, ResponseListWrapper<AgendaResponse>>> {
        return flow {
            val response = apiClient.getAgendaByIdUser(id)
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

    override suspend fun getAllKiriman(): Flow<Result<List<KirimanEntity>, ResponseListWrapperSementara<KirimanResponse>>> {
        return  flow {
            val response = apiClient.getAllKiriman()
            delay(800)
            if (response.isSuccessful){
                val body = response.body()?.data
                val data = mutableListOf<KirimanEntity>()
                body?.forEach {
                    data.add(it.toKirimanEntity())
                    Log.v("VIEWMODEL_BERITA", response.toString())
                }

                emit(Result.Success(data))
            }else{
                Log.v("VIEWMODEL_BERITA", response.toString())
            }
        }
    }

    override suspend fun getKirimanByIdUser(id: Int): Flow<Result<List<KirimanEntity>, ResponseListWrapper<KirimanResponse>>> {
        return flow {
            val response = apiClient.getKirimanByIdUser(id)
            delay(800)
            if (response.isSuccessful) {
                val body = response.body()?.data
                val data = mutableListOf<KirimanEntity>()
                body?.forEach { data.add(it.toKirimanEntity()) }
                emit(Result.Success(data))
            } else {
            }
        }
    }

    override suspend fun getDetailKiriman(id: Int): Flow<Result<KirimanEntity, ResponseObjectWrapper<KirimanResponse>>> {
        return flow {
            val response = apiClient.getDetailKiriman(id)
            delay(800)
            if (response.isSuccessful) {
                val body = response.body()?.data
                emit(Result.Success(body?.toKirimanEntity() as KirimanEntity))
            } else {
                response.message()
            }
        }
    }

    override suspend fun getAllLayanan(): Flow<Result<List<LayananEntity>, ResponseListWrapper<LayananResponse>>> {
        return flow {
            val response = apiClient.getAllLayanan()
            delay(800)
            if (response.isSuccessful) {
                val body = response.body()?.data
                val data = mutableListOf<LayananEntity>()
                body?.forEach { data.add(it.toLayananEntity()) }
                emit(Result.Success(data))
                Log.v("VIEWMODE_LAYANAN", response.toString())
            } else {
                Log.v("VIEWMODE_LAYANAN", response.toString())
            }
        }
    }

    override suspend fun getDetailLayanan(id: Int): Flow<Result<LayananEntity, ResponseObjectWrapper<LayananResponse>>> {
        return flow {
            val response = apiClient.getDetailLayanan(id)
            delay(800)
            if (response.isSuccessful) {
                val body = response.body()?.data
                emit(Result.Success(body?.toLayananEntity() as LayananEntity))
            } else {
                response.message()
            }
        }
    }

    override suspend fun getProfile(id:Int): Flow<Result<ProfileEntity, ResponseObjectWrapper<ProfileResponse>>> {
        return flow {
            val response = apiClient.getProfile(id)
            delay(800)
            if (response.isSuccessful){
                val body = response.body()?.data
                emit(Result.Success(body?.toProfileEntity() as ProfileEntity))
            }else{
                response.message()
            }
        }
    }

    override suspend fun getAllProductByAdmin(id: Int): Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>> {
        return flow {
            val response = apiClient.getAllProductByAdmin(id)
            delay(800)
            if (response.isSuccessful){
                val body = response.body()?.data
                val data = mutableListOf<ProductEntity>()
                body?.forEach { data.add(it.toProductEntity()) }
                emit(Result.Success(data))
            }else{

            }
        }
    }

    override suspend fun getAllProductMarkOI(): Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>> {
        return flow {
            val response = apiClient.getAllProductMarkOI()
            delay(800)
            if (response.isSuccessful){
                val body = response.body()?.data
                val data = mutableListOf<ProductEntity>()
                body?.forEach { data.add(it.toProductEntity()) }
                emit(Result.Success(data))
            }else{

            }
        }
    }

    override suspend fun getProductByIdUser(id: Int): Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>> {
        return flow {
            val response = apiClient.getProductByIdUser(id)
            delay(800)
            if (response.isSuccessful){
                val body = response.body()?.data
                val data = mutableListOf<ProductEntity>()
                body?.forEach { data.add(it.toProductEntity()) }
                emit(Result.Success(data))
                Log.v("VIEWMODE_PRODUCT_USER", response.toString())
            }else{

                Log.v("VIEWMODE_PRODUCT_USER", response.toString())

            }
        }
    }

    override suspend fun postAgenda(agendaRequest: AgendaRequest): Flow<Result<List<PostAgendaEntity>, ResponseListWrapper<AgendaResponse>>> {
        return flow {
            val response = apiClient.postAgenda(
                agendaRequest.title,
                agendaRequest.lokasi,
                agendaRequest.tanggal,
                agendaRequest.waktu,
                agendaRequest.konten,
                agendaRequest.image,
                agendaRequest.status
            )
            delay(1000)
            if (response.isSuccessful) {
                val postAgendaEntity = response.body()?.data
                val data = mutableListOf<PostAgendaEntity>()
                postAgendaEntity?.forEach { data.add(it.toPosAgendaEntity()) }
                emit(Result.Success(data))
                Log.v("VIEWMODEL POST DATA", response.toString())
            } else {
                Log.v("VIEWMODEL POST DATA", response.toString())

            }
        }


    }

    override suspend fun postKiriman(kirimanRequest: KirimanRequest): Flow<Result<PostKirimanEntity, ResponseObjectWrapper<PostKirimanResponse>>> {
        return flow {
            val response = apiClient.postKiriman(
                kirimanRequest.starImage,
                kirimanRequest.content
            )
            delay(1000)
            if (response.isSuccessful) {
                val postKirimanEntity = response.body()?.data?.toPostKirimanEntity()
                emit(Result.Success(postKirimanEntity!!))
            } else {

            }
        }
    }


    //belom diperbaiki
    override suspend fun register(): Flow<Result<SementaraEntity, ResponseObjectWrapper<RegisterResponse>>> {
        TODO("Not yet implemented")
    }



    override suspend fun updateMyProfile(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }







    override suspend fun postProduct(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }













    override suspend fun postPendidikan(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun deletePendidikan(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }
}