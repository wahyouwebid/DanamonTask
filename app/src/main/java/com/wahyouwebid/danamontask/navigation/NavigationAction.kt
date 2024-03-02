package com.wahyouwebid.danamontask.navigation

sealed class NavigationAction {
    object SplashToLogin : NavigationAction()
    object SplashToAdmin : NavigationAction()
    object SplashToUser : NavigationAction()
    object LoginToRegister : NavigationAction()
    object LoginToAdmin : NavigationAction()
    object LoginToUsers : NavigationAction()
    object RegisterToLogin : NavigationAction()
    object UserToLogin : NavigationAction()
    object AdminToLogin : NavigationAction()
    object AdminToEdit : NavigationAction()
}