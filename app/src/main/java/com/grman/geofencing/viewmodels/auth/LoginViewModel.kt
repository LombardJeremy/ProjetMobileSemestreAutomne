package com.grman.geofencing.viewmodels.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grman.geofencing.data.auth.AuthRepository
import com.grman.geofencing.data.auth.SupabaseAuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository = SupabaseAuthRepository()
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun signIn(onSuccess: () -> Unit) {
        when {
            email.isBlank() -> { errorMessage = "Entre ton adresse email"; return }
            password.isBlank() -> { errorMessage = "Entre ton mot de passe"; return }
        }

        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                authRepository.signIn(email.trim(), password)
                onSuccess()
            } catch (e: Exception) {
                errorMessage = e.message ?: "Erreur lors de la connexion"
            } finally {
                isLoading = false
            }
        }
    }
}