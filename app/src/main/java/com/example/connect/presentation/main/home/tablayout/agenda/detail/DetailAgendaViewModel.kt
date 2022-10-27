package com.example.connect.presentation.main.home.tablayout.agenda.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.AgendaEntity
import com.example.connect.domain.usecase.HomeUseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailAgendaViewModel @Inject constructor(
    private val useCase: HomeUseCase
):ViewModel() {

    private val _state = MutableStateFlow<DetailAgendaState>(DetailAgendaState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<AgendaEntity?>(null)
    val data get() = _data

    private fun loading() {
        _state.value = DetailAgendaState.Loading()
    }

    private fun success(agendaEntity: AgendaEntity){
        _state.value = DetailAgendaState.Success(agendaEntity)
        _data.value = agendaEntity
    }

    private fun error(registerEntity: AgendaEntity){
        _state.value = DetailAgendaState.Error(registerEntity)
    }

    fun detailAgenda(id:Int){
        viewModelScope.launch {
            useCase.getDetailAgenda(id)
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
    data class Success(val detailAgendaEntity: AgendaEntity) : DetailAgendaState()
    data class Error(val response: AgendaEntity) : DetailAgendaState()
}