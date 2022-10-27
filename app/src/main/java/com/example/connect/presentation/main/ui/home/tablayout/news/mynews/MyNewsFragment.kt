package com.example.connect.presentation.main.ui.home.tablayout.news.mynews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.MyNewsFragmentBinding
import com.example.connect.presentation.main.ui.home.tablayout.news.NewsAdapter
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
@AndroidEntryPoint
class MyNewsFragment : Fragment() {



    lateinit var binding : MyNewsFragmentBinding
    private val viewModel : MyNewsViewModelTerbaru by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = MyNewsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.rcv.adapter = NewsAdapter(
            NewsAdapter.OnclickListener{
                runCatching {

                }
            }
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.kirimanByUser(52)
        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: MyNewsState) {

        when(state){
            is MyNewsState.Loading ->{
                binding.apply {
                    msvListNews.viewState = MultiStateView.ViewState.LOADING
                }
            }
            is MyNewsState.Success ->{
                binding.apply {
                    msvListNews.viewState =
                        if (state.myNewsEntity.isEmpty()) MultiStateView.ViewState.EMPTY
                        else MultiStateView.ViewState.CONTENT
                }
            }
            else -> {}
        }

    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//
//        binding.back.backImage.setOnClickListener {
//            findNavController().popBackStack()
//        }
//
//
//    }

}