package com.example.connect.presentation.main.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.presentation.main.ui.product.tabLayout.productumum.ProductUmumState
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private val _state = MutableStateFlow<DashboardState>(DashboardState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<Any>("")
    val data get() = _data

    private fun loading() {
        _state.value = DashboardState.Loading()
    }

    private fun success(dashboardEntity: SementaraEntity){
        _state.value = DashboardState.Success(dashboardEntity)
        _data.value = dashboardEntity
    }

    private fun error(dashboardEntity: SementaraEntity){
        _state.value = DashboardState.Error(dashboardEntity)
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

sealed class DashboardState {
    object Init : DashboardState()

    data class Loading(val loading: Boolean = true) : DashboardState()
    data class Success(val dashboardEntity: SementaraEntity) : DashboardState()
    data class Error(val response: SementaraEntity) : DashboardState()
}