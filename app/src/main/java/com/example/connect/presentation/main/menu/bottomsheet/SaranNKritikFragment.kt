package com.example.connect.presentation.main.menu.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.SaranNKritikFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaranNKritikFragment : Fragment() {
    lateinit var binding: SaranNKritikFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.saran_n_kritik_fragment, container, false)

        binding.include11.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}