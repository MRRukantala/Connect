package com.example.connect.signup.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentSignBinding

class SignFragment : Fragment() {

    private lateinit var signViewModel: LoginViewModel
    lateinit var binding: FragmentSignBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign, container, false)
        binding.apply {
            textView30.setOnClickListener {
                findNavController().navigate(SignFragmentDirections.actionSignFragmentToKetentuanAppFragment())
            }
            back.backImage.setOnClickListener {
                findNavController().popBackStack()
            }
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // signViewModel = ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)
        signViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val usernameEditText = binding.username
        val emailEditText = binding.email
        val passwordEditText = binding.password

        val loginButton = binding.login
        val loadingProgressBar = binding.loading

        signViewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid
                loginFormState.usernameError?.let {
                    usernameEditText.error = getString(it)
                }

                loginFormState.emailError?.let {
                    emailEditText.error = getString(it)
                }

                loginFormState.passwordError?.let {
                    passwordEditText.error = getString(it)
                }
            })


        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                signViewModel.loginDataChanged(
                    usernameEditText.text.toString(),
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        emailEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)

        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                signViewModel.signUp(
                    usernameEditText.text.toString(),
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            signViewModel.signUp(
                usernameEditText.text.toString(),
                emailEditText.text.toString(),
                passwordEditText.text.toString()
            )

            signViewModel.loginResult.observe(viewLifecycleOwner, {
                if (null != it) {
                    findNavController().navigate(SignFragmentDirections.actionSignFragmentToVerifFragment())

//                    updateUiWithUser()
                } else {
                    signViewModel.messageLogin.observe(viewLifecycleOwner, {
                        when {
                            it.equals(getString(R.string.email_already)) -> {
                                showLoginFailed(R.string.email_already)
                                loadingProgressBar.visibility = View.GONE
                                signViewModel.messageComplete()
                            }
                        }
                    })
                }
            })
        }
    }

    private fun updateUiWithUser() {
        // TODO : initiate successful logged in experience
//        val appContext = context?.applicationContext ?: return
        findNavController().navigate(SignFragmentDirections.actionSignFragmentToVerifFragment())
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