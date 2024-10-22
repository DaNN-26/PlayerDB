package com.example.playerdb.mvi.main.details

import android.content.Intent
import com.example.playerdb.network.domain.model.SteamResponse
import kotlinx.serialization.Serializable

@Serializable
data class DetailsState(
    val user: SteamResponse = SteamResponse(),
    val isError: Boolean = false,
    val isLoading: Boolean = true
)
