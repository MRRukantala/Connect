package com.example.connect.presentation.main.ui.product.resmi.resmi

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
import com.example.connect.presentation.main.ui.product.tabLayout.productumum.Adapter

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

        binding.recyclerView.adapter = Adapter(
            Adapter.OnClickListener {
                viewModel.displayNewsDetails(it)
            }
        )

        binding.include10.backImage.setOnClickListener {
            findNavController().navigate(StoreResmiFragmentDirections.actionStoreResmiFragmentToDashboardFragment())
        }

        viewModel.navigatedToSelectedNews.observe(viewLifecycleOwner, {
            if(null != it){
                this.findNavController().navigate(
                    StoreResmiFragmentDirections.actionStoreResmiFragmentToDetailProductFragment(it)
                )
                viewModel.displayNewsDetailsCompelete()
            }
        })

        return binding.root

    }
}