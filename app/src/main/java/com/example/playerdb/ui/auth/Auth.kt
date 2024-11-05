package com.example.playerdb.ui.auth

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.example.playerdb.components.auth.AuthComponent
import com.example.playerdb.components.auth.AuthComponent.Child
import com.example.playerdb.ui.auth.signIn.SignIn
import com.example.playerdb.ui.auth.signUp.SignUp
import com.example.playerdb.ui.components.TopBar

@Composable
fun Auth(
    component: AuthComponent
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Children(
            stack = component.stack,
            animation = stackAnimation(fade() + scale())
        ) { child ->
            Scaffold(
                topBar = {
                    TopBar(
                        title = when(child.instance) {
                            is Child.SignUp -> "Sign Up"
                            is Child.SignIn -> "Sign In"
                        }
                    )
                },
                modifier = Modifier.fillMaxSize()
            ) { contentPadding ->
                when (val instance = child.instance) {
                    is Child.SignUp -> SignUp(
                        component = instance.signUpComponent,
                        contentPadding = contentPadding
                    )
                    is Child.SignIn -> SignIn(
                        component = instance.signInComponent,
                        contentPadding = contentPadding
                    )
                }
            }
        }
    }
}