package com.grman.geofencing.viewmodels.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grman.geofencing.data.auth.AuthRepository
import com.grman.geofencing.data.auth.SupabaseAuthRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val authRepository: AuthRepository = SupabaseAuthRepository()
) : ViewModel() {

    val firstName: String = authRepository.currentFirstName().orEmpty()
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun signOut() {
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                authRepository.signOut()
            } catch (e: Exception) {
                errorMessage = "Erreur lors de la déconnexion"
            } finally {
                isLoading = false
            }
        }
    }
}