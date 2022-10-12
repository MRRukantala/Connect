package com.example.connect.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = LoginState.Loading()
    }

    private fun success(loginEntity: SementaraEntity){
        _state.value = LoginState.Success(loginEntity)
        _data.value = loginEntity
    }

    private fun error(registerEntity: SementaraEntity){
        _state.value = LoginState.Error(registerEntity)
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

sealed class LoginState {
    object Init : LoginState()

    data class Loading(val loading: Boolean = true) : LoginState()
    data class Success(val loginEntity: SementaraEntity) : LoginState()
    data class Error(val response: SementaraEntity) : LoginState()
}