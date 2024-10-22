package com.example.playerdb.components.auth

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.example.playerdb.components.auth.AuthComponent.Child
import com.example.playerdb.components.auth.signIn.RealSignInComponent
import com.example.playerdb.components.auth.signUp.RealSignUpComponent
import kotlinx.serialization.Serializable
import javax.inject.Inject

class RealAuthComponent @Inject constructor(
    private val componentContext: ComponentContext,
    private val navigateToMain: () -> Unit
) : AuthComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Config.SignUp,
            serializer = Config.serializer(),
            handleBackButton = true,
            childFactory = ::createChild
        )

    private fun createChild(
        config: Config,
        componentContext: ComponentContext,
    ): Child = when(config) {
        is Config.SignUp -> Child.SignUp(signUpComponent = signUpComponent(componentContext))
        is Config.SignIn -> Child.SignIn(signInComponent = signInComponent(componentContext))
    }

    private fun signUpComponent(componentContext: ComponentContext) =
        RealSignUpComponent(
            componentContext = componentContext,
            navigateToSignIn = { navigation.push(Config.SignIn) }
        )

    private fun signInComponent(componentContext: ComponentContext) =
        RealSignInComponent(
            componentContext = componentContext,
            navigateToMain = navigateToMain
        )

    @Serializable
    private sealed interface Config {
        @Serializable
        data object SignUp : Config
        @Serializable
        data object SignIn : Config
    }
}