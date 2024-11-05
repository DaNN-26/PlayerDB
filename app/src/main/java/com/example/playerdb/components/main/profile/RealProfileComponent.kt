package com.example.playerdb.components.main.profile

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.playerdb.mvi.main.profile.ProfileState
import javax.inject.Inject

class RealProfileComponent @Inject constructor(
    private val componentContext: ComponentContext,
    private val userUrl: String
) : ProfileComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume(PROFILE_COMPONENT, ProfileState.serializer()) ?: ProfileState()
    )

    init {
        _state.update { it.copy(url = userUrl) }
    }

    override val state = _state

    companion object {
        const val PROFILE_COMPONENT = "PROFILE_COMPONENT"
    }
}