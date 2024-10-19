package com.example.network.repository

import com.example.model.SteamResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class KtorRepositoryImpl @Inject constructor(
    private val client: HttpClient
) : KtorRepository {
    override suspend fun getSteamUser(id: String): SteamResponse =
        client.get("https://playerdb.co/api/player/steam/$id").body()
}