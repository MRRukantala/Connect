package com.example.connect.presentation.main.ui.home.tablayout.news.mynews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.KirimanEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyNewsViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private val _state = MutableStateFlow<MyNewsState>(MyNewsState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<List<KirimanEntity>>(mutableListOf())
    val data get() = _data

    private fun loading() {
        _state.value = MyNewsState.Loading()
    }

    private fun success(myNewsEntity: List<KirimanEntity>){
        _state.value = MyNewsState.Success(myNewsEntity)
    }

    private fun error(myNewsEntity: List<KirimanEntity>){
        _state.value = MyNewsState.Error(myNewsEntity)
    }

    fun kirimanByUser(id:Int){
        viewModelScope.launch {
            useCase.getKirimanByIdUser(id)
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
    data class Success(val myNewsEntity: List<KirimanEntity>) : MyNewsState()
    data class Error(val response: List<KirimanEntity>) : MyNewsState()
}