package com.example.connect.main.ui.menu.info_pendidikan.info.edit

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.connect.main.ui.menu.info_pendidikan.MySubData
import com.example.connect.utilites.MarkOIApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

class EditInfoUserViewModel(
    val idUser: Int,
    token: String,
    application: Application,
    data: MySubData
) :
    AndroidViewModel(application) {

    val state = MutableLiveData<EditProfilState>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _selectedNews = MutableLiveData<MySubData>()
    val selectedNews: LiveData<MySubData>
        get() = _selectedNews

    private val _put = MutableLiveData<String>()
    val put: LiveData<String>
        get() = _put

    fun put(put: String) {
        _put.value = put
    }


    private val _image = MutableLiveData<MultipartBody.Part?>()
    val image: LiveData<MultipartBody.Part?>
        get() = _image

    fun image(part: MultipartBody.Part) {
        _image.postValue(part)
    }

    private val _wa = MutableLiveData<String?>()
    val wa: LiveData<String?>
        get() = _wa

    fun wa(wa: String) {
        _wa.value = wa
    }

    private val _domisili = MutableLiveData<String?>()
    val domisili: LiveData<String?>
        get() = _domisili

    fun domisili(domisili: String) {
        _domisili.value = domisili
    }

    private val _tanggal_lahir = MutableLiveData<String?>()
    val tanggal_lahir: LiveData<String?>
        get() = _tanggal_lahir

    fun tanggal_lahir(tanggal_lahir: String) {
        _tanggal_lahir.value = tanggal_lahir
    }

    private val _nim = MutableLiveData<String?>()
    val nim: LiveData<String?>
        get() = _nim

    fun nim(nim: String) {
        _nim.value = nim
    }

    private val _jenis_kelamin = MutableLiveData<String?>()
    val jenis_kelamin: LiveData<String?>
        get() = _jenis_kelamin

    fun jenis_kelamin(jenis_kelamin: String) {
        _jenis_kelamin.value = jenis_kelamin
    }

    fun updatedProfile(token: String) {
        val jenis_kelamin: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            _jenis_kelamin.value ?: ""
        )

        val nim: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            _nim.value ?: ""
        )

        val domisili: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            _domisili.value ?: ""
        )

        val wa: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            _wa.value ?: ""
        )

        val tanggal_lahir: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            _tanggal_lahir.value ?: ""
        )

        val _method: HashMap<String, String> = HashMap()
        _method["_method"] = "PUT"




        coroutineScope.launch {

            state.postValue(EditProfilState.LOADING)
            var update = MarkOIApi.retrofitService.updateMyProfile(
                "Bearer $token",
                jenis_kelamin,
                nim,
                tanggal_lahir,
                domisili,
                wa,
                _image.value,
                id_user = idUser,
                _method
            )

            try {
                update.await()
                state.postValue(EditProfilState.SUCCESS)
                Log.v("EDIT", "Berhasil")
            } catch (e: Exception) {
                state.postValue(EditProfilState.ERROR)
                Log.v("ERROR", e.message.toString())
            }
        }
    }

    init {
        _selectedNews.value = data
    }
}

enum class EditProfilState { SUCCESS, LOADING, ERROR }