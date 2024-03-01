package com.wahyouwebid.danamontask.common.utils

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.View

/***********************************************************************************
 * Created by Ujang Wahyu
 * Email: wahyouwebid@gmail.com
 * Github: github.com/wahyouwebid
 * Linkedin: linkedin.com/in/wahyouwebid,
 ******************************************************************************************/

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

fun View.isVisibilityError(isSuccessful: Boolean) {
    visibility = if (isSuccessful) View.GONE else View.VISIBLE
}

fun getRegex() = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")