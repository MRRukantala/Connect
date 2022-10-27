package com.example.connect.presentation.main.layanan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.databinding.FragmentNotificationsBinding
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LayananFragment : Fragment() {

    lateinit var binding: FragmentNotificationsBinding
    private val viewModel: LayananViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            FragmentNotificationsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.recyclerView2.adapter = LayananAdapter(
            LayananAdapter.OnclickListener {
                findNavController().navigate(LayananFragmentDirections.actionNotificationsFragmentToDetailArtikelMarOIFragment())
            }
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllLayanan()
        observe()


    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: LayananState) {
        when (state) {
            is LayananState.Loading -> {
                binding.msvListLayanan.viewState = MultiStateView.ViewState.LOADING
            }
            is LayananState.Success -> {
                binding.msvListLayanan.viewState =
                    if (state.layananEntity.isEmpty()) MultiStateView.ViewState.EMPTY
                    else MultiStateView.ViewState.CONTENT
            }
            else -> {}
        }
    }

}