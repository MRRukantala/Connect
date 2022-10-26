package com.example.connect.data.repository

import android.util.Log
import com.example.connect.data.api.ApiClient
import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseListWrapperSementara
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.auth.ResponseObjectWrapperSementara
import com.example.connect.data.model.request.*
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

    override suspend fun register(registerRequest: RegisterRequest): Flow<Result<RegisterEntity, ResponseObjectWrapper<RegisterResponse>>> {
        return flow {
            val response = apiClient.register(registerRequest)
            delay(1500)
            if (response.isSuccessful) {
                val registerEntity = response.body()?.data?.toRegisterEntity()
                emit(Result.Success(registerEntity!!))
                Log.v("VIEWMODEL_REGISTER", response.body()?.data.toString())
            } else {
                Log.v("VIEWMODEL_REGISTER", response.body()?.data.toString())

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

    override suspend fun getProfile(id:Int): Flow<Result<List<ProfileEntity>, ResponseListWrapper<ProfileResponse>>> {
        return flow {
            val response = apiClient.getProfile(id)
            delay(800)
            if (response.isSuccessful){
                val body = response.body()?.data
                val data = mutableListOf<ProfileEntity>()
                body?.forEach { data.add(it.toProfileEntity()) }
                emit(Result.Success(data))
                Log.v("VIEWMODE_GET_USER", response.toString())
            }else{
                Log.v("VIEWMODE_GET_USER", response.toString())
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

    override suspend fun postProduct(productRequest: ProductRequest): Flow<Result<List<PostProductEntity>, ResponseListWrapper<PostProductResponse>>> {
        return flow {
            val response = apiClient.postProduct(
                productRequest.image,
                productRequest.harga,
                productRequest.namaProduk,
                productRequest.description
            )
            delay(1000)
            if (response.isSuccessful) {
                val postProductEntity = response.body()?.data
                val data = mutableListOf<PostProductEntity>()
                postProductEntity?.forEach { data.add(it.toPostProductEntity()) }
                emit(Result.Success(data))
                Log.v("VIEWMODEL POST DATA", response.toString())
            } else {
                Log.v("VIEWMODEL POST DATA", response.toString())

            }
        }
    }

    override suspend fun updateMyProfile(
        profileRequest: ProfileRequest,
        id: Int,
        method: Map<String, String>
    ): Flow<Result<EditProfleEntity, ResponseObjectWrapper<EditProfileResponse>>> {
        return flow {
            val response = apiClient.updateMyProfile(
                profileRequest.jenis_kelamin,
                profileRequest.nim,
                profileRequest.tanggal_lahir,
                profileRequest.domisili,
                profileRequest.wa,
                profileRequest.image,
                id,
                method
            )
            delay(1000)
            if (response.isSuccessful) {
                val editProfleEntity = response.body()?.data?.toEditProfileEntity()
                emit(Result.Success(editProfleEntity!!))
                Log.v("VIEWMODEL EDIT PROFILE", response.toString())
            } else {

                Log.v("VIEWMODEL EDIT PROFILE", response.toString())

            }
        }
    }

    override suspend fun getDetailProduct(id: Int): Flow<Result<List<DetailProductEntity>, ResponseListWrapperSementara<ProductResponse>>> {
        return flow {
            val response = apiClient.getDetailProduct(id)
            if (response.isSuccessful) {
                delay(800)
                val body = response.body()?.data
                val data = mutableListOf<DetailProductEntity>()
                body?.forEach {
                    data.add(it.toDetailProductEntity())
                }

                Log.v("VIEWMODELDETAILPRODUCT", data.toString())
                emit(Result.Success(data))

            } else {
                response.message()
            }
        }
    }


    //belom diperbaiki


























    override suspend fun postPendidikan(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun deletePendidikan(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }
}