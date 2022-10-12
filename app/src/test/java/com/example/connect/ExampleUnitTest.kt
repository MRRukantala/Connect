package com.example.connect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.connect.presentation.login.data.model.UserResponse
import com.example.connect.utilites.MarkOIApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
//    @Test
//    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)
//    }

    @Test
    fun getDataLogin() {

        val result = getData()

        assertEquals(17, result)

    }

    fun getData(): Int {

        val _loginResult = MutableLiveData<UserResponse?>()
        val loginResult: LiveData<UserResponse?> = _loginResult

        val coroutineScope = CoroutineScope(Job() + Dispatchers.Default)

        coroutineScope.launch {
            val result = MarkOIApi.retrofitService.loginAPI("auful03@gmail.com", "auful123")

            try {
                val result = result.await()
                _loginResult.value = result
            } catch (e: Exception) {
                _loginResult.value = null
            }
        }
    return loginResult.value!!.user.id
    }
}



