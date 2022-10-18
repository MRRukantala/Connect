package com.example.connect.presentation.main.ui.menu.info_pendidikan.pendidikan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.connect.R
import com.example.connect.databinding.PendidikanUserFragmentBinding

class PendidikanUserFragment : Fragment() {

    lateinit var binding: PendidikanUserFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.pendidikan_user_fragment, container, false)
        binding.lifecycleOwner = this

        val application = requireNotNull(activity).application








        binding.fabNews.setOnClickListener {

        }



        return binding.root
    }
}