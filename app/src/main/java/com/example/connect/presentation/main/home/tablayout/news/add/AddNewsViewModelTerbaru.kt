package com.example.connect.presentation.main.home.tablayout.news.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.data.model.request.KirimanRequest
import com.example.connect.domain.entity.PostKirimanEntity
import com.example.connect.domain.entity.SementaraEntity
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
class AddNewsViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {
    private var _konten = MutableLiveData<String?>()
    val konten: LiveData<String?> get() = _konten

    private var _image = MutableLiveData<MultipartBody.Part?>()
    val image: LiveData<MultipartBody.Part?> get() = _image

    fun setKonten(data:String){
        _konten.value = data
    }

    fun setNullKonten(){
        _konten.value = null
    }

    fun saveImage(data: MultipartBody.Part?){

        _image.value = data

    }

    fun saveImageNull() {
        _image.value = null
    }

    fun setAllFieldNull(){
        setNullKonten()
    }

    fun setAllField(valueKonten:String){
        setKonten(valueKonten)
    }

    private val _state = MutableStateFlow<PostNewsState>(PostNewsState.Init)
    val state get() = _state

    private fun loading() {
        _state.value = PostNewsState.Loading()
    }

    private fun success(postNewsEntity: PostKirimanEntity){
        _state.value = PostNewsState.Success(postNewsEntity)
    }

    private fun error(postNewsEntity: SementaraEntity){
        _state.value = PostNewsState.Error(postNewsEntity)
    }

    fun createPart(value: String): RequestBody = value.toRequestBody()

    fun postKiriman(){
        val kirimanRequest = KirimanRequest(
            _image.value,
            createPart(_konten.value.toString())
        )
        viewModelScope.launch {
            useCase.posKiriman(kirimanRequest)
                .onStart { loading()

                }.catch {

                }
                .collect{ result ->
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
    data class Success(val postNewsEntity: PostKirimanEntity) : PostNewsState()
    data class Error(val response: SementaraEntity) : PostNewsState()
}