package com.example.connect.main.ui.menu.info_pendidikan.pendidikan

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.connect.R
import com.example.connect.databinding.PendidikanUserFragmentBinding
import com.example.connect.main.ui.menu.info_pendidikan.info.Adapter

class PendidikanUserFragment : Fragment() {

    lateinit var binding: PendidikanUserFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.pendidikan_user_fragment, container, false)
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application
        val factory =
            PendidikanViewModelFactory(
                requireActivity()
                    .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
                    .getInt("id", -1),
                requireActivity()
                    .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE)
                    .getString("token", "").toString(),
                application
            )

        val viewModel = ViewModelProvider(this, factory).get(PendidikanUserViewModel::class.java)
        binding.viewModel = viewModel


        binding.rv.adapter = Adapter(
            Adapter.OnClickistener{
                viewModel.displayNewsDetails(it)
            }
        )

        binding.apply {
            viewModel.properties.observe(viewLifecycleOwner, {
                if(it.size != 0){
                    binding.lottieAnimationView.visibility = View.GONE
                }
            })
        }

        binding.rv.adapter =

        return binding.root
    }


}