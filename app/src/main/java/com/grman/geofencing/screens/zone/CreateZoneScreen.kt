package com.grman.geofencing.screens.zone

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grman.geofencing.data.auth.AuthRepository
import com.grman.geofencing.data.auth.AuthState
import com.grman.geofencing.items.zone.CreateZoneHeader
import com.grman.geofencing.items.zone.ZoneLocationCard
import com.grman.geofencing.screens.profile.ProfileScreen
import com.grman.geofencing.ui.theme.GeoFencingTheme
import com.grman.geofencing.viewmodels.profile.ProfileViewModel
import com.grman.geofencing.viewmodels.zone.CreateZoneViewModel
import kotlinx.coroutines.flow.flowOf

@Composable
fun CreateZoneScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onModifyLocationClick: () -> Unit = {},
    onAddPhotosClick: () -> Unit = {},
    onPublishSuccess: () -> Unit = {},
    viewModel: CreateZoneViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 24.dp)
    ) {

        Spacer(Modifier.height(16.dp))

        // Header
        CreateZoneHeader(
            onBackClick = onBackClick
        )

        // Scrollable column
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(Modifier.height(20.dp))

            // Emplacement
            ZoneLocationCard(
                locationName = viewModel.locationName,
                latitude = viewModel.latitude,
                longitude = viewModel.longitude,
                radius = viewModel.radius,
                onModifyClick = onModifyLocationClick
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF0A171C)
@Composable
fun CreateZoneScreenPreview() {
    val fakeRepo = object : AuthRepository {
        override val authState = flowOf(AuthState.Unauthenticated)
        override fun currentFirstName() = "Billie"

        override suspend fun signIn(email: String, password: String) {}
        override suspend fun signUp(email: String, password: String, firstName: String) {}
        override suspend fun signOut() {}
    }

    GeoFencingTheme {
        CreateZoneScreen()
    }
}