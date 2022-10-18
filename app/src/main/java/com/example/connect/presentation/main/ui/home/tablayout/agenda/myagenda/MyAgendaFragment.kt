package com.example.connect.presentation.main.ui.home.tablayout.agenda.myagenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.MyAgendaFragmentBinding

class MyAgendaFragment : Fragment() {

    lateinit var binding: MyAgendaFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.my_agenda_fragment, container, false)

        return binding.root
    }



}