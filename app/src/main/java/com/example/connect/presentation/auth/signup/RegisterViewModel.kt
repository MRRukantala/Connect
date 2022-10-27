package com.example.connect.presentation.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.data.model.request.RegisterRequest
import com.example.connect.domain.entity.LoginEntity
import com.example.connect.domain.entity.RegisterEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private var _email = MutableLiveData<String?>()
    val email: LiveData<String?> get() = _email

    fun setEmail(data:String){
        _email.value = data
    }

    fun setEmailNull(){
        _email.value = null
    }

    private var _nama = MutableLiveData<String?>()
    val nama: LiveData<String?> get() = _nama

    fun setNama(data:String){
        _nama.value = data
    }

    fun setNamaNull(){
        _nama.value = null
    }

    private var _password = MutableLiveData<String?>()
    val password: LiveData<String?> get() = _password

    fun setPassword(data:String){
        _password.value = data
    }

    fun setPasswordNull(){
        _password.value = null
    }

    private var _level = MutableLiveData<Int?>()
    val level: LiveData<Int?> get() = _level

    fun setLevel(data:Int){
        _level.value = data
    }

    fun setAllFieldNull() {
        setEmailNull()
        setPasswordNull()
        setNamaNull()
    }

    fun setAllField(valueNama:String, valueEmail: String, valuePassword: String) {
        setNama(valueNama)
        setEmail(valueEmail)
        setPassword(valuePassword)
    }

    private val _state =  MutableStateFlow<RegisterState>(RegisterState.Init)
    val state: Flow<RegisterState> get() = _state

    private fun loading() {
        _state.value = RegisterState.Loading()
    }

    private fun success(registerEntity: RegisterEntity){
        _state.value = RegisterState.Success(registerEntity)
    }

    private fun error(loginEntity: LoginEntity){
        _state.value = RegisterState.Error(loginEntity)
    }

    fun register(){
        val registerRequest = RegisterRequest(
            _nama.value.toString(),
            _email.value.toString(), _password.value.toString(),
            _level.value?.toInt() ?: 0
        )
        viewModelScope.launch {
            useCase.register(registerRequest)
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

sealed class RegisterState {
    object Init : RegisterState()
    data class Loading(val loading: Boolean = true) : RegisterState()
    data class Success(val registerEntity: RegisterEntity) : RegisterState()
    data class Error(val response: LoginEntity) : RegisterState()
}