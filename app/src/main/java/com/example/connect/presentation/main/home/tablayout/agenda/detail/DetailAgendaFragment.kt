package com.example.connect.presentation.main.home.tablayout.agenda.detail

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
import androidx.navigation.fragment.navArgs
import com.example.connect.R
import com.example.connect.databinding.DetailAgendaFragmentBinding
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailAgendaFragment : Fragment() {

    lateinit var binding: DetailAgendaFragmentBinding
    private val viewModel: DetailAgendaViewModel by viewModels()

    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }
    private val args by navArgs<DetailAgendaFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DetailAgendaFragmentBinding.inflate(inflater, container, false)

        binding.agenda = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.include6.backImage.setOnClickListener {
            mainNavigation?.navigateUp()
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.detailAgenda(args.id)
        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: DetailAgendaState) {
        when (state) {
            is DetailAgendaState.Loading -> {
                binding.apply {
                    msvListDetailAgenda.viewState = MultiStateView.ViewState.LOADING
                }

            }

            is DetailAgendaState.Success -> {
                binding.apply {
                    msvListDetailAgenda.viewState = MultiStateView.ViewState.CONTENT
                    binding.cardView.setOnClickListener {
                        mainNavigation?.navigate(
                            DetailAgendaFragmentDirections.actionDetailAgendaFragmentToImageOpener(
                                state.detailAgendaEntity.gambar
                            )
                        )
                    }
                }

            }
            else -> {}
        }

    }


}