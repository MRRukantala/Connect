package com.example.connect.presentation.main.ui.home.tablayout.agenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.AgendaFragmentBinding
import com.example.connect.presentation.main.ui.home.HomeFragmentDirections
import com.example.connect.utilites.isConnected
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
@AndroidEntryPoint
class AgendaFragment : Fragment() {

    lateinit var binding: AgendaFragmentBinding
    private val viewModel: AgendaViewModelTerbaru by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AgendaFragmentBinding.inflate(inflater, container, false)

        binding.fabAgendas.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddAgendaFragment())
        }

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.agenda()
        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)

        binding.rcv.adapter = AgendaAdapter(
            AgendaAdapter.OnclickListener{
                runCatching {

                }
            }
        )
    }

    private fun handleState(state: AgendaState) {

        when(state){
            is AgendaState.Loading ->{}
            is AgendaState.Success ->{}

        }

    }


}