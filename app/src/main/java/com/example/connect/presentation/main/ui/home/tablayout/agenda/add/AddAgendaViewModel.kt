package com.example.connect.presentation.main.ui.home.tablayout.agenda.add

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.connect.utilites.MarkOIApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AddAgendaViewModel : ViewModel() {

    val state = MutableLiveData<AddAgendaState>()

    private val _value = MutableLiveData<PostAgendaFormState?>()
    val value: LiveData<PostAgendaFormState?>
        get() = _value

    private val _nama_kegiatan = MutableLiveData<String?>()
    val nama_kegiatan: LiveData<String?>
        get() = _nama_kegiatan

    fun namaKegitan(data: String) {
        _nama_kegiatan.value = data
    }

    private val _deskripsi_kegiatan = MutableLiveData<String?>()
    val deskripsi_kegiatan: LiveData<String?>
        get() = _deskripsi_kegiatan

    fun deskripsiKegiatan(string: String) {
        _deskripsi_kegiatan.value = string
    }

    private val _lokasi_kegiatan = MutableLiveData<String?>()
    val lokasi_kegiatan: LiveData<String?>
        get() = _lokasi_kegiatan

    fun lokasiKegitan(data: String) {
        _lokasi_kegiatan.value = data
    }

    private val _tanggal_kegiatan = MutableLiveData<String?>()
    val tanggal_kegiatan: LiveData<String?>
        get() = _tanggal_kegiatan

    fun tanggalKegitan(data: String) {
        _tanggal_kegiatan.value = data
    }

    private val _waktu_kegiatan = MutableLiveData<String?>()
    val waktu_kegiatan: LiveData<String?>
        get() = _waktu_kegiatan

    fun waktuKegiatan(data: String) {
        _waktu_kegiatan.value = data
    }

    private val _image = MutableLiveData<MultipartBody.Part?>()
    val image: LiveData<MultipartBody.Part?>
        get() = _image

    fun image(part: MultipartBody.Part) {
        _image.postValue(part)
    }

    private val _idUser = MutableLiveData<String?>()
    val idUser: LiveData<String?>
        get() = _idUser

    private val _status = MutableLiveData<Int?>()
    val status: LiveData<Int?>
        get() = _status

    fun status(data: Int){
        _status.value = data
    }

    fun namaKegiatanNull() {
        _nama_kegiatan.value = null
    }

    fun deskripsiKegiatanNull() {
        _deskripsi_kegiatan.value = null
    }

    fun lokasiKegiatanNull() {
        _lokasi_kegiatan.value = null
    }

    fun tanggalKegiatanNull() {
        _tanggal_kegiatan.value = null
    }

    fun waktuKegiatanNull() {
        _waktu_kegiatan.value = null
    }

    fun imageNull() {
        _image.value = null
    }

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun posting(token: String) {
        Log.v("POST AGENDA", token + deskripsi_kegiatan)

        val kegiatan: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(), _nama_kegiatan.value ?: ""
        )

        val deskripsi: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(), _deskripsi_kegiatan.value ?: ""
        )

        val lokasi: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(), _lokasi_kegiatan.value ?: ""
        )


//        val tanggal: RequestBody = RequestBody.create(
//            "text/plain".toMediaTypeOrNull(), _tanggal_kegiatan.value ?: ""
//        )

        val tanggal: RequestBody = RequestBody.create(
            "datetime".toMediaTypeOrNull(), _tanggal_kegiatan.value ?: ""
        )


        val waktu: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(), _waktu_kegiatan.value ?: ""
        )

        Log.v(
            "DATA AGENDA", "$kegiatan $lokasi $tanggal $value" +
                    "$deskripsi ${_image.value} $status"
        )

        coroutineScope.launch {
            var post = MarkOIApi.retrofitService.postAgenda(
                "Bearer $token", kegiatan,
                lokasi, tanggal, waktu, deskripsi,
                _image.value, _status.value
            )

            state.postValue(AddAgendaState.LOADING)
            try {
                post.await()
                Log.v("POST", "BERHASIL")
                state.postValue(AddAgendaState.SUCCESS)
            } catch (e: Exception) {
                Log.v("GAGAL", e.message.toString())
//                if (!e.message.equals("Required value 'id_user' missing at \$")) {
//                    Log.v("GAGAL", e.message.toString())
//                    Log.v("POST", "GAGAL")
//                    state.postValue(AddAgendaState.ERROR)
//                } else {
//                    state.postValue(AddAgendaState.SUCCESS)
//                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun postKirimanDataChanged() {
        if (
            _nama_kegiatan.value != null && _lokasi_kegiatan.value != null
            && _tanggal_kegiatan.value != null && _waktu_kegiatan.value != null
            && _deskripsi_kegiatan.value != null && _image.value != null
        ) {
            _value.value = PostAgendaFormState(isDataValid = true)
        } else {
            _value.value = PostAgendaFormState(isDataValid = false)
        }
    }


    data class PostAgendaFormState(
        val isDataValid: Boolean = false
    )
}

enum class AddAgendaState { SUCCESS, LOADING, ERROR }