package com.example.connect.main.ui.menu.info_pendidikan.pendidikan

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.connect.R

class PendidikanUserFragment : Fragment() {

    companion object {
        fun newInstance() = PendidikanUserFragment()
    }

    private lateinit var viewModel: PendidikanUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pendidikan_user_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PendidikanUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}