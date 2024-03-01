package com.wahyouwebid.danamontask.features.auth.domain.model

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = ""
)
