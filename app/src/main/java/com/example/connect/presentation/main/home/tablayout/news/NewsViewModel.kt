package com.example.connect.presentation.main.home.tablayout.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.KirimanEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _state = MutableStateFlow<NewsState>(NewsState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<List<KirimanEntity>>(mutableListOf())
    val data: StateFlow<List<KirimanEntity>> get() = _data

    private fun loading() {
        _state.value = NewsState.Loading()
    }

    private fun success(kirimanEntity: List<KirimanEntity>) {
        _data.value = kirimanEntity
        _state.value = NewsState.Success(kirimanEntity)
    }

    fun berita() {
        viewModelScope.launch {
            useCase.getAllKiriman()
                .onStart {
                    loading()

                }.catch {

                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> success(result.data)
                        is Result.Error -> {}
                    }
                }
        }
    }


}

sealed class NewsState {
    object Init : NewsState()

    data class Loading(val loading: Boolean = true) : NewsState()
    data class Success(val kirimanEntity: List<KirimanEntity>) : NewsState()
    data class Error(val response: List<KirimanEntity>) : NewsState()
}