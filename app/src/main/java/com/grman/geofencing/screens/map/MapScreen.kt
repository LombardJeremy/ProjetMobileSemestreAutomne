package com.grman.geofencing.screens.map

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.grman.geofencing.ui.theme.GeoFencingTheme
import com.grman.geofencing.ui.theme.TealAccent

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    onCreateZoneClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Spacer(Modifier.height(24.dp))

        Text(
            "la map",
            color = TealAccent,
            style = MaterialTheme.typography.labelSmall
        )

        // Create zone btn
        FloatingActionButton(
            onClick = onCreateZoneClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 20.dp),
            containerColor = TealAccent,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            shape = MaterialTheme.shapes.large
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Créer une zone"
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A171C)
@Composable
fun MapScreenPreview() {
    GeoFencingTheme {
        MapScreen()
    }
}