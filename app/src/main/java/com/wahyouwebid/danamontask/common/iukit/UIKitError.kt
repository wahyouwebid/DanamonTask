package com.wahyouwebid.danamontask.common.iukit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.wahyouwebid.danamontask.R
import com.wahyouwebid.danamontask.databinding.UikitErrorBinding

class UIKitError(
    context: Context,
    attributeSet: AttributeSet? = null,
) : ConstraintLayout(context, attributeSet) {

    private val binding: UikitErrorBinding = UikitErrorBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    fun setError(
        title: String? = null,
        message: String? = null,
        action:() -> Unit
    ) = with(binding) {
        tvTitleError.text = title ?: context.getString(R.string.title_error)
        tvMessageError.text = message ?: root.context.getString(R.string.title_error_des)
        btnRetry.setOnClickListener { action.invoke() }
    }
}