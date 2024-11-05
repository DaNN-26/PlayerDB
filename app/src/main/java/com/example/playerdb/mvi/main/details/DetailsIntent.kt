package com.example.playerdb.mvi.main.details

sealed class DetailsIntent {
    data object GetDetails : DetailsIntent()
    data object OpenProfileUrl: DetailsIntent()
}