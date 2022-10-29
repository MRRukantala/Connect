package com.example.connect.presentation.main.ui.product.keranjang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.connect.databinding.FragmentKeranjangBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KeranjangFragment : Fragment() {

    private lateinit var binding: FragmentKeranjangBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKeranjangBinding.inflate(
            inflater, container, false
        )

        binding.include7.backImage.setOnClickListener {
            findNavController().navigateUp()
        }


//        val dataSource = SavedProductDatabase.getInstance(application).savedProductDao

//        val dataSource = SavedProductDatabase.getInstance(application).savedProductDao

//        val viewModelFactory = KeranjangViewModelFactory(id, dataSource, application)
//
//        val keranjangViewModel =
//            ViewModelProvider(this, viewModelFactory).get(KeranjangViewModel::class.java)

//        binding.binding = keranjangViewModel
        binding.lifecycleOwner = viewLifecycleOwner


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