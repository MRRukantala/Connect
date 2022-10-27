package com.example.connect.presentation.main.home.tablayout.agenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.databinding.AgendaFragmentBinding
import com.example.connect.presentation.main.home.HomeFragmentDirections
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AgendaFragment : Fragment() {

    lateinit var binding: AgendaFragmentBinding
    private val viewModel: AgendaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            AgendaAdapter.OnclickListener {
                runCatching {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailAgendaFragment())
                }
            }
        )
    }

    private fun handleState(state: AgendaState) {

        when (state) {
            is AgendaState.Loading -> {
                binding.apply {
                    msvListAgenda.viewState = MultiStateView.ViewState.LOADING
                }
            }
            is AgendaState.Success -> {
                binding.apply {
                    msvListAgenda.viewState =
                        if (state.agendaEntity.isEmpty()) MultiStateView.ViewState.EMPTY else MultiStateView.ViewState.CONTENT
                }
            }

            else -> {}
        }

    }


}