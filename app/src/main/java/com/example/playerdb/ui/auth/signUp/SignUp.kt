package com.example.playerdb.ui.auth.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.playerdb.components.auth.signUp.SignUpComponent
import com.example.playerdb.mvi.auth.signUp.SignUpIntent
import com.example.playerdb.ui.components.ChangeTextField

@Composable
fun SignUp(
    component: SignUpComponent,
    contentPadding: PaddingValues
) {
    val state by component.state.subscribeAsState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(horizontal = 56.dp)
    ) {
        ChangeTextField(
            value = state.email,
            label = "Email",
            desc = "example@gmail.com",
            onValueChanged = {
                component.processIntent(SignUpIntent.EmailChanged(it))
            },
            isError = state.isError,
            isPassword = false,
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        )
        ChangeTextField(
            value = state.password,
            label = "Password",
            desc = "********",
            onValueChanged = {
                component.processIntent(SignUpIntent.PasswordChanged(it))
            },
            isError = state.isError,
            isPassword = true,
            keyboardType = KeyboardType.Password,
            isPasswordVisible = state.isPasswordVisible,
            onPasswordVisibleClick = { component.processIntent(SignUpIntent.IsPasswordVisible(!state.isPasswordVisible)) },
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            component.processIntent(SignUpIntent.GoogleSignInClick)
        }) {
            Text(text = "Sign Up")
        }
        Button(onClick = {
            component.processIntent(SignUpIntent.GoogleSignInClick)
        }) {
            Text(text = "Sign In With Google")
        }
        TextButton(onClick = {
            component.processIntent(SignUpIntent.NavigateToSignIn)
        }) {
            Text(
                text = "Already have an account?\nSign In",
                textAlign = TextAlign.Center
            )
        }
    }
}