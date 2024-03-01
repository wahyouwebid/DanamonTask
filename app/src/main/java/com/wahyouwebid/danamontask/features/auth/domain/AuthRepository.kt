package com.wahyouwebid.danamontask.features.auth.domain

import com.wahyouwebid.danamontask.features.auth.domain.model.User

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/


interface AuthRepository {

    fun login(
        email: String,
        password: String,
        isSuccess: (Boolean) -> Unit
    )

    fun register(
        data: User,
        isSuccess: (Boolean) -> Unit
    )

}