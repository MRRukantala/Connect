package com.example.connect.presentation.main.home.tablayout.news.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.databinding.DetailNewsFragmentBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class DetailNewsFragment : Fragment() {

    lateinit var binding: DetailNewsFragmentBinding
    private val viewModel: DetailNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val application = requireNotNull(activity).application

        binding = DetailNewsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

//        val newProperty = DetailNewsFragmentArgs.fromBundle(requireArguments()).selectedNews
//        val viewModelFactory = DetailViewModelFactory(newProperty, application)


        binding.viewModel = viewModel

        binding.include6.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.cardImage.setOnClickListener {
//            findNavController().navigate(DetailNewsFragmentDirections.actionDetailNewsFragmentToImageOpener(newProperty.gambar))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.detailNews(1)
        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: DetailNewsState) {
        when(state){
            is DetailNewsState.Loading ->{}
            is DetailNewsState.Success ->{}
            else -> {}
        }
    }
}