package com.wahyouwebid.danamontask.navigation

sealed class NavigationAction {
    object LoginToRegister : NavigationAction()
    object RegisterToLogin : NavigationAction()
    object LoginToAdmin : NavigationAction()
    object LoginToUsers : NavigationAction()
    object AdminToLogin : NavigationAction()
    object UserToLogin : NavigationAction()
    object SplashToLogin : NavigationAction()
    object SplashToAdmin : NavigationAction()
    object SplashToUser : NavigationAction()
}