package com.example.connect.presentation.main.ui.product

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentDashboardBinding
import com.example.connect.presentation.main.ui.product.tabLayout.myproduct.MyProductFragment
import com.example.connect.presentation.main.ui.product.tabLayout.productumum.ProductUmumFragment
import com.example.connect.utilites.TabAdapter
import com.google.android.material.tabs.TabLayoutMediator

class DashboardFragment : Fragment() {

    lateinit var binding: FragmentDashboardBinding

    val name = arrayOf(
        "Produk Publik",
        "Produk Saya"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_dashboard,
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
        binding.lifecycleOwner = this

        val viewModelDashboard =
            ProductViewModelFactory(
                requireActivity()
                    .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
                    .getString("token", "").toString(), application
            )
        val viewModel =
            ViewModelProvider(this, viewModelDashboard).get(DashboardViewModel::class.java)
        binding.viewModelDashboard = viewModel

        binding.recyclerView.adapter = Adapter(
            Adapter.OnClickListener {
                viewModel.displayNewsDetails(it)
            }
        )

        viewModel.navigatedToSelectedNews.observe(viewLifecycleOwner, {
            if (null != it) {
                this.findNavController().navigate(
                    DashboardFragmentDirections.actionDashboardFragmentToDetailProductFragment(it)
                )
                viewModel.displayNewsDetailsCompelete()
            }
        })

        binding.tvKunjungiToko.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToStoreResmiFragment())
        }

        return binding.root
    }

}

