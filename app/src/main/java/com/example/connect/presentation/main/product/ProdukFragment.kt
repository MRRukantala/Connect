package com.example.connect.presentation.main.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentTokoBinding
import com.example.connect.presentation.main.product.tabLayout.myproduct.MyProductFragment
import com.example.connect.presentation.main.product.tabLayout.myproduct.MyProductViewModel
import com.example.connect.presentation.main.product.tabLayout.productumum.ProductUmumFragment
import com.example.connect.presentation.main.product.tabLayout.productumum.ProductUmumViewModel
import com.example.connect.utilites.TabAdapter
import com.example.connect.utilites.app.SharedPreferences
import com.google.android.material.tabs.TabLayoutMediator
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ProdukFragment : Fragment() {

    private var _binding: FragmentTokoBinding? = null

    private val binding get() = _binding!!
    private val viewModel: ProdukViewModel by viewModels()
    private val viewModelMyProduk: MyProductViewModel by activityViewModels()
    private val viewModelProdukPublik: ProductUmumViewModel by activityViewModels()

    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }

    @Inject
    lateinit var pref: SharedPreferences

    val name = arrayOf(
        "Produk Publik",
        "Produk Saya"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTokoBinding.inflate(
            inflater,
            container, false
        )
        val root: View = binding.root

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

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.recyclerView.adapter = ProductAdapter(
            ProductAdapter.OnclickListener {
                mainNavigation?.navigate(
                    ProdukFragmentDirections.actionProdukFragmentToDetailProductFragment(
                        it.id
                    )
                )
            }
        )

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.productByViewModel(1)
        viewModelMyProduk.getAllProductByUser(pref.getIdUser())
        viewModelProdukPublik.getAllProduct()
        binding.tvKunjungiToko.setOnClickListener {
            mainNavigation?.navigate(ProdukFragmentDirections.actionProdukFragmentToStoreResmiFragment())
        }
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

