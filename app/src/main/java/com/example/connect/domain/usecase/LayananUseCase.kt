package com.example.connect.domain.usecase

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
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LayananUseCase @Inject constructor(private val repository: LayananApiRepository) {
    suspend fun getLayanan():
            Flow<Result<List<LayananEntity>, ResponseListWrapper<LayananResponse>>> {
        return repository.getAllLayanan()
    }

    suspend fun getDetailLayanan(id: Int):
            Flow<Result<LayananEntity, ResponseObjectWrapper<DetailArtikelResponse>>> {
        return repository.getDetailLayanan(id)
    }

    suspend fun getAllPlaylist(): Flow<Result<List<PlaylistELearningEntity>, ResponseListWrapper<PlaylistElearningResponse>>> {
        return repository.getAllPlaylist()
    }

    suspend fun getPlaylistById(id: Int): Flow<Result<PlaylistELearningByIdEntity, ResponseObjectWrapper<PlaylistElearningByIdResponse>>> {
        return repository.getPlaylistById(id)
    }

    suspend fun getAllVideoELearning(id: Int): Flow<Result<List<VideoELearningEntity>, ResponseListWrapper<VideoELearningResponse>>> {
        return repository.getAllVideoELearning(id)
    }
}