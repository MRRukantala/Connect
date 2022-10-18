package com.example.connect.presentation.main.ui.home.tablayout.news.add

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

class AddNewsViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {
    private val _state = MutableStateFlow<PostNewsState>(PostNewsState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = PostNewsState.Loading()
    }

    private fun success(postNewsEntity: SementaraEntity){
        _state.value = PostNewsState.Success(postNewsEntity)
        _data.value = postNewsEntity
    }

    private fun error(postNewsEntity: SementaraEntity){
        _state.value = PostNewsState.Error(postNewsEntity)
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

sealed class PostNewsState {
    object Init : PostNewsState()

    data class Loading(val loading: Boolean = true) : PostNewsState()
    data class Success(val postNewsEntity: SementaraEntity) : PostNewsState()
    data class Error(val response: SementaraEntity) : PostNewsState()
}