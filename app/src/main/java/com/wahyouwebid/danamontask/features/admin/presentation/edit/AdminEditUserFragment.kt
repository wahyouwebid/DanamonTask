package com.wahyouwebid.danamontask.features.admin.presentation.edit

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.wahyouwebid.danamontask.R
import com.wahyouwebid.danamontask.common.base.BaseFragment
import com.wahyouwebid.danamontask.common.iukit.UikitPopup
import com.wahyouwebid.danamontask.common.utils.KeyBundle
import com.wahyouwebid.danamontask.common.utils.UserRole
import com.wahyouwebid.danamontask.common.utils.isVisibilityError
import com.wahyouwebid.danamontask.common.utils.parcelable
import com.wahyouwebid.danamontask.core.entity.UserEntity
import com.wahyouwebid.danamontask.databinding.FragmentAdminEditUserBinding
import com.wahyouwebid.danamontask.features.admin.presentation.AdminViewModel
import dagger.hilt.android.AndroidEntryPoint

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/

@AndroidEntryPoint
class AdminEditUserFragment: BaseFragment<FragmentAdminEditUserBinding>(FragmentAdminEditUserBinding::inflate){

    private val viewModel: AdminViewModel by viewModels()

    private val dataItem: UserEntity? by lazy {
        arguments?.parcelable(KeyBundle.DATA.name)
    }

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    private var userRole: Int = UserRole.USER.value

    private var myProfile: UserEntity? = null

    override fun setupView(savedInstanceState: Bundle?) {
        setupListener()
    }

    private fun setupListener() = with(binding) {
        ivBack.setOnClickListener {
            navigation?.navigateUp()
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

        btnDelete.setOnClickListener {
            UikitPopup(this@AdminEditUserFragment).showPopupVerification(myProfile) {
                viewModel.deleteUser(dataItem?.id ?: 0)
                navigation?.navigateUp()
            }
        }

        btnSave.setOnClickListener {
            editValidate()
        }
    }

    private fun editValidate() = with(binding){
        if (viewModel.isEditValidate()) {
            viewModel.updateUser(
                UserEntity(
                    id = dataItem?.id ?: 0,
                    username = etUsername.text.toString(),
                    email = etEmail.text.toString(),
                    role = userRole,
                    password = "",
                )
            )
            navigation?.navigateUp()
        } else {
            viewModel.validateEmail(etEmail.text.toString())
            viewModel.validateUsername(etUsername.text.toString())
        }
    }

    override fun setupViewModel() = with(binding){
        viewModel.getUserById(dataItem?.id ?: 0)
        viewModel.user.observe(viewLifecycleOwner) { user ->
            etEmail.setText(user.email)
            etUsername.setText(user.username)
            if (user.role == UserRole.USER.value) {
                rbUser.isChecked = true
                rbAdmin.isChecked = false
            } else {
                rbUser.isChecked = false
                rbAdmin.isChecked = true
            }
        }

        viewModel.getMyProfile()
        viewModel.myProfile.observe(viewLifecycleOwner) { user ->
            myProfile = user
        }

        viewModel.usernameValidationResult.observe(viewLifecycleOwner) { validationResult ->
            tvErrorUsername.isVisibilityError(validationResult.successful)
            tvErrorUsername.text = validationResult.errorMessage
        }

        viewModel.emailValidationResult.observe(viewLifecycleOwner) { validationResult ->
            tvErrorEmail.isVisibilityError(validationResult.successful)
            tvErrorEmail.text = validationResult.errorMessage
        }
    }
}