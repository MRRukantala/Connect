package com.example.connect.presentation.main.home.tablayout.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.KirimanEntity
import com.example.connect.domain.usecase.HomeUseCase
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
    private val useCase: HomeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<NewsState>(NewsState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<ArrayList<KirimanEntity>>(arrayListOf())
    val data: StateFlow<ArrayList<KirimanEntity>> get() = _data

    private fun loading() {
        _state.value = NewsState.Loading()
    }

    private fun success(kirimanEntity: ArrayList<KirimanEntity>) {
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
    data class Success(val kirimanEntity:ArrayList<KirimanEntity>) : NewsState()
    data class Error(val response:ArrayList<KirimanEntity>) : NewsState()
}