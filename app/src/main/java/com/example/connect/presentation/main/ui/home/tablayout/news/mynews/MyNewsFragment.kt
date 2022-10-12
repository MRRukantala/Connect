package com.example.connect.presentation.main.ui.home.tablayout.news.mynews

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.MyNewsFragmentBinding

class MyNewsFragment : Fragment() {

    private val viewModel : MyNewsViewModel by lazy {
        ViewModelProvider(this).get(MyNewsViewModel::class.java)
    }
    lateinit var binding : MyNewsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.my_news_fragment, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.rcv.adapter = Adapter(
            Adapter.OnClickListener{

            },
            Adapter.OnClickMoreListener{

            }
        )

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        binding.back.backImage.setOnClickListener {
            findNavController().popBackStack()
        }


    }

}