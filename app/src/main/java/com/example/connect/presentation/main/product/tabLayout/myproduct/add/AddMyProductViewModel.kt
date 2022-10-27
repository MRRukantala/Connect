package com.example.connect.presentation.main.product.tabLayout.myproduct.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.data.model.request.ProductRequest
import com.example.connect.domain.entity.PostProductEntity
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.ProfileUseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class AddMyProductViewModel @Inject constructor(
    private val useCase: ProfileUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AddMyProductState>(AddMyProductState.Init)
    val state get() = _state

    private var _nama = MutableLiveData<String?>()
    val nama: LiveData<String?> get() = _nama

    fun setNama(data: String) {
        _nama.value = data
    }

    fun setNamaNull() {
        _nama.value = null
    }

    private var _harga = MutableLiveData<String?>()
    val harga: LiveData<String?> get() = _harga

    fun setharga(data: String) {
        _harga.value = data
    }

    fun setHargaNull() {
        _harga.value = null
    }

    private var _deskripsi = MutableLiveData<String?>()
    val deskripsi: LiveData<String?> get() = _deskripsi

    fun setDeskripsi(data: String) {
        _deskripsi.value = data
    }

    fun setDeskripsiNull() {
        _deskripsi.value = null
    }

    private var _imageProduct = MutableLiveData<MultipartBody.Part?>()
    val imageProduct: LiveData<MultipartBody.Part?> get() = _imageProduct

    fun saveImageProduct(data: MultipartBody.Part) {
        _imageProduct.value = data
    }

    fun setImageNull() {
        _imageProduct.value = null
    }

    fun setAllFieldNull() {
        setDeskripsiNull()
        setHargaNull()
        setNamaNull()
    }

    fun setAllField(
        valueHarga: String, valueDeskripsi: String,
        valueNama: String
    ) {
        setharga(valueHarga)
        setNama(valueNama)
        setDeskripsi(valueDeskripsi)
    }


    private fun loading() {
        _state.value = AddMyProductState.Loading()
    }

    private fun success(postProductEntity: List<PostProductEntity>) {
        _state.value = AddMyProductState.Success(postProductEntity)
    }

    private fun error(detailProductEntity: SementaraEntity) {
        _state.value = AddMyProductState.Error(detailProductEntity)
    }

    fun createPart(value: String): RequestBody = value.toRequestBody()


    fun postProduct() {
        val productRequest = ProductRequest(
            _imageProduct.value,
            _harga.value?.toInt(),
            createPart(_nama.value.toString()),
            createPart(_deskripsi.value.toString())

        )
        viewModelScope.launch {
            useCase.posProduct(productRequest)
                .onStart { loading()

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

sealed class AddMyProductState {
    object Init : AddMyProductState()

    data class Loading(val loading: Boolean = true) : AddMyProductState()
    data class Success(val addMyProductEntity: List<PostProductEntity>) : AddMyProductState()
    data class Error(val response: SementaraEntity) : AddMyProductState()
}

