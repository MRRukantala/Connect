package com.example.connect.domain.repo

import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.model.response.DetailArtikelResponse
import com.example.connect.data.model.response.LayananResponse
import com.example.connect.data.model.response.elearning.PlaylistElearningResponse
import com.example.connect.data.model.response.elearning.VideoELearningResponse
import com.example.connect.domain.entity.LayananEntity
import com.example.connect.domain.entity.elearning.PlaylistELearningEntity
import com.example.connect.domain.entity.elearning.VideoELearningEntity
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.Flow

interface LayananApiRepository {

    suspend fun getAllLayanan(): Flow<Result<List<LayananEntity>, ResponseListWrapper<LayananResponse>>>

    suspend fun getDetailLayanan(id: Int): Flow<Result<LayananEntity, ResponseObjectWrapper<DetailArtikelResponse>>>

    suspend fun getAllPlaylist(): Flow<Result<List<PlaylistELearningEntity>, ResponseListWrapper<PlaylistElearningResponse>>>

    suspend fun getAllVideoELearning(id: Int): Flow<Result<List<VideoELearningEntity>, ResponseListWrapper<VideoELearningResponse>>>

}