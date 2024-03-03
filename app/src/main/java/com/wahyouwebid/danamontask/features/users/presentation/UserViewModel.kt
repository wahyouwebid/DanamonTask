package com.wahyouwebid.danamontask.features.users.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.wahyouwebid.danamontask.core.entity.PhotoEntity
import com.wahyouwebid.danamontask.features.auth.domain.AuthUseCase
import com.wahyouwebid.danamontask.features.users.domain.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val authUseCase: AuthUseCase
): ViewModel() {

    val photoList: MutableLiveData<PagingData<PhotoEntity>> by lazy { MutableLiveData() }

    fun getPhotoList() {
        userUseCase.getPhotos(viewModelScope) {
            photoList.postValue(it)
        }
    }

    fun logout() {
        authUseCase.logout()
    }
}