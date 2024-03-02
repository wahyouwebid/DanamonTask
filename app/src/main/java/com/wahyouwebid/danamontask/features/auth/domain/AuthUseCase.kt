package com.wahyouwebid.danamontask.features.auth.domain

import com.wahyouwebid.danamontask.core.model.User
import com.wahyouwebid.danamontask.features.auth.domain.model.ValidationResult

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/


interface AuthUseCase {

    fun login(
        email: String,
        password: String,
        isSuccess: (Boolean) -> Unit
    )

    fun register(
        data: User,
        isSuccess: (Boolean) -> Unit
    )

    fun validateEmail(email: String): ValidationResult

    fun validateUsername(username: String): ValidationResult

    fun validatePassword(password: String): ValidationResult
}