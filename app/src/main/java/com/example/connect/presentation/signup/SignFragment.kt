package com.example.connect.presentation.signup

import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentSignBinding
import com.example.connect.presentation.login.LoginState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SignFragment : Fragment() {

    //    private lateinit var signViewModel: LoginViewModel
    lateinit var binding: FragmentSignBinding
    private val viewModel: RegisterViewModel by viewModels()

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

            }
            back.backImage.setOnClickListener {
                findNavController().popBackStack()
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
        listOf(etNama,etEmail, etPassword).forEach {
            it.addTextChangedListener(textWatcher)
        }

        etNama.setText(viewModel.nama.value)
        etEmail.setText(viewModel.email.value)
        etPassword.setText(viewModel.password.value)

        viewModel.setLevel(0)
        binding.login.setOnClickListener {
            viewModel.register()
        }

//        signViewModel.loginFormState.observe(viewLifecycleOwner,
//            Observer { loginFormState ->
//                if (loginFormState == null) {
//                    return@Observer
//                }
//                loginButton.isEnabled = loginFormState.isDataValid
//                loginFormState.usernameError?.let {
//                    usernameEditText.error = getString(it)
//                }
//
//                loginFormState.emailError?.let {
//                    emailEditText.error = getString(it)
//                }
//
//                loginFormState.passwordError?.let {
//                    passwordEditText.error = getString(it)
//                }
//            })


//        val afterTextChangedListener = object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//                // ignore
//            }
//
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                // ignore
//            }
//
////            override fun afterTextChanged(s: Editable) {
////                signViewModel.loginDataChanged(
////                    usernameEditText.text.toString(),
////                    emailEditText.text.toString(),
////                    passwordEditText.text.toString()
////                )
////            }
//        }
//        usernameEditText.addTextChangedListener(afterTextChangedListener)
//        emailEditText.addTextChangedListener(afterTextChangedListener)
//        passwordEditText.addTextChangedListener(afterTextChangedListener)

//        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                signViewModel.signUp(
//                    usernameEditText.text.toString(),
//                    emailEditText.text.toString(),
//                    passwordEditText.text.toString()
//                )
//            }
//            false
//        }

//        loginButton.setOnClickListener {
//            loadingProgressBar.visibility = View.VISIBLE
//            signViewModel.signUp(
//                usernameEditText.text.toString(),
//                emailEditText.text.toString(),
//                passwordEditText.text.toString()
//            )
//
//            signViewModel.loginResult.observe(viewLifecycleOwner, {
//                if (null != it) {
//                    findNavController().navigate(SignFragmentDirections.actionSignFragmentToVerifFragment())
//
////                    updateUiWithUser()
//                } else {
//                    signViewModel.messageLogin.observe(viewLifecycleOwner, {
//                        when {
//                            it.equals(getString(R.string.email_already)) -> {
//                                showLoginFailed(R.string.email_already)
//                                loadingProgressBar.visibility = View.GONE
//                                signViewModel.messageComplete()
//                            }
//                        }
//                    })
//                }
//            })
//        }
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: RegisterState) {
        when (state) {
            is RegisterState.Loading -> {
                Log.v("DATA", "loading")
            }
            is RegisterState.Success -> {
                Log.v("DATA", "Sukses")



            }
            else -> {}
        }

    }

    private fun updateUiWithUser() {
        // TODO : initiate successful logged in experience
//        val appContext = context?.applicationContext ?: return
//        findNavController().navigate(com.example.connect.presentation.signup.ui.login.SignFragmentDirections.actionSignFragmentToVerifFragment())
//        signViewModel.messageComplete()
        requireActivity().finish()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        binding = null
    }
}