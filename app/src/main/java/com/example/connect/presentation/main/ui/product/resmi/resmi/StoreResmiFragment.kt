package com.example.connect.presentation.main.ui.product.resmi.resmi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.databinding.FragmentStoreResmiBinding
import com.example.connect.presentation.main.ui.product.tabLayout.productumum.ProductUmumAdapter
import com.example.connect.presentation.main.ui.product.tabLayout.productumum.ProductUmumState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class StoreResmiFragment : Fragment() {

    lateinit var binding: FragmentStoreResmiBinding
    private val viewModel: StoreResmiViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentStoreResmiBinding.inflate(
            inflater,
            container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


//        val viewModelStoreResmiFragment =
//            StoreResmiViewModelFactory(
//                requireActivity()
//                    .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
//                    .getString("token", "").toString(), application
//            )
//
//        val viewModel = ViewModelProvider(this, viewModelStoreResmiFragment)
//            .get(StoreResmiViewModel::class.java)

//        binding.viewModel = viewModel
//
//        binding.recyclerView.adapter = ProductUmumAdapter(
//            ProductUmumAdapter.OnClickListener {
//                viewModel.displayNewsDetails(it)
//            }
//        )

        binding.include10.backImage.setOnClickListener {
            findNavController().navigate(StoreResmiFragmentDirections.actionStoreResmiFragmentToDashboardFragment())
        }

        binding.recyclerView.adapter = ProductUmumAdapter(
            ProductUmumAdapter.OnclickListener{
                runCatching {
                    findNavController().navigate(StoreResmiFragmentDirections.actionStoreResmiFragmentToDetailProductFragment())
                }
            }
        )

//        viewModel.navigatedToSelectedNews.observe(viewLifecycleOwner, {
//            if(null != it){
//                this.findNavController().navigate(
//                    StoreResmiFragmentDirections.actionStoreResmiFragmentToDetailProductFragment(it)
//                )
//                viewModel.displayNewsDetailsCompelete()
//            }
//        })

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
            is StoreResmiState.Loading -> {}
            is StoreResmiState.Success -> {}
        }

    }
}