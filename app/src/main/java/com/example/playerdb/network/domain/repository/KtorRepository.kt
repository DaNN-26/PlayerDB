package com.example.playerdb.network.domain.repository

import com.example.playerdb.network.domain.model.SteamResponse

interface KtorRepository {
    suspend fun getSteamUser(id: String): SteamResponse
}