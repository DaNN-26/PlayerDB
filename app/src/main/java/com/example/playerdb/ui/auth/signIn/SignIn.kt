package com.example.playerdb.ui.auth.signIn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
import com.example.playerdb.components.auth.signIn.SignInComponent
import com.example.playerdb.mvi.auth.signIn.SignInIntent
import com.example.playerdb.ui.components.ChangeTextField
import com.example.playerdb.ui.components.TopBar

@Composable
fun SignIn(
    component: SignInComponent
) {
    val state by component.state.subscribeAsState()

    Scaffold(
        topBar = { TopBar(title = "Authorization") },
        modifier = Modifier.fillMaxSize()
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
                    component.processIntent(SignInIntent.EmailChanged(it))
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
                    component.processIntent(SignInIntent.PasswordChanged(it))
                },
                isPassword = true,
                keyboardType = KeyboardType.Password,
                isPasswordVisible = state.isPasswordVisible,
                onPasswordVisibleClick = { component.processIntent(SignInIntent.IsPasswordVisible(!state.isPasswordVisible))}
            )
            Button(onClick = {
                component.processIntent(SignInIntent.SignInClick)
            }) {
                Text(text = "Sign In")
            }
        }
    }
}