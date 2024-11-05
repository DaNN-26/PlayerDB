package com.example.playerdb.firebase.auth.signIn.domain

import com.google.firebase.auth.FirebaseUser

interface SignInRepository {
    suspend fun signIn(
        email: String,
        password: String,
    ): FirebaseUser?
}