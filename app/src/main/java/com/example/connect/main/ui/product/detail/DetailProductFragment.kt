package com.example.connect.main.ui.product.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.DetailProductFragmentBinding

class DetailProductFragment : Fragment() {

    lateinit var binding: DetailProductFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application

        binding = DataBindingUtil.inflate(
            inflater, R.layout.detail_product_fragment, container, false
        )

        val productUmumProperty =
            DetailProductFragmentArgs.fromBundle(requireArguments()).selectedProductUmum
        val viewModelFactory = DetailProductViewModelFactory(productUmumProperty, application)

        binding.viewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailProductViewModel::class.java)

        binding.include7.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root

    }

}