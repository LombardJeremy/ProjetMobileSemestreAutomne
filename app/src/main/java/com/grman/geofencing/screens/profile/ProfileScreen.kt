package com.grman.geofencing.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grman.geofencing.data.auth.AuthRepository
import com.grman.geofencing.data.auth.AuthState
import com.grman.geofencing.items.auth.ErrorMessage
import com.grman.geofencing.items.common.AppButton
import com.grman.geofencing.ui.theme.GeoFencingTheme
import com.grman.geofencing.ui.theme.TealAccent
import com.grman.geofencing.viewmodels.profile.ProfileViewModel
import kotlinx.coroutines.flow.flowOf

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel()
) {
    Column(
        Modifier.fillMaxSize().padding(horizontal = 24.dp)
    ) {
        Spacer(Modifier.height(24.dp))

        Text(
            "le profil",
            color = TealAccent,
            style = MaterialTheme.typography.labelSmall
        )

        Spacer(Modifier.height(24.dp))

        if (viewModel.firstName.isNotBlank()) {
            Text(
                text = viewModel.firstName,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Spacer(Modifier.height(24.dp))

        AppButton(
            text = "Se déconnecter",
            onClick = { viewModel.signOut() },
            enabled = !viewModel.isLoading,
            loading = viewModel.isLoading
        )

        Spacer(Modifier.height(10.dp))

        ErrorMessage(viewModel.errorMessage)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A171C)
@Composable
fun MapScreenPreview() {
    val fakeRepo = object : AuthRepository {
        override val authState = flowOf(AuthState.Unauthenticated)
        override fun currentFirstName() = "Billie"

        override suspend fun signIn(email: String, password: String) {}
        override suspend fun signUp(email: String, password: String, firstName: String) {}
        override suspend fun signOut() {}
    }

    GeoFencingTheme {
        ProfileScreen(viewModel = ProfileViewModel(fakeRepo))
    }
}