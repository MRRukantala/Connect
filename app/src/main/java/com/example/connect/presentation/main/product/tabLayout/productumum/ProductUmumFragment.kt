package com.example.connect.presentation.main.product.tabLayout.productumum

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
import com.example.connect.databinding.ProductUmumFragmentBinding
import com.example.connect.presentation.main.product.ProdukFragmentDirections
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProductUmumFragment : Fragment() {

    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }

    lateinit var binding: ProductUmumFragmentBinding
    private val viewModel: ProductUmumViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ProductUmumFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.recyclerViewProductUmum.adapter = ProductUmumAdapter(
            ProductUmumAdapter.OnclickListener {
                mainNavigation?.navigate(
                    ProdukFragmentDirections.actionProdukFragmentToDetailProductFragment(
                        it.id
                    )
                )
            }
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllProduct()
        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: ProductUmumState) {
        when (state) {
            is ProductUmumState.Loading -> {
                binding.msvListProduct.viewState = MultiStateView.ViewState.LOADING
            }
            is ProductUmumState.Success -> {
                binding.msvListProduct.viewState =
                    if (state.productUmumEntity.isEmpty()) MultiStateView.ViewState.EMPTY
                    else MultiStateView.ViewState.CONTENT
            }
            else -> {}
        }
    }
}