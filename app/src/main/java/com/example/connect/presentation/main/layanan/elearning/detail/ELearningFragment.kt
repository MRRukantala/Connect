package com.example.connect.presentation.main.layanan.elearning.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.connect.R
import com.example.connect.databinding.FragmentELearningBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ELearningFragment : Fragment() {

    private lateinit var binding: FragmentELearningBinding
    private val mainNavigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_fragment_menu)
    }
    val args by navArgs<ELearningFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentELearningBinding.inflate(inflater, container, false)
        binding.include4.backImage.setOnClickListener {
            mainNavigation?.navigateUp()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}