package com.example.connect.presentation.main.ui.product.tabLayout.productumum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.databinding.ProductUmumFragmentBinding
import com.example.connect.presentation.main.ui.product.DashboardFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProductUmumFragment : Fragment() {

    lateinit var binding: ProductUmumFragmentBinding
    private val viewModel: ProductUmumViewModelTerbaru by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ProductUmumFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner


//        val application = requireNotNull(activity).application
//        val factory =
//            ProductUmumViewModelFactory(
//                requireActivity()
//                    .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
//                    .getString("token", "").toString()
//                , application
//            )
//
//
//        val viewModel = ViewModelProvider(this, factory).get(ProductUmumViewModel::class.java)
        binding.viewModel = viewModel

        binding.recyclerViewProductUmum.adapter = ProductUmumAdapter(
            ProductUmumAdapter.OnclickListener {
                runCatching {
                    findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToDetailProductFragment())
                }
            }
        )

//        viewModel.navigatedToSelectedNews.observe(viewLifecycleOwner, {
//            if (null != it) {
//                this.findNavController().navigate(
//                    DashboardFragmentDirections.actionDashboardFragmentToDetailProductFragment(it)
//                )
//                viewModel.displayNewsDetailsCompelete()
//            }
//        })

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
            is ProductUmumState.Loading -> {}
            is ProductUmumState.Success -> {}
        }

    }


}