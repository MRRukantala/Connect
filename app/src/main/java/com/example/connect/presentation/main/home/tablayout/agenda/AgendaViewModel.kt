package com.example.connect.presentation.main.home.tablayout.agenda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.AgendaEntity
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AgendaViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AgendaState>(AgendaState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<List<AgendaEntity>>(mutableListOf())
    val data: StateFlow<List<AgendaEntity>> get() = _data

    private fun loading() {
        _state.value = AgendaState.Loading()
    }

    private fun success(agendaEntity: List<AgendaEntity>) {
        _state.value = AgendaState.Success(agendaEntity)
        _data.value = agendaEntity
    }

    fun agenda() {
        viewModelScope.launch {
            useCase.getAllAgenda()
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

sealed class AgendaState {
    object Init : AgendaState()

    data class Loading(val loading: Boolean = true) : AgendaState()
    data class Success(val agendaEntity: List<AgendaEntity>) : AgendaState()
    data class Error(val response: List<AgendaEntity>) : AgendaState()
}