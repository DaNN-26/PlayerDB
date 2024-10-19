package com.example.playerdb.components.auth.signUp

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.playerdb.mvi.auth.signUp.SignUpIntent
import com.example.playerdb.mvi.auth.signUp.SignUpState
import javax.inject.Inject

class RealSignUpComponent @Inject constructor(
    private val componentContext: ComponentContext,
    private val navigateToSignIn: () -> Unit,
) : SignUpComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume("SIGN_UP_COMPONENT", SignUpState.serializer()) ?: SignUpState()
    )

    override val state = _state

    override fun processIntent(intent: SignUpIntent) {
        when(intent) {
            is SignUpIntent.SignUpClick -> { signUp() }
            is SignUpIntent.EmailChanged -> { _state.update { it.copy(email = intent.email) } }
            is SignUpIntent.PasswordChanged -> { _state.update { it.copy(password = intent.password) } }
            is SignUpIntent.IsPasswordVisible -> { _state.update { it.copy(isPasswordVisible = intent.isPasswordVisible) } }
        }
    }

    private fun signUp() {
        navigateToSignIn()
    }
}