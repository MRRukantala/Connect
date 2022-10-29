package com.example.connect.presentation.main.home.tablayout.agenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.connect.R
import com.example.connect.databinding.AgendaFragmentBinding
import com.example.connect.presentation.main.home.HomeFragmentDirections
import com.example.connect.utilites.app.SharedPreferences
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AgendaFragment : Fragment() {

    lateinit var binding: AgendaFragmentBinding
    private val viewModel: AgendaViewModel by viewModels()

    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AgendaFragmentBinding.inflate(inflater, container, false)

        binding.fabAgendas.setOnClickListener {
            mainNavigation?.navigate(HomeFragmentDirections.actionHomeFragmentToAddAgendaFragment())
        }

        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.agenda()
        binding.viewModel = viewModel


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)

        binding.rcv.adapter = AgendaAdapter(
            AgendaAdapter.OnclickListener {
                mainNavigation?.navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailAgendaFragment(
                        it.id
                    )
                )
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