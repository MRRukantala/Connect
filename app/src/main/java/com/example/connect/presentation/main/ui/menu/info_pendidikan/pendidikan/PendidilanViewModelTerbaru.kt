package com.example.connect.presentation.main.ui.menu.info_pendidikan.pendidikan

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

class PendidilanViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private val _state = MutableStateFlow<PendidilanState>(PendidilanState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = PendidilanState.Loading()
    }

    private fun success(pendidikanEntity: SementaraEntity){
        _state.value = PendidilanState.Success(pendidikanEntity)
        _data.value = pendidikanEntity
    }

    private fun error(pendidikanEntity: SementaraEntity){
        _state.value = PendidilanState.Error(pendidikanEntity)
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

sealed class PendidilanState {
    object Init : PendidilanState()

    data class Loading(val loading: Boolean = true) : PendidilanState()
    data class Success(val pendidikanEntity: SementaraEntity) : PendidilanState()
    data class Error(val response: SementaraEntity) : PendidilanState()
}