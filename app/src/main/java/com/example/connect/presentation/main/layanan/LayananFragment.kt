package com.example.connect.presentation.main.layanan

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
import com.example.connect.databinding.FragmentNotificationsBinding
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LayananFragment : Fragment() {

    lateinit var binding: FragmentNotificationsBinding
    private val viewModel: LayananViewModel by viewModels()

    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }

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
                mainNavigation?.navigate(
                    LayananFragmentDirections.actionNotificationsFragmentToDetailArtikelMarOIFragment(
                        it.id
                    )
                )
            }
        )

        binding.rvPlaylist.adapter = PlaylistAdapter(
            PlaylistAdapter.OnclickListener {
                mainNavigation?.navigate(
                    LayananFragmentDirections.actionNotificationsFragmentToVideoELearningFragment(
                        it.idPlaylist
                    )
                )
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
                binding.msvPlaylist.viewState = MultiStateView.ViewState.LOADING
            }
            is PlaylistState.Success -> {
                binding.msvPlaylist.viewState =
                    if (state.layananEntity.isEmpty()) MultiStateView.ViewState.EMPTY
                    else MultiStateView.ViewState.CONTENT
            }
            else -> {}
        }
    }

}