package com.example.connect.presentation.main.ui.home.tablayout.news.add

import android.util.Log
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


class AddNewsViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _value = MutableLiveData<PostKirimanFormState?>()
    val value: LiveData<PostKirimanFormState?>
        get() = _value

    val state = MutableLiveData<AddNewsState>()

    private val _image = MutableLiveData<MultipartBody.Part?>()
    val image: LiveData<MultipartBody.Part?>
        get() = _image

    fun image(part: MultipartBody.Part) {
        _image.postValue(part)
    }

    private val _idUser = MutableLiveData<String>()
    val idUser : LiveData<String?>
    get() = _idUser

    fun idUser(id: String){
        _idUser.value = id
    }

    private val _content = MutableLiveData<String?>()
    val content: LiveData<String?>
        get() = _content

    fun content(string: String) {
        _content.value = string
    }

    fun imageNull() {
        _image.value = null
    }

    fun contentNull() {
        _content.value = null
    }

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun posting(token: String) {
        Log.v("POST DATA", token  + content)
        val content: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            _content.value ?: ""
        )
        val idUser: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            _idUser.value ?: ""
        )
        coroutineScope.launch {
            var post = MarkOIApi.retrofitService.postKiriman(
                "Bearer $token", idUser,_image.value, content
            )
            state.postValue(AddNewsState.LOADING)
            try {
                post.await()
                Log.v("POST", "BERHASIL")
                state.postValue(AddNewsState.SUCCESS)
            } catch (e: Exception) {
                if (!e.message.equals("Required value 'id_user' missing at \$")) {
                    Log.v("GAGAL", e.message.toString())
                    Log.v("POST", "GAGAL")
                    state.postValue(AddNewsState.ERROR)
                } else {
                    state.postValue(AddNewsState.SUCCESS)
                }
            }
        }
    }

    fun postKirimanDataChanged() {
        if (_image.value != null && _content.value != null) {
            _value.value = PostKirimanFormState(isDataValid = true)
        } else {
            _value.value = PostKirimanFormState(isDataValid = false)
        }
    }

    data class PostKirimanFormState(
        val isDataValid: Boolean = false
    )
}

enum class AddNewsState{ SUCCESS, LOADING, ERROR }

