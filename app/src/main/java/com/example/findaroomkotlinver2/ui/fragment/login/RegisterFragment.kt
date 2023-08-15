package com.example.findaroomkotlinver2.ui.fragment.login

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.findaroomkotlinver2.R
import com.example.findaroomkotlinver2.base.BaseViewModelFragment
import com.example.findaroomkotlinver2.constant.AppConstant
import com.example.findaroomkotlinver2.databinding.FragmentRegisterBinding


class RegisterFragment : BaseViewModelFragment<FragmentRegisterBinding>() {
    private var isLoading = false

    private var isInitialized = false
    override fun initView() {
        setStatusBarStyle(AppConstant.TYPE_DARK, resources.getColor(R.color.purple))

        binding.phone.addTextChangedListener(object : TextWatcher {
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
                        true, "", binding.phone, binding.textErrorPhone
                    )
                } else if (isValid) {
                    isValidate(
                        true, "", binding.phone, binding.textErrorPhone
                    )
                } else {
                    isValidate(
                        false,
                        resources.getString(R.string.textErrorPhone),
                        binding.phone,
                        binding.textErrorPhone
                    )
                }
            }
        })

        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (!isInitialized) {
                    return
                }
                val input = s.toString()
                val isValid = isPhoneNumberOrGmail(input)

                if (input.isNullOrEmpty()) {
                    isValidate(
                        true, "", binding.email, binding.textErrorEmail
                    )
                } else if (isValid) {
                    isValidate(
                        true, "", binding.email, binding.textErrorEmail
                    )
                } else {
                    isValidate(
                        false,
                        resources.getString(R.string.textErrorEmail),
                        binding.email,
                        binding.textErrorEmail
                    )
                }
            }
        })

        binding.password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
                getPasswordStatus(password);
            }

        })

        binding.passwordAgain.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.password.text.toString() != binding.passwordAgain.text.toString()) {
                    isValidate(
                        false,
                        resources.getString(R.string.textErrorPassAgain),
                        binding.passwordAgain,
                        binding.textErrorPasswordAgain
                    )
                } else {
                    isValidate(
                        true, "", binding.passwordAgain, binding.textErrorPasswordAgain
                    )
                }
            }

        })
    }

    override fun initOnClickListener() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.phone.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                isValidate(
                    false,
                    resources.getString(R.string.textErrorNotPhone),
                    binding.phone,
                    binding.textErrorPhone
                )
            } else {
                isValidate(
                    true, "", binding.phone, binding.textErrorPhone
                )
            }
        }

        binding.email.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                isValidate(
                    false,
                    resources.getString(R.string.textErrorNotEmail),
                    binding.email,
                    binding.textErrorEmail
                )
            } else {
                isValidate(
                    true, "", binding.email, binding.textErrorEmail
                )
            }
        }

        binding.password.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                isValidate(
                    false, "", binding.password, binding.textErrorPhone
                )
            } else {
                isValidate(
                    true, "", binding.password, binding.textErrorPhone
                )
            }
        }

        binding.passwordAgain.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                isValidate(
                    false,
                    resources.getString(R.string.pleasePassWord),
                    binding.passwordAgain,
                    binding.textErrorPasswordAgain
                )
            } else {
                isValidate(
                    true, "", binding.passwordAgain, binding.textErrorPasswordAgain
                )
            }
        }

        binding.btnRegister.setOnClickListener {
            if (binding.phone.text.toString().isEmpty()) {
                isValidate(
                    false,
                    resources.getString(R.string.textErrorNotPhone),
                    binding.phone,
                    binding.textErrorPhone
                )
            } else if (!isPhoneNumberOrGmail(binding.phone.text.toString())) {
                isValidate(
                    false,
                    resources.getString(R.string.textErrorPhone),
                    binding.phone,
                    binding.textErrorPhone
                )
            } else if (binding.email.text.toString().isEmpty()) {
                isValidate(
                    false,
                    resources.getString(R.string.textErrorNotEmail),
                    binding.email,
                    binding.textErrorEmail
                )
            } else if (!isPhoneNumberOrGmail(binding.email.text.toString())) {
                isValidate(
                    false,
                    resources.getString(R.string.textErrorEmail),
                    binding.email,
                    binding.textErrorEmail
                )
            } else if (!isPasswordValid(binding.password.text.toString())) {
                isValidate(
                    false, "", binding.password, binding.textErrorPhone
                )
            } else if (binding.password.text.toString() != binding.passwordAgain.text.toString()) {
                isValidate(
                    false,
                    resources.getString(R.string.textErrorPassAgain),
                    binding.passwordAgain,
                    binding.textErrorPasswordAgain
                )
            } else if (!binding.checkBoxPolicy.isChecked) {
                Toast.makeText(
                    requireActivity(),
                    resources.getString(R.string.policyNotCheck),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                showProgressBar()
//                viewModel.register(
//                    BodyRegister(
//                        binding.email.text.toString(),
//                        binding.phone.text.toString(),
//                        binding.passwordAgain.text.toString(),
//                        ""
//                    )
//                )
            }
        }
    }

    override fun observeLiveData() {

    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegisterBinding = FragmentRegisterBinding.inflate(layoutInflater)

    override fun onResume() {
        super.onResume()
        isInitialized = true
    }

    override fun onPause() {
        super.onPause()
        isInitialized = false
    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
        binding.btnRegister.isEnabled = false
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnRegister.isEnabled = true
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

    private fun getPasswordStatus(password: String) {
        val uppercasePattern = Regex(".*[A-Z].*")
        if (!password.contains(uppercasePattern)) {
            binding.checkUppercase.setImageResource(R.drawable.check_register_false)
            binding.textCheckUppercase.setTextColor(resources.getColor(R.color.red))
        } else {
            binding.checkUppercase.setImageResource(R.drawable.check_register_true)
            binding.textCheckUppercase.setTextColor(resources.getColor(R.color.purple))
        }

        val alphanumericPattern =
            Regex("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]*\$")
        if (!password.matches(alphanumericPattern)) {
            binding.checkNumber.setImageResource(R.drawable.check_register_false)
            binding.textCheckNumber.setTextColor(resources.getColor(R.color.red))
        } else {
            binding.checkNumber.setImageResource(R.drawable.check_register_true)
            binding.textCheckNumber.setTextColor(resources.getColor(R.color.purple))

        }

        if (password.length < 8 || password.length > 15) {
            binding.checkLength.setImageResource(R.drawable.check_register_false)
            binding.textCheckLength.setTextColor(resources.getColor(R.color.red))

        } else {
            binding.checkLength.setImageResource(R.drawable.check_register_true)
            binding.textCheckLength.setTextColor(resources.getColor(R.color.purple))

        }
    }
}