package com.example.connect.presentation.main.product.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.databinding.DetailProductFragmentBinding
import com.example.connect.domain.entity.DetailProductEntity
import com.example.connect.utilites.currency
import com.example.connect.utilites.imagePost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailProductFragment : Fragment() {

    lateinit var binding: DetailProductFragmentBinding
    private val viewModel: DetailProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DetailProductFragmentBinding.inflate(
            inflater, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel


        binding.include7.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.includ8.backImage.setOnClickListener {
//            findNavController().navigate(DetailProductFragmentDirections.actionDetailProductFragmentToKeranjangFragment())
        }

//        binding.cardView3.setOnClickListener {
//            findNavController().navigate(DetailProductFragmentDirections.actionDetailProductFragmentToImageOpener2(productUmumProperty.gambar))
//        }


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.detailProduct()
        observe()

//        binding.nama.text = viewModel.data.value[1].nama

//        currency(binding.harga,viewModel.data.value[1].harga)
//        imagePost(binding.image, viewModel.data.value[1].gambar)

    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: DetailProductState) {
        when (state) {
            is DetailProductState.Loading -> {
            }

            is DetailProductState.Success -> {
                val data : DetailProductEntity = state.detailProductEntity.get(0)
                binding.nama.text = data.nama
                binding.deskripsi.text = data.deskripsi
                currency(binding.harga, data.harga)
                imagePost(binding.image, data.gambar)

            }
            else -> {}
        }
    }

}