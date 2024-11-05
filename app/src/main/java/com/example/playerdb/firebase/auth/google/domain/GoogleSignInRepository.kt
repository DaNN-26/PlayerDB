package com.example.playerdb.firebase.auth.google.domain

import android.content.Intent
import androidx.activity.result.ActivityResult

interface GoogleSignInRepository {

    fun getSignInIntent(): Intent
    suspend fun signInWithGoogle(result: ActivityResult)
}