package com.example.connect.main.ui.home.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.NewsFragmentBinding
import com.example.connect.main.ui.home.HomeFragmentDirections
import com.example.connect.main.ui.home.news.model.PostsAdapter

class NewsFragment : Fragment() {

    lateinit var binding: NewsFragmentBinding
    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(this).get(NewsViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.news_fragment, container, false)

        binding.fabNews.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddNewsFragment2())
        }

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.rcv.adapter = PostsAdapter(
            PostsAdapter.OnClickListener {
                viewModel.displayNewsDetails(it)
            }
        )

        viewModel.navigatedToSelectedNews.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailNewsFragment(it)
                )
                viewModel.displayNewsDetailsCompelete()
            }
        })


        return binding.root
    }

}