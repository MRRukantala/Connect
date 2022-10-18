package com.example.connect.presentation.main.ui.home.tablayout.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.databinding.NewsFragmentBinding
import com.example.connect.presentation.main.ui.home.HomeFragmentDirections
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class NewsFragment : Fragment() {

    lateinit var binding: NewsFragmentBinding
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsFragmentBinding.inflate(inflater, container, false)

        binding.fabNews.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddNewsFragment2())
        }

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.rvNews.adapter = NewsAdapter(
            NewsAdapter.OnclickListener {
                runCatching {

                }

            }
        )

//        viewModel.navigatedToSelectedNews.observe(viewLifecycleOwner, Observer {
//            if (null != it) {
//                this.findNavController().navigate(
//                    HomeFragmentDirections.actionHomeFragmentToDetailNewsFragment(it)
//                )
//                viewModel.displayNewsDetailsCompelete()
//            }
//        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> HandleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun HandleState(state: NewsState) {

        when(state){
            is NewsState.Loading ->{

            }
            is NewsState.Success ->{

            }
        }

    }

}