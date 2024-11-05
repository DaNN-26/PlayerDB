package com.example.playerdb.components.main.details

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.playerdb.mvi.main.details.DetailsIntent
import com.example.playerdb.mvi.main.details.DetailsState
import com.example.playerdb.network.domain.repository.KtorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class RealDetailsComponent @Inject constructor(
    private val componentContext: ComponentContext,
    private val ktorRepository: KtorRepository,
    private val onProfileClick: (String) -> Unit,
    private val userId: String
) : DetailsComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume(DETAILS_COMPONENT, DetailsState.serializer()) ?: DetailsState()
    )

    override val state = _state

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    init {
        processIntent(DetailsIntent.GetDetails)
    }

    override fun processIntent(intent: DetailsIntent) {
        when(intent) {
            is DetailsIntent.GetDetails -> getUserDetails(userId = userId)
            is DetailsIntent.OpenProfileUrl -> onProfileClick(state.value.user.data.player.meta.profileUrl)
        }
    }

    private fun getUserDetails(userId: String) {
        scope.launch {
            try {
                val user = ktorRepository.getSteamUser(userId)
                _state.update { it.copy(user = user, isLoading = false) }
                checkError()
            } catch (e: Exception) {
                Log.d("Error", e.message.toString())
            }
        }
    }

    private fun checkError() {
        if (state.value.user.success)
                _state.update { it.copy(isError = false) }
            else
                _state.update { it.copy(isError = true) }
    }

    companion object {
        const val DETAILS_COMPONENT = "DETAILS_COMPONENT"
    }
}