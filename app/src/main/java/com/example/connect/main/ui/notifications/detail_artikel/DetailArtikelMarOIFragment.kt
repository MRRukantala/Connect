package com.example.connect.main.ui.notifications.detail_artikel

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.connect.R

class DetailArtikelMarOIFragment : Fragment() {

    companion object {
        fun newInstance() = DetailArtikelMarOIFragment()
    }

    private lateinit var viewModel: DetailArtikelMarOIViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_artikel_mar_o_i_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailArtikelMarOIViewModel::class.java)
        // TODO: Use the ViewModel
    }

}