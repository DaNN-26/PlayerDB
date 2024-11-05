package com.example.playerdb.components.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.playerdb.components.main.details.DetailsComponent
import com.example.playerdb.components.main.entry.EntryComponent
import com.example.playerdb.components.main.profile.ProfileComponent

interface MainComponent {

    val stack: Value<ChildStack<*, Child>>

    fun navigateBack()

    sealed interface Child {
        class Entry(val entryComponent: EntryComponent) : Child
        class Details(val detailsComponent: DetailsComponent) : Child
        class Profile(val profileComponent: ProfileComponent) : Child
    }
}