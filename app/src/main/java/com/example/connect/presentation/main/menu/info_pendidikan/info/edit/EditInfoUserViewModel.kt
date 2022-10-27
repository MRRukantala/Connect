package com.example.connect.presentation.main.menu.info_pendidikan.info.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.data.model.request.ProfileRequest
import com.example.connect.domain.entity.EditProfleEntity
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.UseCase
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
class EditInfoUserViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private var _jenisKelamin = MutableLiveData<String?>()
    val jenisKelamin: LiveData<String?> get() = _jenisKelamin

    fun setJenisKelamin(data: String) {
        _jenisKelamin.value = data
    }

    fun setJenisKelaminNull() {
        _jenisKelamin.value = null
    }

    private var _nim = MutableLiveData<String?>()
    val nim: LiveData<String?> get() = _nim

    fun setNim(data: String) {
        _nim.value = data
    }

    fun setNimNull() {
        _nim.value = null
    }

    private var _tglLahir = MutableLiveData<String?>()
    val tglLahir: LiveData<String?> get() = _tglLahir

    fun setTglLahir(data: String) {
        _tglLahir.value = data
    }

    fun setTglLahirNull() {
        _tglLahir.value = null
    }

    private var _domisili = MutableLiveData<String?>()
    val domisili: LiveData<String?> get() = _domisili

    fun setDomisili(data: String) {
        _domisili.value = data
    }

    fun setDomisiliNull() {
        _domisili.value = null
    }

    private var _wa = MutableLiveData<String?>()
    val wa: LiveData<String?> get() = _wa

    fun setWa(data: String) {
        _wa.value = data
    }

    fun setWaNull() {
        _wa.value = null
    }

    private var _photo = MutableLiveData<MultipartBody.Part?>()
    val photo: LiveData<MultipartBody.Part?> get() = _photo

    fun savePhoto(data: MultipartBody.Part) {
        _photo.value = data
    }

    fun savePhotoNull() {
        _photo.value = null
    }

    fun createPart(value: String): RequestBody = value.toRequestBody()

    private val _state = MutableStateFlow<EditInfoUserState>(EditInfoUserState.Init)
    val state get() = _state


    private fun loading() {
        _state.value = EditInfoUserState.Loading()
    }

    private fun success(editInfoUserEntity: EditProfleEntity) {
        _state.value = EditInfoUserState.Success(editInfoUserEntity)
    }

    private fun error(editInfoUserEntity: SementaraEntity) {
        _state.value = EditInfoUserState.Error(editInfoUserEntity)
    }

    fun setAllFieldNull() {
        setNimNull()
        setWaNull()
        setDomisiliNull()
    }

    fun setAllField(valueNim: String,
                    valueWa:String,
                    valueDomisili: String) {
        setNim(valueNim)
        setWa(valueWa)
        setDomisili(valueDomisili)
    }

    fun editProfile(id: Int) {
        val profileRequest = ProfileRequest(
            createPart(_jenisKelamin.value.toString()),
            createPart(_nim.value.toString()),
            createPart(_tglLahir.value.toString()),
            createPart(_domisili.value.toString()),
            createPart(_wa.value.toString()),
            _photo.value

        )

        val _method: HashMap<String, String> = HashMap()
        _method["_method"] = "PUT"
        viewModelScope.launch {
            useCase.editProfile(
                profileRequest,
                id,
                _method
            )
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

sealed class EditInfoUserState {
    object Init : EditInfoUserState()

    data class Loading(val loading: Boolean = true) : EditInfoUserState()
    data class Success(val editInfoUserEntity: EditProfleEntity) : EditInfoUserState()
    data class Error(val response: SementaraEntity) : EditInfoUserState()
}