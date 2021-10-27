package com.example.connect.login.ui.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.connect.R
import com.example.connect.login.data.model.DataUser
import com.example.connect.login.data.model.UserResponse
import com.example.connect.utilites.MarkOIApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException


class LoginViewModel : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<UserResponse?>()
    val loginResult: LiveData<UserResponse?> = _loginResult

    private val _messageLogin = MutableLiveData<String>()
    val messageLogin: LiveData<String?> = _messageLogin

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _activeUser = MutableLiveData<DataUser>()
    val activeUser: LiveData<DataUser>
        get() = _activeUser


    fun login(username: String, password: String) {
        coroutineScope.launch {
            // can be launched in a separate asynchronous job

            try {
                val getData = MarkOIApi.retrofitService.loginAPI(username, password)
                val result = getData.await()

                _loginResult.value = result
            } catch (e: HttpException) {

                _loginResult.value = null

                if (e is HttpException && (e!!.code() == 401)) {
                    var responseBody = e!!.response()?.errorBody()?.string()
                    val jsonObject = JSONObject(responseBody!!.trim())
                    var message = jsonObject.getString("message")

                    _messageLogin.value = message

//                    _loginResult.value!!.message = message
                }
//                _loginResult.value?.message = e.message().toString()

//
//                _loginResult.value = result
            }
        }
    }


    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(username).matches()
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    fun messageComplete(){
        _messageLogin.value = null
    }

}