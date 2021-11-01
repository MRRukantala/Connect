package com.example.connect.main.ui.product.tabLayout.myproduct.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.connect.R

class AddMyProdukFragment : Fragment() {

    companion object {
        fun newInstance() = AddMyProdukFragment()
    }

    private lateinit var viewModel: AddMyProdukViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_my_produk_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddMyProdukViewModel::class.java)
        // TODO: Use the ViewModel
    }

}