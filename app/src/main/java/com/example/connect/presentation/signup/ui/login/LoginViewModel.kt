package com.example.connect.presentation.signup.ui.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.connect.R
import com.example.connect.presentation.signup.data.model.UserResponse
import com.example.connect.utilites.MarkOIApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

//class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
class LoginViewModel : ViewModel() {
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<UserResponse>()
    val loginResult: LiveData<UserResponse> = _loginResult

    private val _messageLogin = MutableLiveData<String>()
    val messageLogin: LiveData<String?> = _messageLogin

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun signUp(username: String, email: String, password: String) {
        // can be launched in a separate asynchronous job
        /*
         val result = loginRepository.login(username, email, password)

         if (result is Result.Success) {
             _loginResult.value =
                 LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
         } else {
             _loginResult.value = LoginResult(error = R.string.login_failed)
         }
         */

        coroutineScope.launch {

            try {
                val getData = MarkOIApi.retrofitService.registerAPI(username, email, password)
                val result = getData.await()
                Log.v("DATA", result.toString())
                _loginResult.value = result


            } catch (e: HttpException) {
                _loginResult.value = null
                if(e!!.code() == 422){
                    val responseBody = e.response()?.errorBody()?.string()
                    val jsonObject = JSONObject(responseBody!!.trim())
                    val jsonObjectMessage = jsonObject.getJSONObject("errors")
                    val jsonArray = jsonObjectMessage.getJSONArray("email")
                    val message  = jsonArray.getString(0).toString()

                    _messageLogin.value = message
                    Log.v("Data", message)
                }
            }

        }
    }

    fun loginDataChanged(username: String, email: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isFormatEmailValid(email)) {
            _loginForm.value = LoginFormState(emailError = R.string.email_form_message)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }

    private fun isFormatEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun messageComplete() {
        _messageLogin.value = null
    }
}