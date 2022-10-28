package com.example.connect.presentation.main.layanan

import android.os.Bundle
import android.util.Log
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

        binding.recyclerView2.adapter = LayananAdapter(
            LayananAdapter.OnclickListener {
                findNavController().navigate(LayananFragmentDirections.actionNotificationsFragmentToDetailArtikelMarOIFragment())
            }
        )

        binding.rvPlaylist.adapter = PlaylistAdapter(
            PlaylistAdapter.OnclickListener {

            }
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllLayanan()
        viewModel.getAllPlaylist()

        observe()
        binding.viewModel = viewModel
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
        viewModel.statePlaylist.flowWithLifecycle(lifecycle)
            .onEach { state -> handleStatePlaylist(state) }
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

    private fun handleStatePlaylist(state: PlaylistState) {
        when (state) {
            is PlaylistState.Loading -> {
                Log.v("DATA", "loading")
            }
            is PlaylistState.Success -> {
                Log.v("DATA", state.layananEntity.toString())
            }
            else -> {}
        }
    }

}