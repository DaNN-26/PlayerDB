package com.example.playerdb.mvi.auth.signUp

sealed class SignUpIntent {
    class EmailChanged(val email: String) : SignUpIntent()
    class PasswordChanged(val password: String) : SignUpIntent()
    class IsPasswordVisible(val isPasswordVisible: Boolean) : SignUpIntent()
    data object SignUpClick : SignUpIntent()
}