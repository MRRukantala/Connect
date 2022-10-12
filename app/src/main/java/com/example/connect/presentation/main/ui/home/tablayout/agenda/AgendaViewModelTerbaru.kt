package com.example.connect.presentation.main.ui.home.tablayout.agenda

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

class AgendaViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private val _state = MutableStateFlow<AgendaState>(AgendaState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = AgendaState.Loading()
    }

    private fun success(agendaEntity: SementaraEntity){
        _state.value = AgendaState.Success(agendaEntity)
        _data.value = agendaEntity
    }

    private fun error(agendaEntity: SementaraEntity){
        _state.value = AgendaState.Error(agendaEntity)
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

sealed class AgendaState {
    object Init : AgendaState()

    data class Loading(val loading: Boolean = true) : AgendaState()
    data class Success(val agendaEntity: SementaraEntity) : AgendaState()
    data class Error(val response: SementaraEntity) : AgendaState()
}