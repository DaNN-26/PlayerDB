package com.example.network.repository

import com.example.model.SteamResponse

interface KtorRepository {
    suspend fun getSteamUser(id: String): SteamResponse
}