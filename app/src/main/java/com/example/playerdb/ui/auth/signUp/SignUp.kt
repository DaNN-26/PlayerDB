package com.example.playerdb.ui.auth.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.playerdb.components.auth.signUp.SignUpComponent
import com.example.playerdb.mvi.auth.signUp.SignUpIntent
import com.example.playerdb.ui.components.ChangeTextField
import com.example.playerdb.ui.components.TopBar

@Composable
fun SignUp(
    component: SignUpComponent
) {
    val state by component.state.subscribeAsState()

    Scaffold(
        topBar = { TopBar(title = "Registration")},
        modifier = Modifier.fillMaxSize(),
    ) { contentPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            ChangeTextField(
                value = state.email,
                label = "Email",
                desc = "example@gmail.com",
                onValueChanged = {
                    component.processIntent(SignUpIntent.EmailChanged(it))
                },
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
                isPassword = true,
                keyboardType = KeyboardType.Password,
                isPasswordVisible = state.isPasswordVisible,
                onPasswordVisibleClick = { component.processIntent(SignUpIntent.IsPasswordVisible(!state.isPasswordVisible))}
            )
            Button(onClick = {
                component.processIntent(SignUpIntent.SignUpClick)
            }) {
                Text(text = "Sign Up")
            }
        }
    }
}