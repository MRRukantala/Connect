package com.example.connect.presentation.main.menu.info_pendidikan.pendidikan.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connect.data.model.request.PendidikanRequest
import com.example.connect.domain.entity.DeletePendidikanEntity
import com.example.connect.domain.entity.PostPendidikanEntity
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

    private val _instansi = MutableLiveData<String?>()
    val instansi: LiveData<String?> get() = _instansi

    fun setInstansi(data: String) {
        _instansi.value = data
    }

    fun setIntansiNull() {
        _instansi.value = null
    }

    private val _jenjang = MutableLiveData<String?>()
    val jenjang: LiveData<String?> get() = _jenjang

    fun setJenjang(data: String) {
        _jenjang.value = data
    }

    fun setJenjangNull() {
        _jenjang.value = null
    }

    private val _fakultas = MutableLiveData<String?>()
    val fakultas: LiveData<String?> get() = _fakultas

    fun setFakultas(data: String) {
        _fakultas.value = data
    }

    fun setFakultasNull() {
        _fakultas.value = null
    }

    private val _jurusan = MutableLiveData<String?>()
    val jurusan: LiveData<String?> get() = _jurusan

    fun setJurusan(data: String) {
        _jurusan.value = data
    }

    fun setJurusanNull() {
        _jurusan.value = null
    }

    private val _tahunMasuk = MutableLiveData<String?>()
    val tahunMasuk: LiveData<String?> get() = _tahunMasuk

    fun setTahunMasuk(data: String) {
        _tahunMasuk.value = data
    }

    fun setTahunMasukNull() {
        _tahunMasuk.value = null
    }

    private val _tahunKeluar = MutableLiveData<String?>()
    val tahunKeluar: LiveData<String?> get() = _tahunKeluar

    fun setTahuKeluar(data: String) {
        _tahunKeluar.value = data
    }

    fun setTahuKeluarNull() {
        _tahunKeluar.value = null
    }

    fun setAllField(
        valueInstansi: String,
        valueJenjang: String,
        valueFakultas: String,
        valueJurusan: String,
        valueTahunMasuk: String,
        valueTahunKeluar: String
    ) {
        setInstansi(valueInstansi)
        setJenjang(valueJenjang)
        setFakultas(valueFakultas)
        setJurusan(valueJurusan)
        setTahunMasuk(valueTahunMasuk)
        setTahuKeluar(valueTahunKeluar)

    }

    fun setAllFieldNull() {
        setIntansiNull()
        setJenjangNull()
        setFakultasNull()
        setTahunMasukNull()
        setTahuKeluarNull()
    }

    private val _statePost = MutableStateFlow<PostState>(PostState.Init)
    val statePost get() = _statePost

    private fun loadingPost() {
        _statePost.value = PostState.LoadingPost()
    }

    private fun successPost(postPendidikanEntity: PostPendidikanEntity) {
        _statePost.value = PostState.SuccessPost(postPendidikanEntity)
    }

    fun postPendidikan() {
        val pendidikanRequest = PendidikanRequest(
            _instansi.value.toString(), _jenjang.value.toString(), _fakultas.value.toString(),
            _jurusan.value.toString(), _tahunMasuk.value.toString(), _tahunKeluar.value.toString()
        )
        viewModelScope.launch {
            useCase.postPendidikan(pendidikanRequest)
                .onStart {
                    loadingPost()

                }.catch {

                }.collect { result ->
                    when (result) {
                        is Result.Success -> successPost(result.data)
                        is Result.Error -> {}
                    }
                }
        }
    }

    private val _statePut = MutableStateFlow<PutState>(PutState.Init)
    val statePut get() = _statePut

    private fun loadingPut() {
        _statePut.value = PutState.LoadingPut()
    }

    private fun successPut(postPendidikanEntity: PostPendidikanEntity) {
        _statePut.value = PutState.SuccessPut(postPendidikanEntity)
    }

    fun putPendidikan(id: Int) {
        val pendidikanRequest = PendidikanRequest(
            _instansi.value.toString(), _jenjang.value.toString(), _fakultas.value.toString(),
            _jurusan.value.toString(), _tahunMasuk.value.toString(), _tahunKeluar.value.toString()
        )
        val _method: HashMap<String, String> = HashMap()
        _method["_method"] = "PUT"
        viewModelScope.launch {
            useCase.putPendidikan(pendidikanRequest, id, _method)
                .onStart {
                    loadingPut()

                }.catch {

                }.collect { result ->
                    when (result) {
                        is Result.Success -> successPut(result.data)
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

sealed class PostState{
    object Init: PostState()
    data class LoadingPost(val loading: Boolean = true) : PostState()
    data class SuccessPost(val postPendidikanEntity: PostPendidikanEntity) : PostState()
    data class ErrorPost(val response: SementaraEntity) : PostState()
}

sealed class PutState{
    object Init: PutState()
    data class LoadingPut(val loading: Boolean = true) : PutState()
    data class SuccessPut(val postPendidikanEntity: PostPendidikanEntity) : PutState()
    data class ErrorPut(val response: SementaraEntity) : PutState()
}