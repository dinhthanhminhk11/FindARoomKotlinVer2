package com.example.findaroomkotlinver2.ui.fragment.login

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.findaroomkotlinver2.R
import com.example.findaroomkotlinver2.base.BaseViewModelFragment
import com.example.findaroomkotlinver2.constant.AppConstant
import com.example.findaroomkotlinver2.databinding.FragmentHomeBinding
import com.example.findaroomkotlinver2.databinding.FragmentLoginBinding


class LoginFragment : BaseViewModelFragment<FragmentLoginBinding>() {
    private var isInitialized = false
    private var isLoading = false

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(editable: Editable?) {
            if (!isInitialized) {
                return
            }
            val input = editable.toString()
            val isValid = isPhoneNumberOrGmail(input)

            if (input.isNullOrEmpty()) {
                isValidate(
                    false,
                    resources.getString(R.string.textErrorUsername),
                    binding.username,
                    binding.textErrorUsername
                )
            } else if (isValid) {
                isValidate(
                    true, "", binding.username, binding.textErrorUsername
                )
            } else {
                isValidate(
                    false,
                    resources.getString(R.string.textErrorUsernameCheckEmailOrPhone),
                    binding.username,
                    binding.textErrorUsername
                )
            }
        }
    }
    private val textWatcherPassword = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(editable: Editable?) {

            if (!isInitialized) {
                return
            }

            val input = editable.toString()
            val isValid = isPasswordValid(input)

            if (input.isNullOrEmpty()) {
                isValidate(
                    false,
                    resources.getString(R.string.textErrorPassword),
                    binding.password,
                    binding.textErrorPassword
                )
            } else if (isValid) {
                isValidate(
                    true, "", binding.password, binding.textErrorPassword
                )
            } else {
                isValidate(
                    false,
                    resources.getString(R.string.textErrorPasswordValidate),
                    binding.password,
                    binding.textErrorPassword
                )
            }
        }
    }
    override fun initView() {
        setStatusBarStyle(AppConstant.TYPE_DARK, resources.getColor(R.color.purple))

        binding.username.addTextChangedListener(textWatcher)
        binding.password.addTextChangedListener(textWatcherPassword)
    }

    override fun initOnClickListener() {
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.username.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (binding.username.text.toString().isEmpty()) {
                    isValidate(
                        false,
                        resources.getString(R.string.textErrorUsername),
                        binding.username,
                        binding.textErrorUsername
                    )
                }
            } else {
                isValidate(
                    true, "", binding.username, binding.textErrorUsername
                )
            }
        }

        binding.password.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (binding.password.text.toString().isEmpty()) {
                    isValidate(
                        false,
                        resources.getString(R.string.textErrorPassword),
                        binding.password,
                        binding.textErrorPassword
                    )
                }
            } else {
                isValidate(
                    true, "", binding.password, binding.textErrorPassword
                )
            }
        }

        binding.btnLogin.setOnClickListener {
            if (binding.username.text.toString().isEmpty()) {
                isValidate(
                    false,
                    resources.getString(R.string.textErrorUsername),
                    binding.username,
                    binding.textErrorUsername
                )
            } else if (!isPhoneNumberOrGmail(binding.username.text.toString())) {
                isValidate(
                    false,
                    resources.getString(R.string.textErrorUsernameCheckEmailOrPhone),
                    binding.username,
                    binding.textErrorUsername
                )
            } else if (binding.password.text.toString().isEmpty()) {
                isValidate(
                    false,
                    resources.getString(R.string.textErrorPassword),
                    binding.password,
                    binding.textErrorPassword
                )
            } else if (!isPasswordValid(binding.password.text.toString())) {
                isValidate(
                    false,
                    resources.getString(R.string.textErrorPasswordValidate),
                    binding.password,
                    binding.textErrorPassword
                )
            } else {
                binding.btnLogin.isEnabled = false
            }
        }

        binding.forgotPassword.setOnClickListener {
//            findNavController().navigate(
////                R.id.action_loginFragment2_to_forgotPasswordFragment
//            )
        }
    }

    override fun observeLiveData() {

    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)


    private fun showProgressBar() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
        binding.btnLogin.isEnabled = false
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnLogin.isEnabled = true
    }

    override fun onResume() {
        super.onResume()
        isInitialized = true
    }

    override fun onPause() {
        super.onPause()
        isInitialized = false
    }

    private fun isValidate(
        check: Boolean, textError: String, editText: EditText, textViewError: TextView
    ) {
        textViewError.text = textError
        if (check) {
            editText.background = resources.getDrawable(R.drawable.background_edit_text_true)
        } else {
            editText.background = resources.getDrawable(R.drawable.background_edit_text_false)
        }
    }

    private fun isPhoneNumberOrGmail(input: String): Boolean {
        val phoneRegex = "^(03|05|07|08|09)+([0-9]{8})\\b".toRegex()
        val gmailRegex = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$".toRegex()
        return input.matches(phoneRegex) || input.matches(gmailRegex)
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordRegex =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,15}\$".toRegex()
        return password.matches(passwordRegex)
    }
}