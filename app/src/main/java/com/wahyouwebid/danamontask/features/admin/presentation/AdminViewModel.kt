package com.wahyouwebid.danamontask.features.admin.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.wahyouwebid.danamontask.core.entity.UserEntity
import com.wahyouwebid.danamontask.features.admin.domain.AdminUseCase
import com.wahyouwebid.danamontask.features.auth.domain.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val adminUseCase: AdminUseCase,
    private val authUseCase: AuthUseCase
): ViewModel() {

    val userList: MutableLiveData<PagingData<UserEntity>> by lazy { MutableLiveData() }

    val user: MutableLiveData<UserEntity> by lazy { MutableLiveData() }

    fun getUserList() {
        adminUseCase.getUsers(viewModelScope) {
            userList.postValue(it)
        }
    }

    fun getUserById(id: Int) {
        adminUseCase.getUserById(id) {
            user.postValue(it)
        }
    }

    fun updateUser(user: UserEntity) {
        adminUseCase.updateUser(user)
    }

    fun deleteUser(id: Int) {
        adminUseCase.deleteUser(id)
    }

    fun logout() {
        authUseCase.logout()
    }

}