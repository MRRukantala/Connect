package com.example.connect.main.ui.menu.info_pendidikan.pendidikan.edit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FormPendidikanFragmentBinding

class FormPendidikanFragment : Fragment() {

    lateinit var binding: FormPendidikanFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.form_pendidikan_fragment, container, false)

        binding.btnSimpan.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnHapus.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}