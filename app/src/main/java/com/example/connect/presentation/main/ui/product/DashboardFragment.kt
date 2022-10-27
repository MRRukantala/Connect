package com.example.connect.presentation.main.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.databinding.FragmentDashboardBinding
import com.example.connect.presentation.main.ui.product.tabLayout.myproduct.MyProductFragment
import com.example.connect.presentation.main.ui.product.tabLayout.productumum.ProductUmumFragment
import com.example.connect.utilites.TabAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    lateinit var binding: FragmentDashboardBinding
    private val viewModel: DashboardViewModelTerbaru by viewModels()

    val name = arrayOf(
        "Produk Publik",
        "Produk Saya"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(
            inflater,
            container, false
        )

        val fragmentList = arrayListOf(
            ProductUmumFragment(),
            MyProductFragment()
        )

        val tabLayout = binding.tabLayoutHome
        val viewPager = binding.viewPagerHome

        val adapter = TabAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(
            tabLayout,
            viewPager
        ) { tab, position ->
            tab.text = name[position]
        }.attach()

        val application = requireNotNull(activity).application
        binding.lifecycleOwner = viewLifecycleOwner

//        val viewModelDashboard =
//            ProductViewModelFactory(
//                requireActivity()
//                    .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
//                    .getString("token", "").toString(), application
//            )
//        val viewModel =
//            ViewModelProvider(this, viewModelDashboard).get(DashboardViewModel::class.java)
        binding.viewModelDashboard = viewModel

        binding.recyclerView.adapter = ProductAdapter(
            ProductAdapter.OnclickListener {
//                viewModel.displayNewsDetails(it)
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

        binding.tvKunjungiToko.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToStoreResmiFragment())
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.productByViewModel(2)
        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: DashboardState) {
        when (state) {
            is DashboardState.Loading -> {
                binding.apply {
                    msvListProduct.viewState = MultiStateView.ViewState.LOADING
                }
            }
            is DashboardState.Success -> {
                binding.apply {
                    msvListProduct.viewState =
                        if (state.dashboardEntity.isEmpty()) MultiStateView.ViewState.EMPTY
                        else MultiStateView.ViewState.CONTENT
                }
            }
            else -> {}
        }
    }

}

