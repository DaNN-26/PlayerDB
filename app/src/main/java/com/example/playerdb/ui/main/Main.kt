package com.example.playerdb.ui.main

import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.example.playerdb.components.main.MainComponent
import com.example.playerdb.components.main.MainComponent.Child
import com.example.playerdb.ui.main.details.Details
import com.example.playerdb.ui.main.entry.Entry

@Composable
fun Main(
    component: MainComponent,
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Children(
            stack = component.stack,
            animation = stackAnimation(fade() + scale())
        ) { child ->
            when (val instance = child.instance) {
                is Child.Entry -> Entry(component = instance.entryComponent)
                is Child.Details -> Details(component = instance.detailsComponent)
            }
        }
    }
}