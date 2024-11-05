package com.example.playerdb.components.main.entry

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.playerdb.mvi.main.entry.EntryIntent
import com.example.playerdb.mvi.main.entry.EntryState
import javax.inject.Inject

class RealEntryComponent @Inject constructor(
    private val componentContext: ComponentContext,
    private val onFindClick: (String) -> Unit,
    private val onSignOutClick: () -> Unit
) : EntryComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume(ENTRY_COMPONENT, EntryState.serializer()) ?: EntryState()
    )

    override val state = _state

    override fun processIntent(intent: EntryIntent) {
        when(intent) {
            is EntryIntent.OnIdChange -> { _state.update { it.copy(id = intent.id) } }
            is EntryIntent.FindClick -> onFindClick(state.value.id)
            is EntryIntent.SignOutClick -> onSignOutClick()
        }
    }

    companion object {
        const val ENTRY_COMPONENT = "ENTRY_COMPONENT"
    }
}