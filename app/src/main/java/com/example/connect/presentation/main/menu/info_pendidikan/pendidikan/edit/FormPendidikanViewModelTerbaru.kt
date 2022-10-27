package com.example.connect.presentation.main.menu.info_pendidikan.pendidikan.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.domain.entity.DeletePendidikanEntity
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.ProfileUseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormPendidikanViewModelTerbaru @Inject constructor(
    private val useCase: ProfileUseCase
) : ViewModel() {
    private val _stateDelete = MutableStateFlow<DeleteState>(DeleteState.Init)
    val stateDelete get() = _stateDelete

    private val _dataDelete = MutableStateFlow<DeletePendidikanEntity?>(null)
    val dataDelete get() = _dataDelete

    private fun loading() {
        _stateDelete.value = DeleteState.Loading()
    }

    private fun success(deletePendidikanEntity: DeletePendidikanEntity) {
        _stateDelete.value = DeleteState.Success(deletePendidikanEntity)
        _dataDelete.value = deletePendidikanEntity
    }


    fun delete(id: Int) {
        viewModelScope.launch {
            useCase.deletePendidikan(id)
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

sealed class DeleteState {
    object Init : DeleteState()

    data class Loading(val loading: Boolean = true) : DeleteState()
    data class Success(val deletePendidikanEntity: DeletePendidikanEntity) : DeleteState()
    data class Error(val response: SementaraEntity) : DeleteState()
}