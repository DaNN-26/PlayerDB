package com.example.playerdb.components.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.example.playerdb.network.domain.repository.KtorRepository
import com.example.playerdb.components.auth.RealAuthComponent
import com.example.playerdb.components.main.RealMainComponent
import com.example.playerdb.components.root.RootComponent.Child
import kotlinx.serialization.Serializable
import javax.inject.Inject

class RealRootComponent @Inject constructor(
    private val componentContext: ComponentContext,
    private val ktorRepository: KtorRepository
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Config.Auth,
            serializer = Config.serializer(),
            handleBackButton = false,
            childFactory = ::createChild
        )

    private fun createChild(config: Config, componentContext: ComponentContext): Child =
        when(config){
            Config.Auth -> Child.Auth(authComponent = authComponent(componentContext))
            Config.Main -> Child.Main(mainComponent = mainComponent(componentContext))
        }

    private fun authComponent(componentContext: ComponentContext) =
        RealAuthComponent(
            componentContext = componentContext,
            navigateToMain = { navigation.push(Config.Main)}
        )

    private fun mainComponent(componentContext: ComponentContext) =
        RealMainComponent(
            componentContext = componentContext,
            ktorRepository = ktorRepository
        )

    @Serializable
    sealed interface Config {
        @Serializable
        data object Auth : Config
        @Serializable
        data object Main : Config
    }
}