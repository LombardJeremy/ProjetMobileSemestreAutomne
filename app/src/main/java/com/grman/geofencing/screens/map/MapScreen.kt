package com.grman.geofencing.screens.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grman.geofencing.R
import com.grman.geofencing.screens.auth.LoginScreen
import com.grman.geofencing.ui.theme.GeoFencingTheme
import com.grman.geofencing.ui.theme.TealAccent

@Composable
fun MapScreen(
    modifier: Modifier = Modifier
) {
    Column(
        Modifier.fillMaxSize().padding(horizontal = 24.dp)
    ) {
        Spacer(Modifier.height(24.dp))

        Text(
            "la map",
            color = TealAccent,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A171C)
@Composable
fun MapScreenPreview() {
    GeoFencingTheme {
        MapScreen()
    }
}