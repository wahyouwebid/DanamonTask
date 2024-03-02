package com.wahyouwebid.danamontask.common.iukit

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.wahyouwebid.danamontask.R
import com.wahyouwebid.danamontask.common.utils.isVisibility
import com.wahyouwebid.danamontask.core.entity.UserEntity
import com.wahyouwebid.danamontask.databinding.PopupDialogConfirmationBinding

class UikitPopup(private val fragment: Fragment) :
    Dialog(fragment.requireContext()) {

    fun showPopupVerification(
        dataItem: UserEntity?,
        isSuccess:() -> Unit
    ) {
        val dialog = fragment.context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(true)
        val binding: PopupDialogConfirmationBinding by lazy {
            PopupDialogConfirmationBinding.inflate(layoutInflater)
        }
        dialog?.setContentView(binding.root)
        val window: Window? = dialog?.window
        window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        with(binding) {
            etPassword.addTextChangedListener {
                if (etPassword.text.toString().isNotEmpty()) {
                    tvErrorPassword.isVisibility(false)
                } else {
                    tvErrorPassword.isVisibility(true)
                    tvErrorPassword.text = fragment.getString(R.string.title_password_cannot_empty)
                }
            }

            btnPositive.setOnClickListener {
                if (etPassword.text.toString().isNotEmpty()) {
                    if (dataItem?.password == etPassword.text.toString()) {
                        tvErrorPassword.isVisibility(false)
                        isSuccess.invoke()
                        dialog?.dismiss()
                    } else {
                        tvErrorPassword.isVisibility(true)
                        tvErrorPassword.text = fragment.getString(R.string.title_verification_failed)
                    }
                } else {
                    tvErrorPassword.isVisibility(true)
                    tvErrorPassword.text = fragment.getString(R.string.title_password_cannot_empty)
                }
            }
        }

        dialog?.show()
    }
}