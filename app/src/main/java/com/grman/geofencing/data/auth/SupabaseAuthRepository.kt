
package com.grman.geofencing.data.auth

import com.grman.geofencing.data.auth.AuthRepository
import com.grman.geofencing.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

class SupabaseAuthRepository : AuthRepository {

    override val authState: Flow<AuthState> =
        supabase.auth.sessionStatus.map { status ->
            when (status) {
                is SessionStatus.Initializing -> AuthState.Loading
                is SessionStatus.Authenticated -> AuthState.Authenticated
                else -> AuthState.Unauthenticated
            }
        }

    override fun currentFirstName(): String? =
        supabase.auth.currentUserOrNull()
            ?.userMetadata
            ?.get("firstName")
            ?.jsonPrimitive
            ?.contentOrNull

    override suspend fun signIn(email: String, password: String) {
        supabase.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    override suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
    ) {
        supabase.auth.signUpWith(Email) {
            this.email = email
            this.password = password

            data = buildJsonObject {
                put("firstName", firstName)
            }
        }
    }

    override suspend fun signOut() {
        supabase.auth.signOut()
    }
}
