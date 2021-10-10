package com.example.connect.main.ui.home.agenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.AgendaFragmentBinding
import com.example.connect.main.ui.home.HomeFragmentDirections

class AgendaFragment : Fragment() {

    lateinit var binding: AgendaFragmentBinding

    companion object {
        fun newInstance() = AgendaFragment()
    }

    private lateinit var viewModel: AgendaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.agenda_fragment, container, false)

        binding.fabAgendas.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddAgendaFragment())
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AgendaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}