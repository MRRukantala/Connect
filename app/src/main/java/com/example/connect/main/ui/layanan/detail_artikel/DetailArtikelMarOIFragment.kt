package com.example.connect.main.ui.layanan.detail_artikel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.DetailArtikelMarOIFragmentBinding

class DetailArtikelMarOIFragment : Fragment() {

    lateinit var binding: DetailArtikelMarOIFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.detail_artikel_mar_o_i_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this

        val data = DetailArtikelMarOIFragmentArgs.fromBundle(requireArguments()).selectedArtikelMarkOI

        val viewModelFactory = DetailArtikelMarkOIViewModelFactory(
            data,
            requireNotNull(activity).application
        )

        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(DetailArtikelMarOIViewModel::class.java)

        binding.include4.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.cardView8.setOnClickListener {
            findNavController().navigate(DetailArtikelMarOIFragmentDirections.actionDetailArtikelMarOIFragmentToImageOpener3(data.gambar))
        }

        return binding.root
    }


}