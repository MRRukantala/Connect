package com.example.connect.presentation.main.ui.menu.info_pendidikan.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.ProfileEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoUserViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private val _state = MutableStateFlow<InfoUserViewModelState>(InfoUserViewModelState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<ProfileEntity?>(null)
    val data get() = _data

    private fun loading() {
        _state.value = InfoUserViewModelState.Loading()
    }

    private fun success(infoUserEntity: ProfileEntity){
        _state.value = InfoUserViewModelState.Success(infoUserEntity)
    }

    private fun error(infoUserEntity: ProfileEntity){
        _state.value = InfoUserViewModelState.Error(infoUserEntity)
    }

    fun getProfile(id:Int){
        viewModelScope.launch {
            useCase.getProfile(id)
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
    data class Success(val infoUserEntity: ProfileEntity) : InfoUserViewModelState()
    data class Error(val response: ProfileEntity) : InfoUserViewModelState()
}