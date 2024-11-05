package com.example.playerdb.mvi.auth.signUp

import kotlinx.serialization.Serializable

@Serializable
data class SignUpState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isError: Boolean = false
)
