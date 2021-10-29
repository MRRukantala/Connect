package com.example.connect.main.ui.product.resmi.resmi

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
import com.example.connect.databinding.FragmentStoreResmiBinding
import com.example.connect.main.ui.product.DashboardFragmentDirections
import com.example.connect.main.ui.product.tabLayout.productumum.ProductUmumAdapter

class StoreResmiFragment : Fragment() {

    lateinit var binding: FragmentStoreResmiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_store_resmi,
            container, false
        )

        val application = requireNotNull(activity).application
        binding.lifecycleOwner = this

        val viewModelStoreResmiFragment =
            StoreResmiViewModelFactory(
                requireActivity()
                    .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
                    .getString("token", "").toString(), application
            )

        val viewModel = ViewModelProvider(this, viewModelStoreResmiFragment)
            .get(StoreResmiViewModel::class.java)

        binding.viewModel = viewModel

        binding.recyclerView.adapter = ProductUmumAdapter(
            ProductUmumAdapter.OnClickListener {
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

        return binding.root

    }
}