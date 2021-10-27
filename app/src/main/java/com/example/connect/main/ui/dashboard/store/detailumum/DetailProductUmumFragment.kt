package com.example.connect.main.ui.dashboard.store.detailumum

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.connect.R

class DetailProductUmumFragment : Fragment() {

    companion object {
        fun newInstance() = DetailProductUmumFragment()
    }

    private lateinit var viewModel: DetailProductUmumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_product_umum_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailProductUmumViewModel::class.java)
        // TODO: Use the ViewModel
    }

}