package com.example.playerdb.ui.main.entry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.example.playerdb.components.main.entry.EntryComponent
import com.example.playerdb.mvi.main.entry.EntryIntent
import com.example.playerdb.ui.components.ChangeTextField

@Composable
fun Entry(
    component: EntryComponent,
    contentPadding: PaddingValues
) {
    val state by component.state.subscribeAsState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        Text(
            text = "STEAMID FINDER",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        ChangeTextField(
            value = state.id,
            label = "STEAMID",
            desc = "",
            onValueChanged = {
                if(it.isDigitsOnly())
                    component.processIntent(EntryIntent.OnIdChange(it))
            },
            isPassword = false,
            keyboardType = KeyboardType.NumberPassword
        )
        Text(text = "Example: 76561197960287930")
        Button(onClick = { component.processIntent(EntryIntent.FindClick) }) {
            Text(text = "Find")
        }
        Button(onClick = { component.processIntent(EntryIntent.SignOutClick) }) {
            Text(text = "Sign Out")
        }
    }
}