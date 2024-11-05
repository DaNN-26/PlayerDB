package com.example.playerdb.components.auth.signIn

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.playerdb.firebase.auth.signIn.domain.SignInRepository
import com.example.playerdb.mvi.auth.signIn.SignInIntent
import com.example.playerdb.mvi.auth.signIn.SignInState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class RealSignInComponent @Inject constructor(
    private val componentContext: ComponentContext,
    private val onSignClick: () -> Unit,
    private val navigateToSignUp: () -> Unit,
    private val signInRepository: SignInRepository
) : SignInComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume(SIGN_IN_COMPONENT, SignInState.serializer()) ?: SignInState()
    )

    override val state = _state

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun processIntent(intent: SignInIntent) {
        when(intent) {
            is SignInIntent.SignInClick -> { signIn() }
            is SignInIntent.NavigateToSignUp -> { navigateToSignUp() }
            is SignInIntent.EmailChanged -> { _state.update { it.copy(email = intent.email) } }
            is SignInIntent.PasswordChanged -> { _state.update { it.copy(password = intent.password) } }
            is SignInIntent.IsPasswordVisible -> { _state.update { it.copy(isPasswordVisible = intent.isPasswordVisible) } }
        }
    }

    private fun signIn() {
        scope.launch {
            try {
                val user = signInRepository.signIn(
                    email = state.value.email,
                    password = state.value.password
                )
                if (user != null) {
                    state.update {
                        it.copy(
                            email = user.email.orEmpty(),
                            isError = false
                        )
                    }
                }
                onSignClick()
            } catch (e: Exception) {
                Log.d("Error", e.message.toString())
                state.update { it.copy(isError = true)}
            }
        }
    }

    companion object {
        const val SIGN_IN_COMPONENT = "SIGN_IN_COMPONENT"
    }
}