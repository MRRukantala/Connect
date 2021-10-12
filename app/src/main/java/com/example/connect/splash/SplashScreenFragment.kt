package com.example.connect.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentSplashScreenBinding
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

        if (isConnected(requireContext())) {
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToMenuFragment())
            }, 3000)
        } else {
            toastConnection(requireContext())
        }

        return binding.root
    }
}