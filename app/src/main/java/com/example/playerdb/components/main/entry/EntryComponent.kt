package com.example.playerdb.components.main.entry

import com.arkivanov.decompose.value.Value
import com.example.playerdb.mvi.main.entry.EntryIntent
import com.example.playerdb.mvi.main.entry.EntryState

interface EntryComponent {

    val state: Value<EntryState>

    fun processIntent(intent: EntryIntent)
}