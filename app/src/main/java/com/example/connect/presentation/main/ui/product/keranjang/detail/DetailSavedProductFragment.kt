package com.example.connect.presentation.main.ui.product.keranjang.detail

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.data.database.SavedProductDatabase
import com.example.connect.databinding.DetailSavedProductFragmentBinding
import java.net.URLEncoder

class DetailSavedProductFragment : Fragment() {

     lateinit var binding : DetailSavedProductFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val arguments = DetailSavedProductFragmentArgs.fromBundle(requireArguments())

        val dataSource = SavedProductDatabase.getInstance(application).savedProductDao
        val viewModelFactory = DetailSavedProductViewModelFactory(arguments.idSelectedSavedProduct, dataSource)

        val viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(DetailSavedProductViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.detail_saved_product_fragment, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.button2.setOnClickListener {
            val packageManager = requireActivity().packageManager
            val i = Intent(Intent.ACTION_VIEW)

            val url = "http://api.whatsapp.com/send?phone=+62895613383420&text=" +
                    URLEncoder.encode("Halo saya ingin bertanya terkait produk ${viewModel.getData().value!!.nama_produk}")
            i.setPackage("com.whatsapp")
            i.data = Uri.parse(url)

            if(i.resolveActivity(packageManager)!= null) {
                startActivity(i)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.include7.backImage.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}