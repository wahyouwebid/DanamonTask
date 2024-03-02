package com.wahyouwebid.danamontask.navigation

import androidx.navigation.NavController
import com.wahyouwebid.danamontask.R

fun NavController.navigate(action: NavigationAction) {
    val direction = when (action) {
        is NavigationAction.LoginToRegister -> R.id.action_categoriesFragment_to_sourceFragment
        is NavigationAction.RegisterToLogin -> R.id.action_registerFragment_to_loginFragment
        is NavigationAction.LoginToAdmin -> R.id.action_loginFragment_to_adminFragment
        is NavigationAction.LoginToUsers -> R.id.action_loginFragment_to_usersFragment
        is NavigationAction.AdminToLogin -> R.id.action_adminFragment_to_loginFragment
        is NavigationAction.UserToLogin -> R.id.action_usersFragment_to_loginFragment
        is NavigationAction.SplashToLogin -> R.id.action_splashFragment_to_loginFragment
        is NavigationAction.SplashToAdmin -> R.id.action_splashFragment_to_adminFragment
        is NavigationAction.SplashToUser -> R.id.action_splashFragment_to_usersFragment
    }
    navigate(direction)
}