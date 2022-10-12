package com.example.connect.presentation.login.ui.login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.connect.R
import com.example.connect.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        binding.include2.backImage.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.lifecycleOwner = this.viewLifecycleOwner

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)
            .get(LoginViewModel::class.java)

        val usernameEditText = binding.email
        val passwordEditText = binding.password
        val loginButton = binding.login
        val loadingProgressBar = binding.loading

        viewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                loginButton.isEnabled = loginFormState.isDataValid

                loginFormState.usernameError?.let {
                    usernameEditText.error = getString(it)
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
                viewModel.loginDataChanged(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            viewModel.login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )

            loginButton.hideKeyboard()

            viewModel.loginResult.observe(viewLifecycleOwner, {

                if (null != it) {
                    saveData()

                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainAppActivity())
                    requireActivity().finish()
                } else {
                    viewModel.messageLogin.observe(viewLifecycleOwner, {
                        when {
                            it.equals("Tolong Masukkan akun yang sesuai") -> {
                                showLoginFailed("Masukan akun yang sesuai")
                                loadingProgressBar.visibility = View.GONE
                                viewModel.messageComplete()
                            }
                            it.equals("Akun belum diverifikasi") -> {
                                updateUiWithUser()
                                loadingProgressBar.visibility = View.GONE
                            }
                        }
                    })
                }
            })


        }
    }

    private fun updateUiWithUser() {
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToVerifFragment())
        viewModel.messageComplete()
    }

    fun View.hideKeyboard() {
        val inputMethodManager = context?.getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager

        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun showLoginFailed(errorString: String) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_SHORT).show()
    }

    private fun saveData() {
        return requireActivity()
            .getSharedPreferences("my_data_pref", Context.MODE_PRIVATE).edit()
            .putString("token", viewModel.loginResult.value!!.data.token)
            .putString("token_type", viewModel.loginResult.value!!.data.token_type)
            .putInt("id", viewModel.loginResult.value!!.data.user.id!!)
            .putString("name", viewModel.loginResult.value!!.data.user.name)
            .putString(
                "email_verified_at",
                viewModel.loginResult.value!!.data.user.email_verified_at
            )
            .putString("status", viewModel.loginResult.value!!.data.user.status)
            .putString("created_at", viewModel.loginResult.value!!.data.user.created_at)
            .putString("updated_at", viewModel.loginResult.value!!.data.user.updated_at)
            .apply()
    }
}