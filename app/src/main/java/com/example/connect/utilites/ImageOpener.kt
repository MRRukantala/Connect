package com.example.connect.utilites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.connect.R
import com.example.connect.databinding.FragmentImageOpenerBinding
import android.graphics.BitmapFactory
import android.view.ScaleGestureDetector
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.net.URL


class ImageOpener : Fragment() {

    lateinit var binding: FragmentImageOpenerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val application = requireNotNull(activity).application

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_image_opener, container, false
        )
        binding.lifecycleOwner = this

        binding.include.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        val newProperty = ImageOpenerArgs.fromBundle(requireArguments()).link

//        binding.binding = ViewModelProvider(
//            this,
//            ImageOpenerViewModelFactory(newProperty, application)
//        ).get(ImageOpenerViewModel::class.java)





        return binding.root
    }
}

