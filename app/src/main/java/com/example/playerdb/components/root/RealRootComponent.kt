package com.example.playerdb.components.root

import android.content.Intent
import android.content.SharedPreferences
import androidx.activity.result.ActivityResultLauncher
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushToFront
import com.arkivanov.decompose.value.Value
import com.example.playerdb.components.auth.RealAuthComponent
import com.example.playerdb.components.main.RealMainComponent
import com.example.playerdb.components.root.RootComponent.Child
import com.example.playerdb.firebase.auth.google.domain.GoogleSignInRepository
import com.example.playerdb.firebase.auth.signIn.domain.SignInRepository
import com.example.playerdb.firebase.auth.signUp.domain.SignUpRepository
import com.example.playerdb.network.domain.repository.KtorRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.serialization.Serializable
import javax.inject.Inject

class RealRootComponent @Inject constructor(
    private val componentContext: ComponentContext,
    private val ktorRepository: KtorRepository,
    private val sharedPreferences: SharedPreferences,
    private val firebaseAuth: FirebaseAuth,
    private val signUpRepository: SignUpRepository,
    private val signInRepository: SignInRepository,
    private val googleSignInRepository: GoogleSignInRepository,
    private val googleSignInLauncher: ActivityResultLauncher<Intent>
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            initialConfiguration =
            if(sharedPreferences.getBoolean(FIRST_LAUNCH, true))
                Config.Auth
            else
                Config.Main,
            serializer = Config.serializer(),
            handleBackButton = false,
            childFactory = ::createChild
        )

    private fun createChild(config: Config, componentContext: ComponentContext): Child =
        when(config){
            is Config.Auth -> Child.Auth(authComponent = authComponent(componentContext))
            is Config.Main -> Child.Main(mainComponent = mainComponent(componentContext))
        }

    private fun authComponent(componentContext: ComponentContext) =
        RealAuthComponent(
            componentContext = componentContext,
            signUpRepository = signUpRepository,
            signInRepository = signInRepository,
            onSignClick = {
                navigation.pushToFront(
                    Config.Main
                )
                sharedPreferences
                    .edit()
                    .putBoolean(FIRST_LAUNCH, false)
                    .apply()
            },
            googleSignInRepository = googleSignInRepository,
            googleSignInLauncher = googleSignInLauncher
        )

    private fun mainComponent(componentContext: ComponentContext) =
        RealMainComponent(
            componentContext = componentContext,
            ktorRepository = ktorRepository,
            onSignOutClick = {
                firebaseAuth.signOut()
                navigation.pushToFront(Config.Auth)
                sharedPreferences.edit().putBoolean(FIRST_LAUNCH, true).apply()
            }
        )


    @Serializable
    sealed interface Config {
        @Serializable
        data object Auth : Config
        @Serializable
        data object Main : Config
    }

    companion object {
        const val ROOT_APP_KEY = "ROOT_APP_KEY"
        const val FIRST_LAUNCH = "FIRST_LAUNCH"
    }
}