package com.example.playerdb.ui.main.entry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.playerdb.components.main.MainComponent
import com.example.playerdb.components.main.entry.EntryComponent
import com.example.playerdb.mvi.main.entry.EntryIntent
import com.example.playerdb.ui.components.ChangeTextField

@Composable
fun Entry(
    component: EntryComponent
) {
    val state = component.state.subscribeAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "STEAMID FINDER",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        ChangeTextField(
            value = state.value.id,
            label = "STEAMID",
            desc = "",
            onValueChanged = { component.processIntent(EntryIntent.OnIdChange(it)) },
            isPassword = false,
            keyboardType = KeyboardType.NumberPassword
        )
        Text(text = "Example: 76561197960287930")
        Button(onClick = { component.processIntent(EntryIntent.FindClick) }) {
            Text(text = "Find")
        }
    }
}