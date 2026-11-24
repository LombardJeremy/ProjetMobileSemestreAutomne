package com.grman.geofencing.data.auth

interface AuthRepository {
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String, name: String)
}