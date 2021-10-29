package com.example.connect.main.ui.home.tablayout.news.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.DetailNewsFragmentBinding

class DetailNewsFragment : Fragment() {

    lateinit var binding: DetailNewsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application

        binding = DataBindingUtil.inflate(inflater, R.layout.detail_news_fragment, container, false)
        binding.lifecycleOwner = this

        val newProperty = DetailNewsFragmentArgs.fromBundle(requireArguments()).selectedNews
        val viewModelFactory = DetailViewModelFactory(newProperty, application)

        binding.viewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailNewsViewModel::class.java)

        binding.include6.backImage.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}