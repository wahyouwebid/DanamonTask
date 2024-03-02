package com.wahyouwebid.danamontask.features.auth.domain

import com.wahyouwebid.danamontask.common.utils.getRegex
import com.wahyouwebid.danamontask.core.model.User
import com.wahyouwebid.danamontask.features.auth.domain.model.ErrorField
import com.wahyouwebid.danamontask.features.auth.domain.model.ValidationResult
import javax.inject.Inject

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/


class AuthInteractor @Inject constructor(
    private val repository: AuthRepository
): AuthUseCase {

    override fun login(email: String, password: String, isSuccess: (Boolean) -> Unit) {
        repository.login(email, password, isSuccess)
    }

    override fun register(data: User, isSuccess: (Boolean) -> Unit) {
        repository.register(data, isSuccess)
    }

    override fun validateEmail(email: String): ValidationResult {
        return when {
            email.isEmpty() -> {
                ValidationResult(false, ErrorField.EMAIL_EMPTY.message)
            }

            !email.matches(getRegex()) -> {
                ValidationResult(false, ErrorField.EMAIL_INVALID.message)
            }

            else -> {
                ValidationResult(true)
            }
        }
    }

    override fun validateUsername(username: String): ValidationResult {
        val minLength = 5

        return when {
            username.isEmpty() -> {
                ValidationResult(false, ErrorField.USERNAME_EMPTY.message)
            }
            username.length < minLength -> {
                ValidationResult(false, ErrorField.USERNAME_SHORT_LENGTH.message)
            }
            !username.matches(Regex("^[a-zA-Z0-9._-]*\$")) -> {
                ValidationResult(false, ErrorField.USERNAME_INVALID_CHAR.message)
            }
            else -> {
                ValidationResult(true)
            }
        }
    }

    override fun validatePassword(password: String): ValidationResult {
        return when {
            password.isEmpty() -> {
                ValidationResult(false, ErrorField.PASSWORD_EMPTY.message)
            }
            password.length < 9 -> {
                ValidationResult(false, ErrorField.PASSWORD_INVALID_LENGTH.message)
            }
            else -> {
                ValidationResult(true)
            }
        }
    }

}