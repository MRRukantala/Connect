package com.example.connect.presentation.auth.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentLoginBinding
import com.example.connect.presentation.auth.ContainerAuthFragmentDirections
import com.example.connect.utilites.app.SharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var pref: SharedPreferences

    private lateinit var binding: FragmentLoginBinding
    val viewModel: LoginViewModel by viewModels()

    private val authNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment_authentication) }
    private val mainNavController: NavController? by lazy { activity?.findNavController(R.id.nav_host_fragment_main) }

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s?.isEmpty() == true) {
                viewModel.setAllFieldNull()
            } else {
                viewModel.setAllField(
                    binding.editTextEmail.editText?.text.toString(),
                    binding.editTextPassword.editText?.text.toString()
                )
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(
            inflater, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModelLogin = viewModel

        binding.include2.backImage.setOnClickListener {
            findNavController().navigateUp()
        }

        etEmail = binding.editTextEmail.editText!!
        etPassword = binding.editTextPassword.editText!!

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        listOf(etEmail, etPassword).forEach {
            it.addTextChangedListener(textWatcher)
        }

        etEmail.setText(viewModel.email.value)
        etPassword.setText(viewModel.password.value)

        binding.login.setOnClickListener {
            viewModel.login()
        }

    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: LoginState) {
        when (state) {
            is LoginState.Loading -> {
                loading()
            }
            is LoginState.Error -> {
                Toast.makeText(requireContext(), "${state.response.message}", Toast.LENGTH_LONG)
                    .show()
                binding.iLoading.root.visibility = View.GONE
            }
            is LoginState.Success -> {
                pref.saveToken(
                    state.loginEntity.token,
                    state.loginEntity.user.level!!.toInt(),
                    state.loginEntity.user.id!!
                )
                binding.iLoading.root.visibility = View.GONE
                mainNavController?.navigate(ContainerAuthFragmentDirections.actionContainerAuthFragmentToContainerMainFragment())
            }
            else -> {}
        }
    }

    private fun loading() {
        binding.iLoading.root.visibility = View.VISIBLE
    }


}