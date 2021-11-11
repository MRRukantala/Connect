package com.example.connect.main.ui.product.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.database.SavedProductDatabase
import com.example.connect.databinding.DetailProductFragmentBinding
import com.example.connect.main.ui.home.tablayout.news.detail.DetailNewsFragmentDirections
import java.net.URLEncoder

class DetailProductFragment : Fragment() {

    lateinit var binding: DetailProductFragmentBinding

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application

        binding = DataBindingUtil.inflate(
            inflater, R.layout.detail_product_fragment, container, false
        )

        val productUmumProperty =
            DetailProductFragmentArgs.fromBundle(requireArguments()).selectedProductUmum

        val id = requireActivity()
            .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
            .getInt("id", -1)

        val dataSource = SavedProductDatabase.getInstance(application).savedProductDao

        val viewModelFactory = DetailProductViewModelFactory(id, dataSource, productUmumProperty, application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(DetailProductViewModel::class.java)

        binding.viewModel = viewModel


        binding.include7.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.button2.setOnClickListener {
            val packageManager = requireActivity().packageManager
            val i = Intent(Intent.ACTION_VIEW)

            val url = "http://api.whatsapp.com/send?phone=${productUmumProperty.wa}&text=" +
                    URLEncoder.encode("Halo saya ingin bertanya terkait produk ${productUmumProperty.nama_produk}")
            i.setPackage("com.whatsapp")
            i.data = Uri.parse(url)

            if(i.resolveActivity(packageManager)!= null) {
                startActivity(i)
            }
        }

        binding.includ8.backImage.setOnClickListener {
            findNavController().navigate(DetailProductFragmentDirections.actionDetailProductFragmentToKeranjangFragment())
        }

        binding.cardView3.setOnClickListener {
            findNavController().navigate(DetailProductFragmentDirections.actionDetailProductFragmentToImageOpener2(productUmumProperty.gambar))
        }

        viewModel.state.observe(viewLifecycleOwner, {
            when(it){
                AddProdukState.SUCCESS -> {
                    Toast.makeText(context, "Produk Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                }
                AddProdukState.ERROR -> {
                    Toast.makeText(context, "Produkt Gagal Disimpan", Toast.LENGTH_SHORT).show()
                }
            }
        })



        return binding.root

    }

}