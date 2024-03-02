package com.wahyouwebid.danamontask.navigation

import androidx.navigation.NavController
import com.wahyouwebid.danamontask.R

fun NavController.navigate(action: NavigationAction) {
    val direction = when (action) {
        is NavigationAction.LoginToRegister -> R.id.action_categoriesFragment_to_sourceFragment
        is NavigationAction.RegisterToLogin -> R.id.action_registerFragment_to_loginFragment
    }
    navigate(direction)
}