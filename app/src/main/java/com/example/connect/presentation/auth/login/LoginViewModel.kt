package com.example.connect.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.data.model.request.LoginRequest
import com.example.connect.domain.entity.LoginEntity
import com.example.connect.domain.usecase.AuthUseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: AuthUseCase
):ViewModel() {

    private var _email = MutableLiveData("")
    val email: LiveData<String?> get() = _email

    private var _password = MutableLiveData("")
    val password: LiveData<String?> get() = _password

    private fun setEmail(value: String) {
        _email.value = value
    }

    private fun setNullEmail() {
        _email.value = null
    }

    private fun setPassword(value: String?) {
        _password.value = value
    }

    private fun setNullPassword() {
        _password.value = null
    }

    fun setAllFieldNull() {
        setNullEmail()
        setNullPassword()
    }

    fun setAllField(valueEmail: String, valuePassword: String) {
        setEmail(valueEmail)
        setPassword(valuePassword)
    }

    private val _state =  MutableStateFlow<LoginState>(LoginState.Init)
    val state: Flow<LoginState> get() = _state

    private fun loading() {
        _state.value = LoginState.Loading()
    }

    private fun success(loginEntity: LoginEntity){
        _state.value = LoginState.Success(loginEntity)
    }

    private fun error(loginEntity: LoginEntity){
        _state.value = LoginState.Error(loginEntity)
    }

    fun login(){
        val loginRequest = LoginRequest(
            _email.value.toString(), _password.value.toString()
        )
        viewModelScope.launch {
            useCase.login(loginRequest)
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
    data class Success(val loginEntity: LoginEntity) : LoginState()
    data class Error(val response: LoginEntity) : LoginState()
}