package com.example.connect.presentation.main.ui.menu.info_pendidikan.info

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

class InfoUserViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private val _state = MutableStateFlow<InfoUserViewModelState>(InfoUserViewModelState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = InfoUserViewModelState.Loading()
    }

    private fun success(infoUserEntity: SementaraEntity){
        _state.value = InfoUserViewModelState.Success(infoUserEntity)
        _data.value = infoUserEntity
    }

    private fun error(infoUserEntity: SementaraEntity){
        _state.value = InfoUserViewModelState.Error(infoUserEntity)
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

sealed class InfoUserViewModelState {
    object Init : InfoUserViewModelState()

    data class Loading(val loading: Boolean = true) : InfoUserViewModelState()
    data class Success(val infoUserEntity: SementaraEntity) : InfoUserViewModelState()
    data class Error(val response: SementaraEntity) : InfoUserViewModelState()
}