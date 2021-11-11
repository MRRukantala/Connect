package com.example.connect.main.ui.product.tabLayout.myproduct.add

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

class AddMyProdukViewModel : ViewModel() {

    private val _value = MutableLiveData<PostProductFormState?>()

    val state = MutableLiveData<AddProdukState>()
    val value: LiveData<PostProductFormState?>
        get() = _value

    private val _nama_product = MutableLiveData<String?>()
    val nama_product: LiveData<String?>
        get() = _nama_product

    fun namaProduct(data: String) {
        _nama_product.value = data
    }

    private val _deskripsi = MutableLiveData<String?>()
    val deskripsi: LiveData<String?>
        get() = _deskripsi

    fun deskripsi(data: String) {
        _deskripsi.value = data
    }

    private val _image = MutableLiveData<MultipartBody.Part?>()
    val image: LiveData<MultipartBody.Part?>
        get() = _image

    fun image(part: MultipartBody.Part) {
        _image.postValue(part)
    }

    private val _harga = MutableLiveData<Int?>()
    val harga : LiveData<Int?>
    get() = _harga

    fun harga(data: Int){
        _harga.value = data
    }

    fun namaProductNull(){
        _nama_product.value = null
    }

    fun deskripsiProductNull(){
        _deskripsi.value = null
    }

    fun hargaNull(){
        _harga.value = null
    }

    fun imageNull(){
        _image.value =null
    }

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun posting(token: String) {
//        Log.v("POST DATA", token  + content)
        val namaProduct: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            _nama_product.value ?: ""
        )

        val deskripsiProduk: RequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            _deskripsi.value ?: ""
        )



        coroutineScope.launch {
            var post = MarkOIApi.retrofitService.postProduct(
                "Bearer $token", _image.value, _harga.value,
                namaProduct, deskripsiProduk
            )

            Log.v("DATA",  "$token ${image.value} ${harga.value} $namaProduct $deskripsiProduk" )
            state.postValue(AddProdukState.LOADING)
            try {
                post.await()
                Log.v("POST", "BERHASIL")
                state.postValue(AddProdukState.SUCCESS)
//                _post.value = result
            } catch (e: Exception) {
                Log.v("GAGAL", e.message.toString())
//                if (!e.message.equals("Required value 'id_user' missing at \$")) {
//                    Log.v("GAGAL", e.message.toString())
//                    Log.v("POST", "GAGAL")
//                    state.postValue(AddProdukState.ERROR)
//                } else {
//                    state.postValue(AddProdukState.SUCCESS)
//                }
            }
        }
    }
    fun postProdukDataChanged() {
        if (_image.value != null && _nama_product.value != null
            && _deskripsi.value != null && _harga.value != null) {
            _value.value = PostProductFormState(isDataValid = true)
        } else {
            _value.value = PostProductFormState(isDataValid = false)
        }
    }

    data class PostProductFormState(
        val isDataValid: Boolean = false
    )
}

enum class AddProdukState{ SUCCESS, LOADING, ERROR }