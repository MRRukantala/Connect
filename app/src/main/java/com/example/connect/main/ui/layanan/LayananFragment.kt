package com.example.connect.main.ui.layanan

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentNotificationsBinding

class LayananFragment : Fragment() {

    lateinit var binding: FragmentNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false)
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application
        val factory = LayananViewModelFactory(
            requireActivity()
                .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
                .getString("token", "").toString(), application
        )

        val viewModel = ViewModelProvider(this, factory).get(LayananViewModel::class.java)
        binding.viewModel = viewModel

        binding.recyclerView2.adapter = Adapter(
            Adapter.OnClickistener {
                viewModel.displayNewsDetails(it)
            }
        )

        viewModel.navigatedToSelectedNews.observe(viewLifecycleOwner, {
            if (null != it) {
                this.findNavController().navigate(
                    LayananFragmentDirections.actionNotificationsFragmentToDetailArtikelMarOIFragment(
                        it
                    )
                )
                viewModel.displayNewsDetailsCompelete()
            }
        })

        return binding.root
    }

}