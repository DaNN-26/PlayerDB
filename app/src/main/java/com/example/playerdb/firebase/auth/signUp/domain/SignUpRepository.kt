package com.example.playerdb.firebase.auth.signUp.domain

import com.example.playerdb.firebase.auth.model.User

interface SignUpRepository {
    suspend fun signUp(
        email: String,
        password: String,
    ): User
}