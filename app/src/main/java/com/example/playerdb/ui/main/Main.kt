package com.example.playerdb.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.example.playerdb.components.main.MainComponent
import com.example.playerdb.components.main.MainComponent.Child
import com.example.playerdb.ui.components.TopBar
import com.example.playerdb.ui.main.details.Details
import com.example.playerdb.ui.main.entry.Entry
import com.example.playerdb.ui.main.profile.Profile

@Composable
fun Main(
    component: MainComponent,
) {
    Children(
        stack = component.stack,
        animation = stackAnimation(fade() + scale())
    ) { child ->
        Scaffold(
            topBar = {
                TopBar(
                    title = when(child.instance) {
                        is Child.Entry -> "Finder"
                        is Child.Details -> "Details"
                        is Child.Profile -> "Profile"
                    },
                    canNavigateBack = child.instance !is Child.Entry,
                    navigateBack = component::navigateBack
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { contentPadding ->
            when (val instance = child.instance) {
                is Child.Entry -> Entry(
                    component = instance.entryComponent,
                    contentPadding = contentPadding
                )
                is Child.Details -> Details(
                    component = instance.detailsComponent,
                    contentPadding = contentPadding
                )
                is Child.Profile -> Profile(
                    component = instance.profileComponent,
                    contentPadding = contentPadding
                )
            }
        }
    }
}