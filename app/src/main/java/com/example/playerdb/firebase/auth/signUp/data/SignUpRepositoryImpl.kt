package com.example.playerdb.firebase.auth.signUp.data

import com.example.playerdb.firebase.auth.model.User
import com.example.playerdb.firebase.auth.signUp.domain.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SignUpRepositoryImpl @Inject constructor(
    private val firebase: FirebaseAuth,
) : SignUpRepository {
    override suspend fun signUp(email: String, password: String): User =
        suspendCoroutine { continuation ->
            firebase.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    val user = result.user
                    if (user != null)
                        continuation.resume(value = User(user.email.orEmpty()))
                }
                .addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        }
}