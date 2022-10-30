package com.example.connect.presentation.main.layanan.elearning.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.connect.R
import com.example.connect.databinding.FragmentVideoELearningBinding
import com.kennyc.view.MultiStateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class VideoELearningFragment : Fragment() {

    lateinit var binding: FragmentVideoELearningBinding
    private val viewModel: VideoELearningViewModel by viewModels()

    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }

    val args by navArgs<VideoELearningFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoELearningBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.include4.backImage.setOnClickListener {
            mainNavigation?.navigateUp()
        }
        binding.rvVideoELearning.adapter = VideoELearningAdapter(
            VideoELearningAdapter.OnclickListener {
                mainNavigation?.navigate(
                    VideoELearningFragmentDirections.actionVideoELearningFragmentToVideoActivity(
                        it
                    )
                )
            }
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllVideo(args.id)
        observer()
    }

    private fun observer() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: VideoELearningState) {
        when (state) {
            is VideoELearningState.Loading -> {
                binding.msvListVideo.viewState = MultiStateView.ViewState.LOADING
            }
            is VideoELearningState.Success -> {
                binding.msvListVideo.viewState =
                    if (state.videoElearningEntity.videos.isEmpty() == true) MultiStateView.ViewState.EMPTY else MultiStateView.ViewState.CONTENT
            }
            else -> {}
        }
    }
}