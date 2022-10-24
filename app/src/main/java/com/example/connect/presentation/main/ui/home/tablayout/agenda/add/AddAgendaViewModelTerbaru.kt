package com.example.connect.presentation.main.ui.home.tablayout.agenda.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.data.model.request.AgendaRequest
import com.example.connect.domain.entity.PostAgendaEntity
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.usecase.UseCase
import com.example.connect.utilites.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddAgendaViewModelTerbaru @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private var _title = MutableLiveData<String?>()
    val nama: LiveData<String?> get() = _title

    private var _lokasi = MutableLiveData<String?>()
    val lokasi: LiveData<String?> get() = _lokasi

    private var _tanggal = MutableLiveData<String?>()
    val tanggal: LiveData<String?> get() = _tanggal

    private var _waktu = MutableLiveData<String?>()
    val waktu: LiveData<String?> get() = _waktu

    private var _konten = MutableLiveData<String?>()
    val konten: LiveData<String?> get() = _konten

    private var _image = MutableLiveData<MultipartBody.Part?>()
    val image: LiveData<MultipartBody.Part?> get() = _image

    private var _status = MutableLiveData<String?>()
    val status: LiveData<String?> get() = _status

    private val _state = MutableStateFlow<AddAgendaDataState>(AddAgendaDataState.Init)
    val state get() = _state



    private fun loading() {
        _state.value = AddAgendaDataState.Loading()
    }

    private fun success(addAgendaEntity: PostAgendaEntity){
        _state.value = AddAgendaDataState.Success(addAgendaEntity)
    }

    private fun error(addAgendaEntity: PostAgendaEntity){
        _state.value = AddAgendaDataState.Error(addAgendaEntity)
    }

    fun createImagePart(file: File?, name: String): MultipartBody.Part {
        val body = file?.asRequestBody("image/*".toMediaTypeOrNull())
        Log.v("GAMBAR", file.toString())
        return if (file == null) MultipartBody.Part.createFormData(
            "photo",
            file?.name ?: ""
        ) else MultipartBody.Part.createFormData("photo", file.name, body!!)
    }

    fun createPart(value: String): RequestBody = value.toRequestBody()

    fun setTitle(data:String){
        _title.value = data
    }

    fun setKonten(data:String){
        _konten.value = data
    }

    fun setLokasi(data:String){
        _lokasi.value = data
    }
    fun setStatus(data:String){
        _status.value = data
    }

    private fun setNullTitle() {
        _title.value = null
    }

    private fun setNullLokasi() {
        _lokasi.value = null
    }

    private fun setNullKonten() {
        _konten.value = null
    }

    fun setWaktuKegiatan(data:String){
        _waktu.value = data
    }

    fun setTanggal(data:String){
        _tanggal.value = data
    }

    fun saveImage(file: MultipartBody.Part?){
        _image.value = file
    }

    fun imageNull() {
        _image.value = null
    }

    fun setAllFieldNull() {
        setNullTitle()
        setNullLokasi()
        setNullKonten()
    }

    fun setAllField(valueTitle: String, valueKonten:String, valueLokasi: String) {
        setTitle(valueTitle)
        setLokasi(valueLokasi)
        setKonten(valueKonten)
    }

    fun postAgenda(){
        val agendaRequest = AgendaRequest(

            createPart(_title.value.toString()),
            createPart(_lokasi.value.toString()),
            createPart(_tanggal.value.toString()),
            createPart(_waktu.value.toString()),
            createPart(_konten.value.toString()),
            _image.value,
            createPart(_status.value.toString()),

        )
        viewModelScope.launch {
            useCase.postAgenda(agendaRequest)
//                .onStart { loading()
//
//                }.catch {
//
//                }
                .collect{ result ->
                    when(result){
                        is Result.Success -> success(result.data)
                        is Result.Error -> { }
                    }
                }
        }
    }


}

sealed class AddAgendaDataState {
    object Init : AddAgendaDataState()

    data class Loading(val loading: Boolean = true) : AddAgendaDataState()
    data class Success(val addAgendaEntity: PostAgendaEntity) : AddAgendaDataState()
    data class Error(val response: PostAgendaEntity) : AddAgendaDataState()
}