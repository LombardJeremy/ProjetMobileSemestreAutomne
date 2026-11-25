package com.grman.geofencing.viewmodels.auth

import android.util.Patterns
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
    var firstName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun signUp(onSuccess: () -> Unit) {

        // Validation locale des erreurs dans mdp etc
        when {
            firstName.isBlank() -> { errorMessage = "Choisis un prénom"; return }
            firstName.length < 3 -> { errorMessage = "Le prénom doit faire au moins 3 caractères"; return }
            email.isBlank() -> { errorMessage = "Entre ton adresse email"; return }
            password.length < 6 -> { errorMessage = "Le mot de passe doit faire au moins 6 caractères"; return }
            password != confirmPassword -> { errorMessage = "Les mots de passe ne correspondent pas"; return }
            !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() -> { errorMessage = "Adresse email invalide"; return }
        }

        // Debut du chargement avec spinner dans sign up screen
        isLoading = true
        errorMessage = null

        // Creation du compte dans supabase email / password
        viewModelScope.launch {
            try {
                authRepository.signUp(email.trim(), password, firstName.trim())
                onSuccess()
            } catch (e: Exception) {
                errorMessage = e.message ?: "Erreur lors de l'inscription"
            } finally {
            isLoading = false
            }
        }
    }
}