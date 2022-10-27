package com.example.connect.data.repository

import com.example.connect.data.api.ProfileApiClient
import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.model.request.AgendaRequest
import com.example.connect.data.model.request.KirimanRequest
import com.example.connect.data.model.request.ProductRequest
import com.example.connect.data.model.request.ProfileRequest
import com.example.connect.data.model.response.*
import com.example.connect.domain.entity.*
import com.example.connect.domain.repo.ProfileApiRepository
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryInteractor @Inject constructor(private val apiClient: ProfileApiClient) :
    ProfileApiRepository {
    override suspend fun getProfile(id: Int): Flow<Result<List<ProfileEntity>, ResponseListWrapper<ProfileResponse>>> {
        return flow {
            val response = apiClient.getProfile(id)
            delay(800)
            if (response.isSuccessful) {
                val body = response.body()?.data
                val data = mutableListOf<ProfileEntity>()
                body?.forEach { data.add(it.toProfileEntity()) }
                emit(Result.Success(data))
            } else {
                response.message()

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
            } else {
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
            } else {

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
            } else {
            }
        }
    }

    override suspend fun deletePendidikan(id: Int): Flow<Result<DeletePendidikanEntity, ResponseObjectWrapper<DeletePendidikanResponse>>> {
        return flow {
            val response = apiClient.deletePendidikan(id)
            delay(1500)
            if (response.isSuccessful) {
                val deletePendidikanEntity = response.body()?.data?.toDeletePendidikanEntity()
                emit(Result.Success(deletePendidikanEntity!!))
            } else {
            }
        }
    }


    override suspend fun postPendidikan(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }

}