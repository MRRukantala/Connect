package com.example.connect.main.ui.menu.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.SaranNKritikFragmentBinding

class SaranNKritikFragment : Fragment() {

    lateinit var binding: SaranNKritikFragmentBinding

    companion object {
        fun newInstance() = SaranNKritikFragment()
    }

    private lateinit var viewModel: SaranNKritikViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.saran_n_kritik_fragment, container, false)

        binding.send.setOnClickListener {
            findNavController().navigate(SaranNKritikFragmentDirections.actionSaranNKritikFragmentToSaranTerimaKasihFragment())
        }

        binding.include11.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SaranNKritikViewModel::class.java)
        // TODO: Use the ViewModel
    }

}