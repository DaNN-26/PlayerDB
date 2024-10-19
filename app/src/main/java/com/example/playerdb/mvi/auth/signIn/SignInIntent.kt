package com.example.playerdb.mvi.auth.signIn

sealed class SignInIntent {
    class EmailChanged(val email: String) : SignInIntent()
    class PasswordChanged(val password: String) : SignInIntent()
    class IsPasswordVisible(val isPasswordVisible: Boolean) : SignInIntent()
    data object SignInClick : SignInIntent()
}