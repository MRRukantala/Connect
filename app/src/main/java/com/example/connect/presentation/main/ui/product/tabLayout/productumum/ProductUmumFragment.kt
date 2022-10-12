package com.example.connect.presentation.main.ui.product.tabLayout.productumum

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
import com.example.connect.databinding.ProductUmumFragmentBinding
import com.example.connect.presentation.main.ui.product.DashboardFragmentDirections

class ProductUmumFragment : Fragment() {

    lateinit var binding: ProductUmumFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.product_umum_fragment, container, false)
        binding.lifecycleOwner = this


        val application = requireNotNull(activity).application
        val factory =
            ProductUmumViewModelFactory(
                requireActivity()
                    .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
                    .getString("token", "").toString()
                , application
            )


        val viewModel = ViewModelProvider(this, factory).get(ProductUmumViewModel::class.java)
        binding.viewModel = viewModel

        binding.recyclerViewProductUmum.adapter = Adapter(
            Adapter.OnClickListener{
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

        return binding.root
    }


}