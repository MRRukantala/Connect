package com.example.connect.main.ui.home.tablayout.news.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.connect.R
import com.example.connect.utilites.MarkOIApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import java.lang.Exception

class AddNewsViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _postKirimanForm = MutableLiveData<PostKirimanFormState>()
    val postKirimanState: LiveData<PostKirimanFormState> = _postKirimanForm


    private val _post = MutableLiveData<AddedResponses>()
    val post: LiveData<AddedResponses>
        get() = _post

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun posting(token: String, imageUri: RequestBody , content: RequestBody){
        Log.v("POST DATA", token + imageUri + content)
        coroutineScope.launch {
            var post = MarkOIApi.retrofitService.postKiriman(
                "Bearer " + token, imageUri, content
            )

            try {
                val result = post.await()
                Log.v("POST", "BERHASIL")
                _post.value = result
            } catch (e: Exception){
                Log.v("GAGAL", e.message.toString())
                Log.v("POST", "GAGAL")
            }
        }
    }

    fun postKirimanDataChanged(imageUri: String, content: String) {
        if (!isContentAny(content)) {
            _postKirimanForm.value =
                PostKirimanFormState(contentError = R.string.invalid_username)
        } else if (isImageAny(imageUri)) {
            _postKirimanForm.value = PostKirimanFormState(imageError = R.string.invalid_username)
        } else {
            _postKirimanForm.value = PostKirimanFormState(isDataValid = true)
        }
    }

    private fun isContentAny(content: String): Boolean {
        return content.isNotBlank()
    }

    private fun isImageAny(image: String): Boolean {
        return image.equals("null")
    }
}

data class PostKirimanFormState(
    val imageError: Int? = null,
    val contentError: Int? = null,
    val isDataValid: Boolean = false
)