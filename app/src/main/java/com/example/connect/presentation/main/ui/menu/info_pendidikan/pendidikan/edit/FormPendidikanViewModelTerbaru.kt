package com.example.connect.presentation.main.ui.menu.info_pendidikan.pendidikan.edit

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

class FormPendidikanViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {
    private val _state = MutableStateFlow<FormPendidikanState>(FormPendidikanState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = FormPendidikanState.Loading()
    }

    private fun success(formPendidikanEntity: SementaraEntity){
        _state.value = FormPendidikanState.Success(formPendidikanEntity)
        _data.value = formPendidikanEntity
    }

    private fun error(formPendidikanEntity: SementaraEntity){
        _state.value = FormPendidikanState.Error(formPendidikanEntity)
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

sealed class FormPendidikanState {
    object Init : FormPendidikanState()

    data class Loading(val loading: Boolean = true) : FormPendidikanState()
    data class Success(val formPendidikanEntity: SementaraEntity) : FormPendidikanState()
    data class Error(val response: SementaraEntity) : FormPendidikanState()
}