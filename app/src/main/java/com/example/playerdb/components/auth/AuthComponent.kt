package com.example.playerdb.components.auth

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.playerdb.components.auth.signIn.SignInComponent
import com.example.playerdb.components.auth.signUp.SignUpComponent

interface AuthComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class SignUp(val signUpComponent: SignUpComponent) : Child
        class SignIn(val signInComponent: SignInComponent) : Child
    }
}