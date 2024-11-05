package com.example.playerdb.firebase.auth.di

import android.content.Context
import com.example.playerdb.firebase.auth.google.data.GoogleSignInRepositoryImpl
import com.example.playerdb.firebase.auth.google.domain.GoogleSignInRepository
import com.example.playerdb.firebase.auth.signIn.data.SignInRepositoryImpl
import com.example.playerdb.firebase.auth.signIn.domain.SignInRepository
import com.example.playerdb.firebase.auth.signUp.data.SignUpRepositoryImpl
import com.example.playerdb.firebase.auth.signUp.domain.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Singleton
    @Provides
    fun provideFirebase(): FirebaseAuth =
        FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideSignUpRepository(firebaseAuth: FirebaseAuth): SignUpRepository =
        SignUpRepositoryImpl(firebaseAuth)

    @Singleton
    @Provides
    fun provideSignInRepository(firebaseAuth: FirebaseAuth): SignInRepository =
        SignInRepositoryImpl(firebaseAuth)

    @Singleton
    @Provides
    fun provideGoogleSignInRepository(
        firebaseAuth: FirebaseAuth,
        @ApplicationContext context: Context
    ): GoogleSignInRepository =
        GoogleSignInRepositoryImpl(firebaseAuth, context)
}