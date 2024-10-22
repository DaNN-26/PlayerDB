package com.example.playerdb.network.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SteamResponse(
    val data: Data = Data(),
    val success: Boolean = false
)
@Serializable
data class Data(
    val player: Player = Player()
)
@Serializable
data class Player(
    val meta: Meta = Meta(),
    val username: String = "",
    val id: String = "",
    val avatar: String = ""
)
@Serializable
data class Meta (
    @SerialName("steam2id")
    var steam2Id: String = "",
    @SerialName("steam2id_new")
    var steam2IdNew: String = "",
    @SerialName("steam3id")
    var steam3Id: String = "",
    @SerialName("steam64id")
    var steam64Id: String = "",
    @SerialName("steamid")
    var steamId: String = "",
    @SerialName("personaname")
    var personaName: String = "",
    @SerialName("profileurl")
    var profileUrl: String = ""

)