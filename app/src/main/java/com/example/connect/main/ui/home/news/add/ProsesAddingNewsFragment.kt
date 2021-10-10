package com.example.connect.main.ui.home.news.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.connect.R
import com.example.connect.databinding.FragmentProsesAddingNewsBinding

class ProsesAddingNewsFragment : Fragment() {

    lateinit var binding: FragmentProsesAddingNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_proses_adding_news, container, false)
        return binding.root
    }

}