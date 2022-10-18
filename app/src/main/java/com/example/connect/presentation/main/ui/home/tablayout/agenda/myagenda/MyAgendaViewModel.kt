package com.example.connect.presentation.main.ui.home.tablayout.agenda.myagenda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.AgendaEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyAgendaViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AgendaByUserState>(AgendaByUserState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<List<AgendaEntity>>(mutableListOf())
    val data get() = _data

    private fun loading() {
        _state.value = AgendaByUserState.Loading()
    }

    private fun success(agendaEntity: List<AgendaEntity>) {
        _state.value = AgendaByUserState.Success(agendaEntity)
        _data.value = agendaEntity
    }

    private fun error(registerEntity: List<AgendaEntity>) {
        _state.value = AgendaByUserState.Error(registerEntity)
    }

    fun agendaByUser(id: Int) {
        viewModelScope.launch {
            useCase.getAgendaByIdUser(id)
                .onStart {
                    loading()

                }.catch {

                }.collect { result ->
                    when (result) {
                        is Result.Success -> success(result.data)
                        is Result.Error -> {}
                    }
                }
        }
    }

}

sealed class AgendaByUserState {
    object Init : AgendaByUserState()

    data class Loading(val loading: Boolean = true) : AgendaByUserState()
    data class Success(val agendaEntity: List<AgendaEntity>) : AgendaByUserState()
    data class Error(val agendaEntity: List<AgendaEntity>) : AgendaByUserState()
}