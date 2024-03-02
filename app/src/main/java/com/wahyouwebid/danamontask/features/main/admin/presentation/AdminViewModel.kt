package com.wahyouwebid.danamontask.features.main.admin.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.wahyouwebid.danamontask.core.entity.UserEntity
import com.wahyouwebid.danamontask.features.main.admin.domain.AdminUseCase
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
    private val useCase: AdminUseCase
): ViewModel() {

    val userList: MutableLiveData<PagingData<UserEntity>> by lazy { MutableLiveData() }

    val user: MutableLiveData<UserEntity> by lazy { MutableLiveData() }

    fun getUserList() {
        useCase.getUsers(viewModelScope) {
            userList.postValue(it)
        }
    }

    fun getUserById(id: Int) {
        useCase.getUserById(id) {
            user.postValue(it)
        }
    }

    fun updateUser(user: UserEntity) {
        useCase.updateUser(user)
    }

    fun deleteUser(id: Int) {
        useCase.deleteUser(id)
    }

}