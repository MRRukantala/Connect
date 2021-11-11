package com.example.connect.main.ui.home.tablayout.agenda

import android.content.Context
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
import com.example.connect.utilites.isConnected

class AgendaFragment : Fragment() {

    lateinit var binding: AgendaFragmentBinding
    private val viewModel: AgendaViewModel by lazy {
        ViewModelProvider(this).get(AgendaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.agenda_fragment, container, false)

        binding.fabAgendas.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddAgendaFragment())
        }

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        when {
            isConnected(requireContext()) -> {
                binding.nointernet.visibility = View.GONE
                binding.viewModel = viewModel
                binding.rcv.adapter = AgendaAdapter(
                    AgendaAdapter.OnClickListener {
                        viewModel.displayAgendaDetail(it)
                    }
                )
            }
            else -> {
                binding.nointernet.visibility = View.VISIBLE
            }
        }

        viewModel.navigatedToSelectedAgenda.observe(viewLifecycleOwner, {
            if (null != it) {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailAgendaFragment(it)
                )
                viewModel.displayAgendaDetailComplete()
            }
        })

        return binding.root
    }


}