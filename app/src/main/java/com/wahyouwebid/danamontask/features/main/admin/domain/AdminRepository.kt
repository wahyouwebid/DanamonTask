package com.wahyouwebid.danamontask.features.main.admin.domain

import androidx.paging.Pager
import com.wahyouwebid.danamontask.core.entity.UserEntity

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/


interface AdminRepository {

    fun getUsers(): Pager<Int, UserEntity>

    fun getUsersById(id: Int, callback: (UserEntity) -> Unit)

    fun updateUser(data: UserEntity)

    fun deleteUser(id: Int)
}