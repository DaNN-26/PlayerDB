package com.example.playerdb.ui.main.details

import android.telecom.Call.Details
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.playerdb.components.main.details.DetailsComponent

@Composable
fun Details(
    component: DetailsComponent
) {
    val state = component.state.subscribeAsState()
    
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = state.value.user.id)
        Text(text = state.value.user.username)
    }
}