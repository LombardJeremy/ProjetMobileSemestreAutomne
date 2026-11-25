package com.grman.geofencing.viewmodels.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grman.geofencing.data.auth.AuthRepository
import com.grman.geofencing.data.auth.AuthState
import com.grman.geofencing.data.auth.SupabaseAuthRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class AppViewModel(
    authRepository: AuthRepository = SupabaseAuthRepository()
) : ViewModel() {

    val authState: StateFlow<AuthState> = authRepository.authState
        .stateIn(viewModelScope, SharingStarted.Eagerly, AuthState.Loading)
}