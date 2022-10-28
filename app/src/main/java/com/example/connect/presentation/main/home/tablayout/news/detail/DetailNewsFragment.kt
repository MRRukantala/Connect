package com.example.connect.presentation.main.home.tablayout.news.detail

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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.connect.R
import com.example.connect.databinding.DetailNewsFragmentBinding
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailNewsFragment : Fragment() {

    lateinit var binding: DetailNewsFragmentBinding
    private val viewModel: DetailNewsViewModel by viewModels()
    private val args by navArgs<DetailNewsFragmentArgs>()

    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DetailNewsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.include6.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.detailNews(args.id)
        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }


    private fun handleState(state: DetailNewsState) {
        when (state) {
            is DetailNewsState.Loading -> {
                binding.apply {
                    msvDetailBerita.viewState = MultiStateView.ViewState.LOADING
                }
            }
            is DetailNewsState.Success -> {
                binding.apply {
                    msvDetailBerita.viewState = MultiStateView.ViewState.CONTENT
                    val data = state.detailNewsEntity[0]
                    binding.data = data
                    cardImage.setOnClickListener {
                        mainNavigation?.navigate(
                            DetailNewsFragmentDirections.actionDetailNewsFragmentToImageOpener(
                                data.fotoKonten
                            )
                        )
                    }
                }
            }
            else -> {}
        }
    }
}