package com.example.connect.splash

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
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentSplashScreenBinding
import com.example.connect.login.data.model.DataUser
import com.example.connect.login.data.model.UserResponse
import com.example.connect.utilites.isConnected
import com.example.connect.utilites.toastConnection

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {
    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_splash_screen, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            checking()
        }, 3000)


        return binding.root
    }

    private fun checking() {
        if (checkConnection()) {
            when (loggedIn().data.user.id) {
                -1 ->
                    findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToMenuFragment())
                else -> {
                    findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToMainAppActivity())
                }
            }
            Log.v("CEK ID", loggedIn().data.user.id.toString())
            Log.v("CEK TOKEN", loggedIn().data.token)
        } else {
            toastConnection(requireContext())
        }
    }

    private fun checkConnection(): Boolean {
        return isConnected(requireContext())
    }

    fun loggedIn(): UserResponse {
        val sharedPreferences = requireActivity()
            .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)

        val sharedPreferencesDataUser = DataUser(
            sharedPreferences.getInt("id", -1),
            sharedPreferences.getString("name", "").toString(),
            sharedPreferences.getString("email", "").toString(),
            sharedPreferences.getString("email_verified_at", "").toString(),
            sharedPreferences.getString("status", ""),
            sharedPreferences.getString("level", "").toString(),
            sharedPreferences.getString("created_at", "").toString(),
            sharedPreferences.getString("updated_at", "").toString()
        )

        val sharedPreferencesResponse = com.example.connect.login.data.model.response(
            sharedPreferences.getString("token", "").toString(),
            sharedPreferences.getString("token_type", "").toString(),
            sharedPreferencesDataUser
        )

        return UserResponse(
            sharedPreferencesResponse,
            "null"
        )
    }
}