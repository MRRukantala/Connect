package com.example.connect.presentation.main.ui.menu.info_pendidikan.pendidikan.edit

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.connect.R
import com.example.connect.presentation.main.ui.menu.info_pendidikan.message
import com.example.connect.utilites.MarkOIApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FormPendidikanViewModel : ViewModel() {

    val _state = MutableLiveData<state>()

    private val _pendidikanForm = MutableLiveData<PendidikanState>()
    val pendidikanForm: LiveData<PendidikanState>
        get() = _pendidikanForm

    private val _result = MutableLiveData<String>()
    val result: LiveData<String>
        get() = _result

    private val _deleted = MutableLiveData<message>()
    val deleted : LiveData<message>
    get() = _deleted

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun delete(
        token: String,
        id: Int
    ) {
        coroutineScope.launch {
            _state.postValue(state.LOADING)
            val delete =  MarkOIApi.retrofitService.deletePendidikan("Bearer " + token, id)
            try {
                _state.postValue(state.SUCCESS)
                val resul = delete.await()
                Log.v("HASIL", resul.message)
                _deleted.value = resul
            } catch (e: Exception){
                e.message.toString()
                _state.postValue(state.ERROR)
                Log.v("HASIL", e.message.toString())
            }
        }
    }


    fun input(
        token: String, instansi: String, jenjang: String, fakultas: String, jurusan: String,
        tahunMasuk: String, tahunKeluar: String
    ) {
        _state.postValue(state.LOADING)
        Log.v("DATA", token + instansi + jenjang + fakultas + jurusan + tahunMasuk + tahunKeluar)
        coroutineScope.launch {

            val input = MarkOIApi.retrofitService.postPendidikan(
                "Bearer " +token,
                instansi,
                jenjang,
                fakultas,
                jurusan,
                tahunMasuk,
                tahunKeluar
            )


            try {
                _state.postValue(state.SUCCESS)
                val result = input.await()
                Log.v("HASIL", result.toString())
            } catch (e: Exception){
                _state.postValue(state.ERROR)
                Log.v("ERROR PESAN", e.message.toString())
                e.toString()
            }
        }
    }

    fun inputPendidikanDataChanged(
        instansi: String, jenjang: String, fakultas: String, jurusan: String,
        tahunMasuk: String, tahunKeluar: String
    ) {
        if(!isEmpty(instansi)) {
            _pendidikanForm.value = PendidikanState(instansi = R.string.invalid_username)
        } else if(!isEmpty(jenjang)){
            _pendidikanForm.value = PendidikanState(jenjang = R.string.invalid_username)
        } else if(!isEmpty(fakultas)){
            _pendidikanForm.value = PendidikanState(fakultas = R.string.invalid_username)
        } else if(!isEmpty(jurusan)){
            _pendidikanForm.value = PendidikanState(jurusan = R.string.invalid_username)
        } else if(!isEmpty(tahunMasuk)){
            _pendidikanForm.value = PendidikanState(tahunMasuk = R.string.invalid_username)
        } else if(!isEmpty(tahunKeluar)){
            _pendidikanForm.value = PendidikanState(tahunKeluar = R.string.invalid_username)
        } else {
            _pendidikanForm.value = PendidikanState(isDataValid = true)
        }
    }

    private  fun isEmpty(string: String) : Boolean {
        return if (string.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(string).matches()
        } else {
            string.isNotBlank()
        }
    }
}

data class PendidikanState(
    val instansi: Int? = null,
    val jenjang: Int? = null,
    val fakultas : Int? = null,
    val jurusan: Int? = null,
    val tahunMasuk: Int? = null,
    val tahunKeluar: Int? = null,
    val isDataValid : Boolean = false
)

enum class state{
    SUCCESS,
    LOADING,
    ERROR
}