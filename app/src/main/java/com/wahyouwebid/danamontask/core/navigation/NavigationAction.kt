package com.wahyouwebid.danamontask.core.navigation

sealed class NavigationAction {
    object LoginToRegister : NavigationAction()
    object RegisterToLogin : NavigationAction()
}