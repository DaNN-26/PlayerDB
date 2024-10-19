package com.example.playerdb.components.main.details

import com.arkivanov.decompose.value.Value
import com.example.playerdb.mvi.main.details.DetailsIntent
import com.example.playerdb.mvi.main.details.DetailsState

interface DetailsComponent {

    val state: Value<DetailsState>

    fun processIntent(intent: DetailsIntent)
}