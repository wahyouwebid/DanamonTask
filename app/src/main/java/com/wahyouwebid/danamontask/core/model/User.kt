package com.wahyouwebid.danamontask.core.model

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/

data class User (
    val id : Int = 0,
    val username: String?,
    val role: Int?,
    val email: String?,
    val password: String? = ""
)