package com.example.connect.presentation.main.ui.menu.info_pendidikan.info.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditInfoUserViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private val _state = MutableStateFlow<EditInfoUserState>(EditInfoUserState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = EditInfoUserState.Loading()
    }

    private fun success(editInfoUserEntity: SementaraEntity){
        _state.value = EditInfoUserState.Success(editInfoUserEntity)
        _data.value = editInfoUserEntity
    }

    private fun error(editInfoUserEntity: SementaraEntity){
        _state.value = EditInfoUserState.Error(editInfoUserEntity)
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

sealed class EditInfoUserState {
    object Init : EditInfoUserState()

    data class Loading(val loading: Boolean = true) : EditInfoUserState()
    data class Success(val editInfoUserEntity: SementaraEntity) : EditInfoUserState()
    data class Error(val response: SementaraEntity) : EditInfoUserState()
}