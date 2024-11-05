package com.example.playerdb.components.main.profile

import com.arkivanov.decompose.value.Value
import com.example.playerdb.mvi.main.profile.ProfileState

interface ProfileComponent {
    val state: Value<ProfileState>
}