package com.wahyouwebid.danamontask.features.auth.presentation.login

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
import com.wahyouwebid.danamontask.databinding.FragmentLoginBinding
import com.wahyouwebid.danamontask.features.auth.domain.model.LoginResult
import com.wahyouwebid.danamontask.features.auth.presentation.AuthViewModel
import com.wahyouwebid.danamontask.navigation.NavigationAction
import com.wahyouwebid.danamontask.navigation.navigate
import dagger.hilt.android.AndroidEntryPoint

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/

@AndroidEntryPoint
class LoginFragment: BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: AuthViewModel by viewModels()

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        setupListener()
    }

    private fun setupListener() = with(binding) {
        btnLogin.setOnClickListener {
            loginValidate()
        }

        btnRegister.setOnClickListener {
            navigation?.navigate(NavigationAction.LoginToRegister)
            viewModel.clearValue()
        }

        etEmail.addTextChangedListener {
            viewModel.validateEmail(it.toString())
        }

        etPassword.addTextChangedListener {
            viewModel.validatePassword(it.toString())
        }

    }

    private fun loginValidate() = with(binding) {
        if (viewModel.isLoginValidate()) {
            viewModel.login(etEmail.text.toString(), etPassword.text.toString())
        } else {
            viewModel.validateEmail(etEmail.text.toString())
            viewModel.validatePassword(etPassword.text.toString())
        }
    }

    override fun setupViewModel() = with(binding){
        viewModel.isSuccessLogin.observe(viewLifecycleOwner) { result ->
            if (result != null) {
                checkUserRole(result)
            }
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

    private fun checkUserRole(result: LoginResult) {
        if (result.isSuccess) {
            if (result.data?.role == UserRole.ADMIN.value) {
                navigation?.navigate(NavigationAction.LoginToAdmin)
            } else {
                navigation?.navigate(NavigationAction.LoginToUsers)
            }
        } else {
            setError(getString(R.string.title_register_login))
        }
    }

    private fun setError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}