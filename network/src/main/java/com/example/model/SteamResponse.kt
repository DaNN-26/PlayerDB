package com.example.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SteamResponse(
    val username: String = "",
    val id: String = "",
    val avatar: String = "",
    val meta: Meta = Meta()
)

@Serializable
data class Meta(
    @SerialName("steam2id_new")
    val steam2IdNew: String = "",
    @SerialName("realname")
    val realName: String = ""
)
