package com.wahyouwebid.danamontask.navigation

import android.os.Bundle
import androidx.navigation.NavController
import com.wahyouwebid.danamontask.R

fun NavController.navigate(action: NavigationAction, dataBundle: Bundle? = null) {
    val direction = when (action) {
        is NavigationAction.SplashToLogin -> R.id.action_splashFragment_to_loginFragment
        is NavigationAction.SplashToAdmin -> R.id.action_splashFragment_to_adminFragment
        is NavigationAction.SplashToUser -> R.id.action_splashFragment_to_usersFragment
        is NavigationAction.LoginToRegister -> R.id.action_categoriesFragment_to_sourceFragment
        is NavigationAction.LoginToAdmin -> R.id.action_loginFragment_to_adminFragment
        is NavigationAction.LoginToUsers -> R.id.action_loginFragment_to_usersFragment
        is NavigationAction.RegisterToLogin -> R.id.action_registerFragment_to_loginFragment
        is NavigationAction.UserToLogin -> R.id.action_usersFragment_to_loginFragment
        is NavigationAction.AdminToLogin -> R.id.action_adminFragment_to_loginFragment
        is NavigationAction.AdminToEdit -> {
            val direction = R.id.action_adminFragment_to_adminEditUserFragment
            dataBundle?.let { navigate(direction, it) } ?: navigate(direction)
            return
        }

    }
    navigate(direction)
}