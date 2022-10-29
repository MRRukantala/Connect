package com.example.connect.presentation.main.layanan.elearning.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.elearning.PlaylistELearningByIdEntity
import com.example.connect.domain.usecase.LayananUseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoELearningViewModel @Inject constructor(
    private val useCase: LayananUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<VideoELearningState>(VideoELearningState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<PlaylistELearningByIdEntity>(
        PlaylistELearningByIdEntity(
            0, "", "", "", mutableListOf()
        )
    )
    val data get() = _data

    private fun loading() {
        _state.value = VideoELearningState.Loading()
    }

    private fun success(videoElearningEntity: PlaylistELearningByIdEntity) {
        _state.value = VideoELearningState.Success(videoElearningEntity)
        _data.value = videoElearningEntity

    }

    private fun error(videoElearningEntity: PlaylistELearningByIdEntity) {
        _state.value = VideoELearningState.Error(videoElearningEntity)
    }

    fun getAllVideo(id: Int) {
        viewModelScope.launch {
            useCase.getPlaylistById(id)
                .onStart {
                    loading()

                }.catch {

                }.collect { result ->
                    when (result) {
                        is Result.Success -> {
                            success(result.data)
                        }
                        is Result.Error -> {}
                    }
                }
        }
    }
}

sealed class VideoELearningState {
    object Init : VideoELearningState()
    data class Loading(val loading: Boolean = true) : VideoELearningState()
    data class Success(val videoElearningEntity: PlaylistELearningByIdEntity) :
        VideoELearningState()

    data class Error(val response: PlaylistELearningByIdEntity) : VideoELearningState()
}