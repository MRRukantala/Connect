package com.example.connect.presentation.main.ui.menu.info_pendidikan.pendidikan.edit

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FormPendidikanFragmentBinding

class FormPendidikanFragment : Fragment() {

    lateinit var binding: FormPendidikanFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.form_pendidikan_fragment, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        val token = requireActivity()
            .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
            .getString("token", "").toString()

        val instansitv = binding.instansi
        val jenjangtv = binding.jenjang
        val fakultastv = binding.fakultas
        val jurusantv = binding.jurusan
        val tahunmasuktv = binding.tahunMasuk
        val tahunlulustv = binding.tahunLulus












        binding.include8.backImage.setOnClickListener {
            findNavController().popBackStack()
        }








        return binding.root
    }
}