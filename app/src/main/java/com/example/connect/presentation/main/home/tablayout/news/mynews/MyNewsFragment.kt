package com.example.connect.presentation.main.home.tablayout.news.mynews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.connect.databinding.MyNewsFragmentBinding
import com.example.connect.presentation.main.home.tablayout.news.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
@AndroidEntryPoint
class MyNewsFragment : Fragment() {



    lateinit var binding : MyNewsFragmentBinding
    private val viewModel : MyNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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
            }
            is MyNewsState.Success ->{}
            else -> {}
        }

    }
}