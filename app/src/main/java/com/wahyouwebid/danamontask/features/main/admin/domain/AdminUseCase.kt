package com.wahyouwebid.danamontask.features.main.admin.domain

import androidx.paging.PagingData
import com.wahyouwebid.danamontask.core.entity.UserEntity
import kotlinx.coroutines.CoroutineScope

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/


interface AdminUseCase {

    fun getUsers(
        scope: CoroutineScope,
        callback: (PagingData<UserEntity>) -> Unit
    )

    fun getUserById(id: Int, callback: (UserEntity) -> Unit)

    fun updateUser(data: UserEntity)

    fun deleteUser(id: Int)

}