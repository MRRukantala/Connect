package com.example.connect.presentation.main.home.tablayout.news.mynews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.KirimanEntity
import com.example.connect.domain.usecase.HomeUseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyNewsViewModel @Inject constructor(
    private val useCase: HomeUseCase
):ViewModel() {

    private val _state = MutableStateFlow<MyNewsState>(MyNewsState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<ArrayList<KirimanEntity>>(arrayListOf())
    val data get() = _data

    private fun loading() {
        _state.value = MyNewsState.Loading()
    }

    private fun success(myNewsEntity: ArrayList<KirimanEntity>){
        _state.value = MyNewsState.Success(myNewsEntity)
        _data.value = myNewsEntity
    }

    private fun error(myNewsEntity: ArrayList<KirimanEntity>){
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
    data class Success(val myNewsEntity: ArrayList<KirimanEntity>) : MyNewsState()
    data class Error(val response: ArrayList<KirimanEntity>) : MyNewsState()
}