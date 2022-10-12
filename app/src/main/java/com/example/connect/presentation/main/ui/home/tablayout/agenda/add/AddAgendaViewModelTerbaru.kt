package com.example.connect.presentation.main.ui.home.tablayout.agenda.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddAgendaViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private val _state = MutableStateFlow<AddAgendaDataState>(AddAgendaDataState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = AddAgendaDataState.Loading()
    }

    private fun success(addAgendaEntity: SementaraEntity){
        _state.value = AddAgendaDataState.Success(addAgendaEntity)
        _data.value = addAgendaEntity
    }

    private fun error(addAgendaEntity: SementaraEntity){
        _state.value = AddAgendaDataState.Error(addAgendaEntity)
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

sealed class AddAgendaDataState {
    object Init : AddAgendaDataState()

    data class Loading(val loading: Boolean = true) : AddAgendaDataState()
    data class Success(val addAgendaEntity: SementaraEntity) : AddAgendaDataState()
    data class Error(val response: SementaraEntity) : AddAgendaDataState()
}