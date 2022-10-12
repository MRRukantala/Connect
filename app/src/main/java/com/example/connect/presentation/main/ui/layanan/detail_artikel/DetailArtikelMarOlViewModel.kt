package com.example.connect.presentation.main.ui.layanan.detail_artikel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.presentation.RegisterActivityState
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailArtikelMarOlViewModel @Inject constructor(
    private val useCase: UseCase
):ViewModel(){
    private val _state = MutableStateFlow<DetailArtikelMarOlState>(DetailArtikelMarOlState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = DetailArtikelMarOlState.Loading()
    }

    private fun success(detailArtikelEntity: SementaraEntity){
        _state.value = DetailArtikelMarOlState.Success(detailArtikelEntity)
        _data.value = detailArtikelEntity
    }

    private fun error(detailArtikelEntity: SementaraEntity){
        _state.value = DetailArtikelMarOlState.Error(detailArtikelEntity)
    }

    fun register(){
        viewModelScope.launch {
            useCase.register()
                .onStart { loading()

                }.catch {

                }.collect{ result ->
                    when(result){
                        is Result.Success -> success(result.data)
                        is Result.Error -> { }
                    }
                }
        }
    }
}

sealed class DetailArtikelMarOlState {
    object Init : DetailArtikelMarOlState()

    data class Loading(val loading: Boolean = true) : DetailArtikelMarOlState()
    data class Success(val detailArtikelEntity: SementaraEntity) : DetailArtikelMarOlState()
    data class Error(val response: SementaraEntity) : DetailArtikelMarOlState()
}