package com.wahyouwebid.danamontask.features.auth.presentation.register

import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.wahyouwebid.danamontask.R
import com.wahyouwebid.danamontask.common.base.BaseFragment
import com.wahyouwebid.danamontask.common.utils.UserRole
import com.wahyouwebid.danamontask.common.utils.isVisibilityError
import com.wahyouwebid.danamontask.core.model.User
import com.wahyouwebid.danamontask.databinding.FragmentRegisterBinding
import com.wahyouwebid.danamontask.features.auth.presentation.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/

@AndroidEntryPoint
class RegisterFragment: BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: AuthViewModel by viewModels()

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    private var userRole: Int = UserRole.USER.value

    override fun setupView(savedInstanceState: Bundle?) {
        setupListener()
    }

    private fun setupListener() = with(binding){
        ivBack.setOnClickListener {
            navigation?.navigateUp()
        }

        btnRegister.setOnClickListener {
            registerValidate()
        }

        rbRole.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_user -> {
                    userRole = UserRole.USER.value
                }
                R.id.rb_admin -> {
                    userRole = UserRole.ADMIN.value
                }
            }
        }

        etEmail.addTextChangedListener {
            viewModel.validateEmail(it.toString())
        }

        etUsername.addTextChangedListener {
            viewModel.validateUsername(it.toString())
        }

        etPassword.addTextChangedListener {
            viewModel.validatePassword(it.toString())
        }
    }

    private fun registerValidate() = with(binding){
        if (viewModel.isRegisterValidate()) {
            viewModel.register(
                User(
                    username = etUsername.text.toString(),
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString(),
                    role = userRole
                )
            )
        } else {
            viewModel.validateEmail(etEmail.text.toString())
            viewModel.validateUsername(etUsername.text.toString())
            viewModel.validatePassword(etPassword.text.toString())
        }
    }

    override fun setupViewModel() = with(binding) {
        viewModel.isSuccessRegister.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                navigation?.navigateUp()
            } else {
                setError(getString(R.string.title_register_error))
            }
        }

        viewModel.usernameValidationResult.observe(viewLifecycleOwner) { validationResult ->
            tvErrorUsername.isVisibilityError(validationResult.successful)
            tvErrorUsername.text = validationResult.errorMessage
        }

        viewModel.emailValidationResult.observe(viewLifecycleOwner) { validationResult ->
            tvErrorEmail.isVisibilityError(validationResult.successful)
            tvErrorEmail.text = validationResult.errorMessage
        }

        viewModel.passwordValidationResult.observe(viewLifecycleOwner) { validationResult ->
            tvErrorPassword.isVisibilityError(validationResult.successful)
            tvErrorPassword.text = validationResult.errorMessage
        }

    }

    private fun setError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}