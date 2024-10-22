package com.example.playerdb.network.data.repository

import com.example.playerdb.network.core.ApiConst
import com.example.playerdb.network.domain.model.SteamResponse
import com.example.playerdb.network.domain.repository.KtorRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class KtorRepositoryImpl @Inject constructor(
    private val client: HttpClient
) : KtorRepository {
    override suspend fun getSteamUser(id: String): SteamResponse =
        client.get(ApiConst.STEAM_API + id).body()
}