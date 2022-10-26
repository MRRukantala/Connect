package com.example.connect.presentation.main.ui.product.keranjang

import android.content.Context
import android.content.Intent
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
import com.example.connect.data.database.SavedProductDatabase
import com.example.connect.databinding.FragmentKeranjangBinding
import java.net.URLEncoder

class KeranjangFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentKeranjangBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_keranjang, container, false
        )

        binding.include7.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        val application = requireNotNull(this.activity).application

        val id = requireActivity()
            .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
            .getInt("id", -1)

        val dataSource = SavedProductDatabase.getInstance(application).savedProductDao

//        val viewModelFactory = KeranjangViewModelFactory(id, dataSource, application)
//
//        val keranjangViewModel =
//            ViewModelProvider(this, viewModelFactory).get(KeranjangViewModel::class.java)

//        binding.binding = keranjangViewModel
        binding.setLifecycleOwner(this)


//        val adapter = Adapter(Adapter.OnClickListener {
//            keranjangViewModel.displayToDetail(it)
//        }, Adapter.OnClickListenerDelete {
//            keranjangViewModel.delete(it)
//        },
//            Adapter.OnClickCall {
//            if(it.wa!!.equals(null)){
//                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
//            } else if(it.wa!!.length < 10){
//                Toast.makeText(requireContext(), "No hp salah", Toast.LENGTH_SHORT).show()
//            } else {
//                val packageManager = requireActivity().packageManager
//                val i = Intent(Intent.ACTION_VIEW)
//
//                val url = "http://api.whatsapp.com/send?phone={${it.wa}&text=" +
//                        URLEncoder.encode("Halo saya ingin bertanya terkait produk ${it.nama_produk}")
//                i.setPackage("com.whatsapp")
//                i.data = Uri.parse(url)
//
//                if(i.resolveActivity(packageManager)!= null) {
//                    startActivity(i)
//                }
//            }
//        })



//        keranjangViewModel.navigateToDetail.observe(viewLifecycleOwner, {
//            findNavController().navigate(
//                KeranjangFragmentDirections.actionKeranjangFragmentToDetailSavedProductFragment(
//                    it
//                )
//            )
//        })

//        binding.apply {
//            keranjangViewModel.properties.observe(viewLifecycleOwner, {
//                if (it.size != 0) {
//                    binding.lottieAnimationView.visibility = View.GONE
//                }
//            })
//        }

//        binding.recyclerView.adapter = adapter


//        keranjangViewModel.properties.observe(viewLifecycleOwner, {
//            it?.let {
//                adapter.submitList(it)
//            }
//        })

        return binding.root
    }
}