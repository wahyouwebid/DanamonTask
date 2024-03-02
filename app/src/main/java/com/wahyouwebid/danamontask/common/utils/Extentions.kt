package com.wahyouwebid.danamontask.common.utils

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.wahyouwebid.danamontask.R

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

fun View.isVisibility(isShown: Boolean) {
    visibility = if (isShown) View.VISIBLE else View.GONE
}

fun getRegex() = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")

fun Throwable.logError(tag: String = "ERROR") {
    Log.e(tag, this.message ?: "Unknown error", this)
}

fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this.context)
        .load(imageUrl)
        .error(R.color.grey)
        .into(this)
}

fun String.splitString(): String {
    val parts = this.split(" ")
    return if (parts.size == 2) {
        val first = parts[0].first()
        val second = parts[1].first()
        "${first}${second}"
    } else {
        "${parts[0].first()}"
    }
}