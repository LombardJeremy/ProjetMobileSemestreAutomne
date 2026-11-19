package com.grman.geofencing.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grman.geofencing.R
import com.grman.geofencing.items.auth.LogoHeader
import com.grman.geofencing.ui.theme.AppBackground
import com.grman.geofencing.ui.theme.GeoFencingTheme
import com.grman.geofencing.ui.theme.TealAccent
import com.grman.geofencing.items.auth.InfoPill
import com.grman.geofencing.items.common.AppButton
import com.grman.geofencing.ui.theme.GraySubtitle

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit = {},
    onLogInClick: () -> Unit = {},
) {

    Box(
        modifier.fillMaxSize().background(AppBackground)
    ){
        // Photo background
        Image(
            painter = painterResource(R.drawable.home_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Dégradé de la photo pour que ça soit + lisible
        Box(
            Modifier.fillMaxSize().background(
                Brush.verticalGradient(
                    0f to AppBackground.copy(alpha = 0.95f),   // haut bien sombre (logo + titre)
                    0.30f to AppBackground.copy(alpha = 0.75f), // toujours sombre au niveau du sous-titre
                    0.50f to Color.Transparent,                 // la photo réapparaît au milieu
                    0.80f to AppBackground.copy(alpha = 0.9f),  // sombre en bas (boutons)
                    1f to AppBackground
                )
            )
        )

        Column(Modifier.fillMaxSize().systemBarsPadding().padding(horizontal = 24.dp)) {
            Spacer(Modifier.height(24.dp))

            // Logo
            LogoHeader(stringResource(R.string.app_name))

            Spacer(Modifier.height(32.dp))

            // Home text 1
            Text(
                stringResource(R.string.home_tag),
                color = TealAccent,
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(Modifier.height(16.dp))

            // Home text 2
            Text(
                stringResource(R.string.home_title),
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(Modifier.height(16.dp))

            // Home text 3
            Text(
                stringResource(R.string.home_subtitle),
                color = Color.White.copy(alpha = 0.85f),
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(Modifier.weight(1f))

            // Infos sur chicoutimi
            InfoPill(
                Icons.Filled.LocationOn,
                stringResource(R.string.home_pill_location),
                Modifier.wrapContentWidth()
            )

            Spacer(Modifier.height(12.dp))

            // Infos sur les alertes
            InfoPill(
                Icons.Filled.NotificationsNone,
                stringResource(R.string.home_pill_alert),
                Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            // Btn create account
            AppButton(
                text = stringResource(R.string.create_account),
                onClick = onSignUpClick,
                trailingIcon = Icons.AutoMirrored.Filled.ArrowForward
            )
            Spacer(Modifier.height(12.dp))

            // Btn login
            AppButton(
                text = stringResource(R.string.already_account),
                onClick = onLogInClick,
                containerColor = Color.White.copy(alpha = 0.12f),
                contentColor = Color.White
            )

            Spacer(Modifier.height(16.dp))

            // Footer text
            Text(
                stringResource(R.string.home_footer),
                color = GraySubtitle,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A171C)
@Composable
fun WelcomeScreenPreview() {
    GeoFencingTheme() {
        WelcomeScreen()
    }
}