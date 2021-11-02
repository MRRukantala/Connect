package com.example.connect.main.ui.home.tablayout.news.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.connect.utilites.MarkOIApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class AddNewsViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _value = MutableLiveData<PostKirimanFormState?>()
    val value: LiveData<PostKirimanFormState?>
        get() = _value

    private val _image = MutableLiveData<RequestBody?>()
    val image: LiveData<RequestBody?>
        get() = _image

    fun image(requestBody: RequestBody) {
        _image.value = requestBody
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


    fun posting(token: String, imageUri: RequestBody, content: RequestBody) {
        Log.v("POST DATA", token + imageUri + content)
        coroutineScope.launch {
            var post = MarkOIApi.retrofitService.postKiriman(
                "Bearer " + token, imageUri, content
            )

            try {
                post.await()
                Log.v("POST", "BERHASIL")
//                _post.value = result
            } catch (e: Exception) {
                Log.v("GAGAL", e.message.toString())
                Log.v("POST", "GAGAL")
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
//
//    private fun isContentAny(content: String): Boolean {
//        return content.isNotBlank()
//    }
//
//    private fun isImageAny(image: RequestBody): Boolean {
//        return image.equals("null")
//    }
}

