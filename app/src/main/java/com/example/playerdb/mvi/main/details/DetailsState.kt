package com.example.playerdb.mvi.main.details

import com.example.model.SteamResponse
import kotlinx.serialization.Serializable

@Serializable
data class DetailsState(
    val user: SteamResponse = SteamResponse()
)
