package com.example.playerdb.components.auth.signIn

import com.arkivanov.decompose.value.Value
import com.example.playerdb.mvi.auth.signIn.SignInIntent
import com.example.playerdb.mvi.auth.signIn.SignInState

interface SignInComponent {

    val state: Value<SignInState>

    fun processIntent(intent: SignInIntent)
}