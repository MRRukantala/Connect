package com.example.connect.presentation.splash

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.model.response.LoginResponse
import com.example.connect.data.model.response.UserLogin
import com.example.connect.databinding.FragmentSplashScreenBinding
import com.example.connect.utilites.app.SharedPreferences
import com.example.connect.utilites.isConnected
import com.example.connect.utilites.toastConnection
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {
    private lateinit var binding: FragmentSplashScreenBinding
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_splash_screen, container, false)

        val token = sharedPreferences.getToken()
        if (token.isNotEmpty()) goToHome()



        Handler(Looper.getMainLooper()).postDelayed({
            checking()
        }, 3000)



        return binding.root
    }

    private fun goToHome() {
        findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToMainAppActivity())
    }


    private fun checking() {
        if (checkConnection()) {
            when (loggedIn().data?.user?.id) {
                -1 ->
                    findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToMenuFragment())
                else -> {
                    findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToMainAppActivity())
                }
            }
            Log.v("CEK ID", loggedIn().data?.user?.id.toString())
            Log.v("CEK TOKEN", loggedIn().data?.token.toString())
        } else {
            toastConnection(requireContext())
        }
    }

    private fun checkConnection(): Boolean {
        return isConnected(requireContext())
    }

    fun loggedIn(): ResponseObjectWrapper<LoginResponse> {
        val sharedPreferences = requireActivity()
            .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)

        val sharedPreferencesDataUser = UserLogin(
            sharedPreferences.getInt("id", -1),
            sharedPreferences.getString("name", "").toString(),
            sharedPreferences.getString("email", "").toString(),
            sharedPreferences.getString("email_verified_at", "").toString(),
            sharedPreferences.getString("status", ""),
            sharedPreferences.getString("level", "").toString(),
            sharedPreferences.getString("created_at", "").toString(),
            sharedPreferences.getString("updated_at", "").toString()
        )

        val sharedPreferencesResponse = LoginResponse(
            sharedPreferences.getString("token", "").toString(),
            sharedPreferences.getString("token_type", "").toString(),
            sharedPreferencesDataUser
        )

        return ResponseObjectWrapper(
            true,
            "null",
            sharedPreferencesResponse,
        )
    }
}