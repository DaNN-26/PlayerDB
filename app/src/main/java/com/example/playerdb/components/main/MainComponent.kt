package com.example.playerdb.components.main

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.playerdb.components.main.details.DetailsComponent
import com.example.playerdb.components.main.entry.EntryComponent

interface MainComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class Entry(val entryComponent: EntryComponent) : Child
        class Details(val detailsComponent: DetailsComponent) : Child
    }
}