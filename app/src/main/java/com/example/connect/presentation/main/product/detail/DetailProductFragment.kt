package com.example.connect.presentation.main.product.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.connect.R
import com.example.connect.databinding.DetailProductFragmentBinding
import com.example.connect.domain.entity.DetailProductEntity
import com.example.connect.utilites.app.SharedPreferences
import com.example.connect.utilites.currency
import com.example.connect.utilites.imagePost
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.detail_product_fragment.view.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class DetailProductFragment : Fragment() {

    @Inject
    lateinit var pref: SharedPreferences

    lateinit var binding: DetailProductFragmentBinding
    private val viewModel: DetailProductViewModel by viewModels()
    private val args by navArgs<DetailProductFragmentArgs>()

    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }

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
            mainNavigation?.popBackStack()
        }

        binding.includ8.backImage.setOnClickListener {
            mainNavigation?.navigate(DetailProductFragmentDirections.actionDetailProductFragmentToKeranjangFragment())
        }

//        binding.cardView3.setOnClickListener {
//            findNavController().navigate(DetailProductFragmentDirections.actionDetailProductFragmentToImageOpener2(productUmumProperty.gambar))
//        }


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.detailProduct(args.id)
        observe()
        viewModel.inputKeranjang()

    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: DetailProductState) {
        when (state) {
            is DetailProductState.Loading -> {
                binding.msvDetailProduct.viewState = MultiStateView.ViewState.LOADING
            }

            is DetailProductState.Success -> {
                checkData(state.detailProductEntity.get(0))
                val data: DetailProductEntity = state.detailProductEntity.get(0)
                binding.nama.text = data.nama
                binding.deskripsi.text = data.deskripsi
                currency(binding.harga, data.harga)
                imagePost(binding.image, data.gambar)
                binding.msvDetailProduct.viewState = MultiStateView.ViewState.CONTENT
            }
            else -> {}
        }
    }

    private fun checkData(get: DetailProductEntity) {
        if (get.idUser ==  pref.getIdUser()) {
            binding.constraintLayout.visibility = View.GONE
        }
    }

}