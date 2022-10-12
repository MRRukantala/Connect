package com.example.connect.presentation.main.ui.home.tablayout.news.mynews

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

class MyNewsViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private val _state = MutableStateFlow<MyNewsState>(MyNewsState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = MyNewsState.Loading()
    }

    private fun success(myNewsEntity: SementaraEntity){
        _state.value = MyNewsState.Success(myNewsEntity)
        _data.value = myNewsEntity
    }

    private fun error(myNewsEntity: SementaraEntity){
        _state.value = MyNewsState.Error(myNewsEntity)
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

sealed class MyNewsState {
    object Init : MyNewsState()

    data class Loading(val loading: Boolean = true) : MyNewsState()
    data class Success(val myNewsEntity: SementaraEntity) : MyNewsState()
    data class Error(val response: SementaraEntity) : MyNewsState()
}