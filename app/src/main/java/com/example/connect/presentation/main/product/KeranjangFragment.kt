package com.example.connect.presentation.main.product

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.data.database.SaveProductDataEntity
import com.example.connect.databinding.FragmentKeranjangBinding
import com.example.connect.presentation.main.product.keranjang.Adapter
import com.example.connect.presentation.main.product.keranjang.KeranjangViewModelTerbaru
import com.example.connect.presentation.main.product.keranjang.KeranjangViewState
import com.example.connect.utilites.app.SharedPreferences
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.net.URLEncoder
import javax.inject.Inject

@AndroidEntryPoint
class KeranjangFragment : Fragment() {

    private lateinit var binding: FragmentKeranjangBinding
    private val viewModel: KeranjangViewModelTerbaru by viewModels()

    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }

    @Inject
    lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKeranjangBinding.inflate(
            inflater, container, false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.include7.backImage.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.recyclerView.adapter = Adapter(
            Adapter.OnClickListener {
                mainNavigation?.navigate(
                    KeranjangFragmentDirections.actionKeranjangFragmentToDetailSavedProductFragment(
                        it.toItemKeranjangEntity()
                    )
                )
            },
            Adapter.OnClickListenerDelete {

            },
            Adapter.OnClickCall {
                toWa(it)
            })

        return binding.root
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: KeranjangViewState) {

        when (state) {
            is KeranjangViewState.Loading -> {
                binding.msvKeranjang.viewState = MultiStateView.ViewState.LOADING
            }
            is KeranjangViewState.Success -> {
                binding.msvKeranjang.viewState =
                    if (state.keranjangEntity.isEmpty()) MultiStateView.ViewState.EMPTY
                    else MultiStateView.ViewState.CONTENT
            }
            else -> {}
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDataById(pref.getIdUser())
        observe()
    }

    private fun toWa(it: SaveProductDataEntity) {
        if (it.wa!!.equals(null)) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        } else if (it.wa!!.length < 10) {
            Toast.makeText(requireContext(), "No hp salah", Toast.LENGTH_SHORT).show()
        } else {
            val packageManager = requireActivity().packageManager
            val i = Intent(Intent.ACTION_VIEW)

            val url = "http://api.whatsapp.com/send?phone={${it.wa}&text=" +
                    URLEncoder.encode("Halo saya ingin bertanya terkait produk ${it.nama_produk}")
            i.setPackage("com.whatsapp")
            i.data = Uri.parse(url)

            if (i.resolveActivity(packageManager) != null) {
                startActivity(i)
            }
        }
    }
}