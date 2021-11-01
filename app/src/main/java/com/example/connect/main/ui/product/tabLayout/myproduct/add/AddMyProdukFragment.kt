package com.example.connect.main.ui.product.tabLayout.myproduct.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.AddMyProdukFragmentBinding

class AddMyProdukFragment : Fragment() {

    lateinit var binding: AddMyProdukFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.add_my_produk_fragment, container, false)

        binding.fabNews.setOnClickListener {
            findNavController().navigate( AddMyProdukFragmentDirections.actionAddMyProdukFragmentToProsesAddMyProdukFragment())
        }

        binding.include3.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }


}