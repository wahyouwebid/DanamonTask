package com.wahyouwebid.danamontask.features.auth.domain.model

import com.wahyouwebid.danamontask.core.model.User

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/

data class LoginResult(
    val isSuccess: Boolean,
    val data: User? = null,
)
