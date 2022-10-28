package com.example.connect.presentation.main.home.tablayout.news.mynews

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
import com.example.connect.databinding.MyNewsFragmentBinding
import com.example.connect.presentation.main.home.tablayout.news.NewsAdapter
import com.example.connect.utilites.app.SharedPreferences
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MyNewsFragment : Fragment() {

    @Inject
    lateinit var pref: SharedPreferences

    lateinit var binding: MyNewsFragmentBinding
    private val viewModel: MyNewsViewModel by viewModels()

    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = MyNewsFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.rcv.adapter = NewsAdapter(
            NewsAdapter.OnclickListener {
                mainNavigation?.navigate(
                    MyNewsFragmentDirections.actionMyNewsFragmentToDetailNewsFragment(
                        it.id
                    )
                )
            }
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.kirimanByUser(pref.getIdUser())
        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: MyNewsState) {

        when (state) {
            is MyNewsState.Loading -> {
                binding.apply {
                    msvListNews.viewState = MultiStateView.ViewState.LOADING
                }
            }
            is MyNewsState.Success -> {
                binding.apply {
                    msvListNews.viewState =
                        if (state.myNewsEntity.isEmpty()) MultiStateView.ViewState.EMPTY
                        else MultiStateView.ViewState.CONTENT
                }
            }
            else -> {}
        }

    }
}