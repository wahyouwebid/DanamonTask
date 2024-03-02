package com.wahyouwebid.danamontask.features.auth.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wahyouwebid.danamontask.core.model.User
import com.wahyouwebid.danamontask.features.auth.domain.AuthUseCase
import com.wahyouwebid.danamontask.features.auth.domain.model.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val useCase: AuthUseCase
): ViewModel() {

    val isSuccessLogin: MutableLiveData<Boolean> by lazy { MutableLiveData() }

    val isSuccessRegister: MutableLiveData<Boolean> by lazy { MutableLiveData() }

    val emailValidationResult = MutableLiveData<ValidationResult>()

    val usernameValidationResult = MutableLiveData<ValidationResult>()

    val passwordValidationResult = MutableLiveData<ValidationResult>()


    fun login(email: String, password: String) {
        useCase.login(email, password) { isSuccessful ->
            isSuccessLogin.postValue(isSuccessful)
        }
    }

    fun register(user: User) {
        useCase.register(user) { isSuccessful ->
            isSuccessRegister.postValue(isSuccessful)
        }
    }

    fun validateEmail(email: String) {
        emailValidationResult.value = useCase.validateEmail(email)
    }

    fun validateUsername(username: String) {
        usernameValidationResult.value = useCase.validateUsername(username)
    }

    fun validatePassword(password: String) {
        passwordValidationResult.value = useCase.validatePassword(password)
    }

    fun clearValue() {
        isSuccessLogin.postValue(null)
        isSuccessRegister.postValue(null)
    }

    fun isLoginValidate() =
        emailValidationResult.value?.successful == true &&
        passwordValidationResult.value?.successful == true

    fun isRegisterValidate() =
        emailValidationResult.value?.successful == true &&
        passwordValidationResult.value?.successful == true &&
        usernameValidationResult.value?.successful == true
}