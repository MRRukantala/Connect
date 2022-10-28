package com.example.connect.presentation.main.home.tablayout.news.detail

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.databinding.DetailNewsFragmentBinding
import com.example.connect.utilites.imagePost
import com.example.connect.utilites.time
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailNewsFragment : Fragment() {

    lateinit var binding: DetailNewsFragmentBinding
    private val viewModel: DetailNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        val application = requireNotNull(activity).application

        binding = DetailNewsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

//        val newProperty = DetailNewsFragmentArgs.fromBundle(requireArguments()).selectedNews
//        val viewModelFactory = DetailViewModelFactory(newProperty, application)


//        binding.viewModel = viewModel

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
        viewModel.detailNews(61)
        observe()


    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }


    private fun handleState(state: DetailNewsState) {
        when(state){
            is DetailNewsState.Loading ->{
                binding.apply {
                    msvDetailBerita.viewState = MultiStateView.ViewState.LOADING
                }
            }
            is DetailNewsState.Success ->{
                binding.apply {
                    msvDetailBerita.viewState = MultiStateView.ViewState.CONTENT
                    Log.v("DATA DETAIL NEWS", state.detailNewsEntity.toString())
                    Log.v("DATA DETAIL NEWS1", viewModel?.data?.value.toString())
                    val data = state.detailNewsEntity[0]
                    binding.data = data

//                    binding.nameTextView.text = data.nama
//                    imagePost(binding.circleImageView, data.fotoUser)


                }


            }
            else -> {}
        }
    }
}