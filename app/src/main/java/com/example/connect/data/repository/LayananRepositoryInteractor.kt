package com.example.connect.data.repository

import com.example.connect.data.api.LayananApiClient
import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.model.response.DetailArtikelResponse
import com.example.connect.data.model.response.LayananResponse
import com.example.connect.data.model.response.elearning.PlaylistElearningByIdResponse
import com.example.connect.data.model.response.elearning.PlaylistElearningResponse
import com.example.connect.data.model.response.elearning.VideoELearningResponse
import com.example.connect.domain.entity.LayananEntity
import com.example.connect.domain.entity.elearning.PlaylistELearningByIdEntity
import com.example.connect.domain.entity.elearning.PlaylistELearningEntity
import com.example.connect.domain.entity.elearning.VideoELearningEntity
import com.example.connect.domain.repo.LayananApiRepository
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LayananRepositoryInteractor @Inject constructor(private val apiClient: LayananApiClient) :
    LayananApiRepository {
    override suspend fun getAllLayanan(): Flow<Result<List<LayananEntity>, ResponseListWrapper<LayananResponse>>> {
        return flow {
            val response = apiClient.getAllLayanan()
            delay(800)
            if (response.isSuccessful) {
                val body = response.body()?.data
                val data = mutableListOf<LayananEntity>()
                body?.forEach { data.add(it.toLayananEntity()) }
                emit(Result.Success(data.reversed()))
            } else {
            }
        }
    }

    override suspend fun getDetailLayanan(id: Int): Flow<Result<LayananEntity, ResponseObjectWrapper<DetailArtikelResponse>>> {
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

    override suspend fun getAllPlaylist(): Flow<Result<List<PlaylistELearningEntity>, ResponseListWrapper<PlaylistElearningResponse>>> {
        return flow {
            val response = apiClient.getPlaylist()
            delay(800)
            if (response.isSuccessful) {
                val body = response.body()?.data
                val data = mutableListOf<PlaylistELearningEntity>()
                body?.forEach { data.add(it.toPlaylistELearningEntity()) }
                emit(Result.Success(data.reversed()))
            } else {

            }
        }
    }

    override suspend fun getPlaylistById(id: Int): Flow<Result<PlaylistELearningByIdEntity, ResponseObjectWrapper<PlaylistElearningByIdResponse>>> {
        return flow {
            val response = apiClient.getPlaylist(id)
            delay(800)
            if (response.isSuccessful) {
                val data = response.body()?.data
                emit(Result.Success(data?.toPlaylistELearningByIdEntity() as PlaylistELearningByIdEntity))
            } else {

            }
        }
    }

    override suspend fun getAllVideoELearning(id: Int): Flow<Result<List<VideoELearningEntity>, ResponseListWrapper<VideoELearningResponse>>> {
        return flow {
            val response = apiClient.getVideoPlaylist(id)
            delay(800)
            if (response.isSuccessful) {
                val body = response.body()?.data
                val data = mutableListOf<VideoELearningEntity>()
                body?.forEach { data.add(it.toVideoELearningEntity()) }
                emit(Result.Success(data.reversed()))
            } else {

            }
        }
    }
}