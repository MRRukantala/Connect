package com.example.connect.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.connect.R
import com.example.connect.databinding.FragmentImageOpenerBinding
import com.example.connect.utilites.imagePost
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImageOpener : Fragment() {

    lateinit var binding: FragmentImageOpenerBinding
    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }
    val args by navArgs<ImageOpenerArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_image_opener, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner

        binding.include.backImage.setOnClickListener {
            mainNavigation?.navigateUp()
        }


        return binding.root
    }
}

