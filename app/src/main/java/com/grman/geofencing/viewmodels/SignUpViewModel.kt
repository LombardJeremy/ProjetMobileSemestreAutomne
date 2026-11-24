package com.grman.geofencing.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grman.geofencing.data.auth.AuthRepository
import com.grman.geofencing.data.auth.SupabaseAuthRepository
import kotlinx.coroutines.launch

class SignUpViewModel(private val authRepository: AuthRepository = SupabaseAuthRepository()) : ViewModel()
{
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun signUp(onSuccess: () -> Unit) {

        // Validation locale des erreurs dans mdp etc
        when {
            name.isBlank() -> { errorMessage = "Choisis un nom"; return }
            name.length < 3 -> { errorMessage = "Le nom doit faire au moins 3 caractères"; return }
            email.isBlank() -> { errorMessage = "Entre ton adresse email"; return }
            password.length < 6 -> { errorMessage = "Le mot de passe doit faire au moins 6 caractères"; return }
            password != confirmPassword -> { errorMessage = "Les mots de passe ne correspondent pas"; return }
        }

        // Debut du chargement avec spinner dans sign up screen
        isLoading = true
        errorMessage = null

        // Creation du compte dans firebase email / password
        viewModelScope.launch {
            try {
                authRepository.signUp(email.trim(), password, name.trim())
                onSuccess()
            } catch (e: Exception) {
                errorMessage = e.message ?: "Erreur lors de l'inscription"
            }
            isLoading = false
        }
    }
}