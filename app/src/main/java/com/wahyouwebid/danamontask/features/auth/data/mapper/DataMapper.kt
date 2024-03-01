package com.wahyouwebid.danamontask.features.auth.data.mapper

import com.wahyouwebid.danamontask.features.auth.data.entity.UserEntity
import com.wahyouwebid.danamontask.features.auth.domain.model.User

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/


object DataMapper {

    fun UserEntity?.mapUserEntityToUser(): User? {
        return User(
            username = this?.username,
            role = this?.role,
            email = this?.email,
        )
    }

    fun User?.mapUserToUserEntity(): UserEntity {
        return UserEntity(
            username = this?.username ?: "",
            role = this?.role ?: 0,
            email = this?.email ?: "",
            password = this?.password ?: ""
        )
    }
}