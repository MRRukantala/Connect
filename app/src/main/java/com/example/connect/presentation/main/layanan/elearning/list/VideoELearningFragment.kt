package com.example.connect.presentation.main.layanan.elearning.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.connect.databinding.FragmentVideoELearningBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class VideoELearningFragment : Fragment() {

    lateinit var binding: FragmentVideoELearningBinding
    private lateinit var viewModel: VideoELearningViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoELearningBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.rvVideoELearning.adapter = VideoELearningAdapter(
            VideoELearningAdapter.OnclickListener {

            }
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllVideo(0)
        observer()
    }

    private fun observer() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: VideoELearningState) {
        when (state) {
            is VideoELearningState.Loading -> {}
            is VideoELearningState.Success -> {}
            else -> {}
        }
    }
}