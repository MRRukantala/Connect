package com.example.connect.presentation.main.layanan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.LayananEntity
import com.example.connect.domain.entity.elearning.PlaylistELearningEntity
import com.example.connect.domain.usecase.LayananUseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LayananViewModel @Inject constructor(
    private val useCase: LayananUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<LayananState>(LayananState.Init)
    val state get() = _state

    private val _statePlaylist = MutableStateFlow<PlaylistState>(PlaylistState.Init)
    val statePlaylist get() = _statePlaylist

    private val _data = MutableStateFlow<List<LayananEntity>>(mutableListOf())
    val data get() = _data

    private val _dataPlaylist = MutableStateFlow<List<PlaylistELearningEntity>>(mutableListOf())
    val dataPlaylist get() = _dataPlaylist


    private fun loading() {
        _state.value = LayananState.Loading()
    }


    private fun success(layananEntity: List<LayananEntity>) {
        _state.value = LayananState.Success(layananEntity)
        _data.value = layananEntity

    }

    private fun error(layananEntity: List<LayananEntity>) {
        _state.value = LayananState.Error(layananEntity)
    }

    fun getAllLayanan() {
        viewModelScope.launch {
            useCase.getLayanan()
                .onStart {
                    loading()

                }.catch {

                }.collect { result ->
                    when (result) {
                        is Result.Success -> success(result.data)
                        is Result.Error -> {}
                    }
                }
        }
    }

    fun getAllPlaylist() {
        viewModelScope.launch {
            useCase.getAllPlaylist()
                .onStart {
                    _statePlaylist.value = PlaylistState.Loading()
                }.catch {

                }
                .collect {
                    when (it) {
                        is Result.Success -> {
                            _statePlaylist.value = PlaylistState.Success(it.data)
                            _dataPlaylist.value = it.data
                        }
                        is Result.Error -> {}
                    }

                }
        }
    }
}

sealed class LayananState {
    object Init : LayananState()
    data class Loading(val loading: Boolean = true) : LayananState()
    data class Success(val layananEntity: List<LayananEntity>) : LayananState()
    data class Error(val response: List<LayananEntity>) : LayananState()
}

sealed class PlaylistState {
    object Init : PlaylistState()
    data class Loading(val loading: Boolean = true) : PlaylistState()
    data class Success(val layananEntity: List<PlaylistELearningEntity>) : PlaylistState()
    data class Error(val response: List<PlaylistELearningEntity>) : PlaylistState()
}