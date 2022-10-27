package com.example.connect.presentation.auth.boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.connect.databinding.MenuFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {
    private lateinit var binding: MenuFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MenuFragmentBinding.inflate(inflater,  container, false)
        binding.button.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToSignFragment())
        }

        binding.textView4.setOnClickListener {
            findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToLoginFragment())
        }
        return binding.root
    }
}