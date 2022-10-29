package com.example.connect.presentation.main.product.resmi.resmi

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
import com.example.connect.databinding.FragmentStoreResmiBinding
import com.example.connect.presentation.main.product.tabLayout.productumum.ProductUmumAdapter
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class StoreResmiFragment : Fragment() {

    lateinit var binding: FragmentStoreResmiBinding
    private val viewModel: StoreResmiViewModel by viewModels()

    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoreResmiBinding.inflate(
            inflater,
            container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.include10.backImage.setOnClickListener {
            mainNavigation?.navigateUp()
        }

        binding.recyclerView.adapter = ProductUmumAdapter(
            ProductUmumAdapter.OnclickListener {
                mainNavigation?.navigate(
                    StoreResmiFragmentDirections.actionStoreResmiFragmentToDetailProductFragment(
                        it.id
                    )
                )
            }
        )

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllProductByAdmin()
        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: StoreResmiState) {

        when (state) {
            is StoreResmiState.Loading -> {
                binding.msvListProduct.viewState = MultiStateView.ViewState.LOADING
            }
            is StoreResmiState.Success -> {
                binding.msvListProduct.viewState =
                    if (state.storeResmiEntity.isEmpty()) MultiStateView.ViewState.EMPTY
                    else MultiStateView.ViewState.CONTENT
            }
            else -> {}
        }

    }
}