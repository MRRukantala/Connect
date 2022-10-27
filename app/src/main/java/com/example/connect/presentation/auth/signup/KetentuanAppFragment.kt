package com.example.connect.presentation.auth.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentKetentuanAppBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KetentuanAppFragment : Fragment() {
    lateinit var binding: FragmentKetentuanAppBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ketentuan_app, container, false)

        binding.include.backImage.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }
}
