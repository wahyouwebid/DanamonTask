package com.wahyouwebid.danamontask.navigation

sealed class NavigationAction {
    object LoginToRegister : NavigationAction()
    object RegisterToLogin : NavigationAction()
}