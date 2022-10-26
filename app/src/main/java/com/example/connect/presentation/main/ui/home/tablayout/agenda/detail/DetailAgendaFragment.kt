package com.example.connect.presentation.main.ui.home.tablayout.agenda.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.databinding.DetailAgendaFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailAgendaFragment : Fragment() {

    lateinit var binding: DetailAgendaFragmentBinding
    private val viewModel: DetailAgendaViewModelTerbaru by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DetailAgendaFragmentBinding.inflate(inflater, container, false)

        binding.agenda = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.include6.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.cardView.setOnClickListener {

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.detailAgenda(63)

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

            }

            is DetailAgendaState.Success -> {

            }
        }

    }


}