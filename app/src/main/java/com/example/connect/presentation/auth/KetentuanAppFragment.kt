package com.example.connect.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.connect.databinding.FragmentKetentuanAppBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KetentuanAppFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentKetentuanAppBinding.inflate(inflater, container, false)

        binding.include.backImage.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }
}
