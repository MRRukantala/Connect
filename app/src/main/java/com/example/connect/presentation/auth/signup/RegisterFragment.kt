package com.example.connect.presentation.auth.signup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentSignBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    lateinit var binding: FragmentSignBinding
    private val viewModel: RegisterViewModel by viewModels()

    private val authNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment_authentication) }

    private lateinit var etNama: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s?.isEmpty() == true) {
                viewModel.setAllFieldNull()
            } else {
                viewModel.setAllField(
                    binding.username.text.toString(),
                    binding.email.text.toString(),
                    binding.password.text.toString()
                )
            }
        }

        override fun afterTextChanged(s: Editable?) {
            binding.login.isEnabled = true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.apply {
            textView30.setOnClickListener {
                authNavController?.navigate(RegisterFragmentDirections.actionSignFragmentToKetentuanAppFragment())
            }
            back.backImage.setOnClickListener {
                authNavController?.navigateUp()
            }

            etNama = username
            etEmail = email
            etPassword = password

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        listOf(etNama, etEmail, etPassword).forEach {
            it.addTextChangedListener(textWatcher)
        }

        etNama.setText(viewModel.nama.value)
        etEmail.setText(viewModel.email.value)
        etPassword.setText(viewModel.password.value)
        viewModel.setLevel(0)
        binding.login.setOnClickListener {
            viewModel.register()
        }
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: RegisterState) {
        when (state) {
            is RegisterState.Loading -> {
                binding.iloading.root.visibility = View.VISIBLE
            }
            is RegisterState.Success -> {
                binding.iloading.root.visibility = View.VISIBLE
                authNavController?.navigate(RegisterFragmentDirections.actionSignFragmentToVerifFragment())
            }
            else -> {}
        }
    }
}