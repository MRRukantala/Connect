package com.example.connect.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContohViewModel @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private val _state = MutableStateFlow<RegisterActivityState>(RegisterActivityState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = RegisterActivityState.Loading()
    }

    private fun success(registerEntity: SementaraEntity){
        _state.value = RegisterActivityState.Success(registerEntity)
        _data.value = registerEntity
    }

    private fun error(registerEntity: SementaraEntity){
        _state.value = RegisterActivityState.Error(registerEntity)
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

sealed class RegisterActivityState {
    object Init : RegisterActivityState()

    data class Loading(val loading: Boolean = true) : RegisterActivityState()
    data class Success(val registerEntity: SementaraEntity) : RegisterActivityState()
    data class Error(val response: SementaraEntity) : RegisterActivityState()
}