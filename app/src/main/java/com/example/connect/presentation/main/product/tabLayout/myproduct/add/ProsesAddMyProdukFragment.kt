package com.example.connect.presentation.main.product.tabLayout.myproduct.add

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentProsesAddMyProdukBinding

class ProsesAddMyProdukFragment : Fragment() {

    lateinit var binding: FragmentProsesAddMyProdukBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_proses_add_my_produk, container, false
        )

        Handler(Looper.getMainLooper()).postDelayed({
//            findNavController().navigate(ProsesAddMyProdukFragmentDirections.actionProsesAddMyProdukFragmentToDashboardFragment())
        }, 2000)
        return binding.root
    }

}