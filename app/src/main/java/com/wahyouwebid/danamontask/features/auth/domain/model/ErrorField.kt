package com.wahyouwebid.danamontask.features.auth.domain.model

enum class ErrorField(val message: String){
    EMAIL_EMPTY("Email cannot be empty"),
    EMAIL_INVALID("Email invalid format"),
    USERNAME_EMPTY("Username cannot be empty"),
    USERNAME_SHORT_LENGTH("Username number minimum 4 digits"),
    USERNAME_INVALID_CHAR("Username invalid character"),
    PASSWORD_EMPTY("Password cannot be empty"),
    PASSWORD_INVALID_LENGTH("Username number minimum 8 digits"),
}