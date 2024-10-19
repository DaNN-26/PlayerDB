package com.example.playerdb.components.main.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.example.network.repository.KtorRepository
import com.example.playerdb.mvi.main.details.DetailsIntent
import com.example.playerdb.mvi.main.details.DetailsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class RealDetailsComponent @Inject constructor(
    private val componentContext: ComponentContext,
    private val ktorRepository: KtorRepository,
    private val userId: String
) : DetailsComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume("DETAILS_COMPONENT", DetailsState.serializer()) ?: DetailsState()
    )

    override val state = _state

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        processIntent(DetailsIntent.GetDetails)
    }

    override fun processIntent(intent: DetailsIntent) {
        when(intent) {
            is DetailsIntent.GetDetails -> getUserDetails(userId = userId)
        }
    }

    private fun getUserDetails(userId: String) {
        scope.launch {
            try {
                val user = ktorRepository.getSteamUser(userId)
                _state.update { it.copy(user = user)}
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}