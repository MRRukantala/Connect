package com.example.connect.presentation.main.product.keranjang.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.connect.R
import com.example.connect.data.database.ItemKeranjangEntity
import com.example.connect.databinding.DetailSavedProductFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLEncoder

@AndroidEntryPoint
class DetailSavedProductFragment : Fragment() {

    lateinit var binding: DetailSavedProductFragmentBinding
    private val args by navArgs<DetailSavedProductFragmentArgs>()
    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.detail_saved_product_fragment,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.data = args.data

        binding.include7.backImage.setOnClickListener {
            mainNavigation?.navigateUp()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button2.setOnClickListener {
            callSeller(args.data)
        }

    }

    private fun callSeller(data: ItemKeranjangEntity) {
        val packageManager = requireActivity().packageManager
        val i = Intent(Intent.ACTION_VIEW)

        val url = "http://api.whatsapp.com/send?phone=+62895613383420&text=" +
                URLEncoder.encode("Halo saya ingin bertanya terkait produk ${data.namaProduct}")
        i.setPackage("com.whatsapp")
        i.data = Uri.parse(url)

        if (i.resolveActivity(packageManager) != null) {
            startActivity(i)
        }
    }
}
