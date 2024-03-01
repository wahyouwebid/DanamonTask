package com.wahyouwebid.danamontask.common.base

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/

sealed class BaseResultState<out T : Any> {

    data class Success<out T : Any>(val data: T) : BaseResultState<T>()

    data class Error(val error: Throwable) : BaseResultState<Nothing>()

    object Loading : BaseResultState<Nothing>()

}