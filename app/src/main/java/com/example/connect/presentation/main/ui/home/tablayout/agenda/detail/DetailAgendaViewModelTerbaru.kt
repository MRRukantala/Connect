package com.example.connect.presentation.main.ui.home.tablayout.agenda.detail

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

class DetailAgendaViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private val _state = MutableStateFlow<DetailAgendaState>(DetailAgendaState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = DetailAgendaState.Loading()
    }

    private fun success(agendaEntity: SementaraEntity){
        _state.value = DetailAgendaState.Success(agendaEntity)
        _data.value = agendaEntity
    }

    private fun error(registerEntity: SementaraEntity){
        _state.value = DetailAgendaState.Error(registerEntity)
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

sealed class DetailAgendaState {
    object Init : DetailAgendaState()

    data class Loading(val loading: Boolean = true) : DetailAgendaState()
    data class Success(val detailAgendaEntity: SementaraEntity) : DetailAgendaState()
    data class Error(val response: SementaraEntity) : DetailAgendaState()
}