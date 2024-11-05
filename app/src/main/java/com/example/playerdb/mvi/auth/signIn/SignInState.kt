package com.example.playerdb.mvi.auth.signIn

import kotlinx.serialization.Serializable

@Serializable
data class SignInState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isError: Boolean = false
)