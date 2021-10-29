package com.example.connect.main.ui.product.tabLayout.myproduct

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.connect.R
import com.example.connect.databinding.MyProductFragmentBinding

class MyProductFragment : Fragment() {

    lateinit var binding: MyProductFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.my_product_fragment, container, false)
        binding.lifecycleOwner = this


        return binding.root
    }

}