package com.example.playerdb.components.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.playerdb.components.auth.AuthComponent
import com.example.playerdb.components.main.MainComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class Auth(val authComponent: AuthComponent) : Child
        class Main(val mainComponent: MainComponent) : Child
    }
}