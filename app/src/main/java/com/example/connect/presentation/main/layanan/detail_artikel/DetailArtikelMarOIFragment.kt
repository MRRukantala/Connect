package com.example.connect.presentation.main.layanan.detail_artikel

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
import com.example.connect.databinding.DetailArtikelMarOIFragmentBinding
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailArtikelMarOIFragment : Fragment() {

    lateinit var binding: DetailArtikelMarOIFragmentBinding
    private val viewModel: DetailArtikelMarOlViewModel by viewModels()
    private val args by navArgs<DetailArtikelMarOIFragmentArgs>()

    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DetailArtikelMarOIFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.include4.backImage.setOnClickListener {
            mainNavigation?.navigateUp()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.detailLayanan(args.id)
        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: DetailArtikelMarOlState) {
        when (state) {
            is DetailArtikelMarOlState.Loading -> {
                binding.msvDetailLayanan.viewState = MultiStateView.ViewState.LOADING
            }
            is DetailArtikelMarOlState.Success -> {
                binding.msvDetailLayanan.viewState = MultiStateView.ViewState.CONTENT
                binding.cardView8.setOnClickListener {
                    mainNavigation?.navigate(
                        DetailArtikelMarOIFragmentDirections.actionDetailArtikelMarOIFragmentToImageOpener3(
                            state.detailArtikelEntity.gambar
                        )
                    )
                }
            }
            else -> {}
        }
    }
}