package com.example.playerdb.mvi.main.profile

import kotlinx.serialization.Serializable

@Serializable
data class ProfileState(
    val url: String = ""
)