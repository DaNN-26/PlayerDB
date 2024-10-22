package com.example.playerdb.mvi.main.details

import android.content.Context

sealed class DetailsIntent {
    data object GetDetails : DetailsIntent()
    data object NavigateBack : DetailsIntent()
}