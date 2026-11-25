
package com.grman.geofencing.data.auth

import com.grman.geofencing.data.auth.AuthRepository
import com.grman.geofencing.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class SupabaseAuthRepository : AuthRepository {

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
}
