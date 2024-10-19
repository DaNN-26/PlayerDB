package com.example.playerdb.components.auth.signIn

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.playerdb.mvi.auth.signIn.SignInIntent
import com.example.playerdb.mvi.auth.signIn.SignInState
import javax.inject.Inject

class RealSignInComponent @Inject constructor(
    private val componentContext: ComponentContext,
    private val navigateToMain: () -> Unit,
) : SignInComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume("SIGN_IN_COMPONENT", SignInState.serializer()) ?: SignInState()
    )

    override val state = _state

    override fun processIntent(intent: SignInIntent) {
        when(intent) {
            is SignInIntent.SignInClick -> { signIn() }
            is SignInIntent.EmailChanged -> { _state.update { it.copy(email = intent.email) } }
            is SignInIntent.PasswordChanged -> { _state.update { it.copy(password = intent.password) } }
            is SignInIntent.IsPasswordVisible -> { _state.update { it.copy(isPasswordVisible = intent.isPasswordVisible) } }
        }
    }

    private fun signIn() {
        navigateToMain()
    }
}