package com.example.connect.presentation.main.ui.layanan.detail_artikel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.LayananEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailArtikelMarOlViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {
    private val _state = MutableStateFlow<DetailArtikelMarOlState>(DetailArtikelMarOlState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<LayananEntity?>(null)
    val data get() = _data

    private fun loading() {
        _state.value = DetailArtikelMarOlState.Loading()
    }

    private fun success(detailArtikelEntity: LayananEntity) {
        _state.value = DetailArtikelMarOlState.Success(detailArtikelEntity)
        _data.value = detailArtikelEntity
    }

    private fun error(detailArtikelEntity: LayananEntity) {
        _state.value = DetailArtikelMarOlState.Error(detailArtikelEntity)
    }

    fun detailLayanan(id: Int) {
        viewModelScope.launch {
            useCase.getDetailLayanan(id)
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
}

sealed class DetailArtikelMarOlState {
    object Init : DetailArtikelMarOlState()

    data class Loading(val loading: Boolean = true) : DetailArtikelMarOlState()
    data class Success(val detailArtikelEntity: LayananEntity) : DetailArtikelMarOlState()
    data class Error(val response: LayananEntity) : DetailArtikelMarOlState()
}