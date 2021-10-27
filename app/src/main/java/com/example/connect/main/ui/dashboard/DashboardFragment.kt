package com.example.connect.main.ui.dashboard

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
import com.example.connect.login.data.model.response
import com.example.connect.main.ui.dashboard.store.DashbiardViewModelFactory
import com.example.connect.main.ui.dashboard.store.ProductAdapter
import com.example.connect.main.ui.dashboard.store.umum.ProductUmumAdapter

class DashboardFragment : Fragment() {

    lateinit var binding: FragmentDashboardBinding

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

        val application = requireNotNull(activity).application
        binding.lifecycleOwner = this


        val viewModelDashboard =
            DashbiardViewModelFactory(
                    requireActivity()
                        .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
                        .getString("token", "").toString()
                , application
            )
        val viewModel =
            ViewModelProvider(this, viewModelDashboard).get(DashboardViewModel::class.java)
        binding.viewModelTokoMarkOI = viewModel
        binding.viewModelProductUmum = viewModel

        binding.recyclerView.adapter = ProductAdapter(
            ProductAdapter.OnClickListener {
                viewModel.displayNewsDetails(it)
            }
        )

        binding.recyclerViewProductUmum.adapter = ProductUmumAdapter(
            ProductUmumAdapter.OnClickListener{
                viewModel.displayNewsDetails(it)
            }
        )

        viewModel.navigatedToSelectedNews.observe(viewLifecycleOwner, {
            if(null != it){
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

