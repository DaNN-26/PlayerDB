package com.example.playerdb.components.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.example.playerdb.components.main.MainComponent.Child
import com.example.playerdb.components.main.details.RealDetailsComponent
import com.example.playerdb.components.main.entry.RealEntryComponent
import com.example.playerdb.components.main.profile.RealProfileComponent
import com.example.playerdb.network.domain.repository.KtorRepository
import kotlinx.serialization.Serializable
import javax.inject.Inject

class RealMainComponent @Inject constructor(
    private val componentContext: ComponentContext,
    private val ktorRepository: KtorRepository,
    private val onSignOutClick: () -> Unit,
) : MainComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Config.Entry,
            serializer = Config.serializer(),
            handleBackButton = true,
            childFactory = ::createChild
        )

    private fun createChild(
        config: Config,
        componentContext: ComponentContext,
    ): Child = when(config) {
        is Config.Entry -> Child.Entry(
            entryComponent = entryComponent(componentContext)
        )
        is Config.Details -> Child.Details(
            detailsComponent = detailsComponent(
                config = Config.Details(config.userId),
                componentContext = componentContext
            )
        )
        is Config.Profile -> Child.Profile(
            profileComponent = profileComponent(
                config = Config.Profile(config.userUrl),
                componentContext = componentContext
            )
        )
    }

    private fun entryComponent(componentContext: ComponentContext) =
        RealEntryComponent(
            componentContext = componentContext,
            onFindClick = { id ->
                navigation.push(Config.Details(userId = id))
            },
            onSignOutClick = onSignOutClick
        )

    private fun detailsComponent(
        config: Config.Details,
        componentContext: ComponentContext,
    ) =
        RealDetailsComponent(
            componentContext = componentContext,
            ktorRepository = ktorRepository,
            onProfileClick = { navigation.push(Config.Profile(userUrl = it))},
            userId = config.userId
        )

    private fun profileComponent(
        config: Config.Profile,
        componentContext: ComponentContext
    ) =
        RealProfileComponent(
            componentContext = componentContext,
            userUrl = config.userUrl
        )

    override fun navigateBack() =
        navigation.pop()


    @Serializable
    private sealed interface Config {
        @Serializable
        data object Entry : Config
        @Serializable
        data class Details(val userId: String) : Config
        @Serializable
        data class Profile(val userUrl: String) : Config
    }
}