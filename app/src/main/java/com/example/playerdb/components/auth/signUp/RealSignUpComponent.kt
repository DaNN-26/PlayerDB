package com.example.playerdb.components.auth.signUp

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.playerdb.firebase.auth.google.domain.GoogleSignInRepository
import com.example.playerdb.firebase.auth.signUp.domain.SignUpRepository
import com.example.playerdb.mvi.auth.signUp.SignUpIntent
import com.example.playerdb.mvi.auth.signUp.SignUpState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class RealSignUpComponent @Inject constructor(
    private val componentContext: ComponentContext,
    private val navigateToSignIn: () -> Unit,
    private val signUpRepository: SignUpRepository,
    private val googleSignInRepository: GoogleSignInRepository,
    private val googleSignInLauncher: ActivityResultLauncher<Intent>,
    private val onSignClick: () -> Unit
) : SignUpComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume(SIGN_UP_COMPONENT, SignUpState.serializer()) ?: SignUpState()
    )

    override val state = _state

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun processIntent(intent: SignUpIntent) {
        when(intent) {
            is SignUpIntent.SignUpClick -> { signUp() }
            is SignUpIntent.GoogleSignInClick -> { signInWithGoogle() }
            is SignUpIntent.NavigateToSignIn -> { navigateToSignIn() }
            is SignUpIntent.EmailChanged -> { _state.update { it.copy(email = intent.email) } }
            is SignUpIntent.PasswordChanged -> { _state.update { it.copy(password = intent.password) } }
            is SignUpIntent.IsPasswordVisible -> { _state.update { it.copy(isPasswordVisible = intent.isPasswordVisible) } }
        }
    }

    private fun signUp() {
        scope.launch {
            try {
                signUpRepository.signUp(
                    email = state.value.email,
                    password = state.value.password
                )
                state.update {
                    it.copy(isError = false)
                }
                navigateToSignIn()
            } catch (e: Exception) {
                Log.d("Error", e.message.toString())
                state.update { it.copy(isError = true)}
            }
        }
    }
    private fun signInWithGoogle() {
        val signInIntent = googleSignInRepository.getSignInIntent()
        googleSignInLauncher.launch(signInIntent)
        onSignClick()
    }

    companion object {
        const val SIGN_UP_COMPONENT = "SIGN_UP_COMPONENT"
    }
}