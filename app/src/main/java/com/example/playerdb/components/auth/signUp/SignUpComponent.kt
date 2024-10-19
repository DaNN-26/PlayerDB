package com.example.playerdb.components.auth.signUp

import com.arkivanov.decompose.value.Value
import com.example.playerdb.mvi.auth.signUp.SignUpIntent
import com.example.playerdb.mvi.auth.signUp.SignUpState

interface SignUpComponent {

    val state: Value<SignUpState>

    fun processIntent(intent: SignUpIntent)
}