package com.grman.geofencing.data.auth

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val authState: Flow<AuthState>

    fun currentFirstName(): String?

    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String, firstName: String)
    suspend fun signOut()
}