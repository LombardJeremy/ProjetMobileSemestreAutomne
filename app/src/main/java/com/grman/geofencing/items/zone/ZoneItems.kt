package com.grman.geofencing.items.zone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.grman.geofencing.items.common.BackButton
import com.grman.geofencing.ui.theme.TealAccent

@Composable
fun CreateZoneHeader(
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Retour
        BackButton(
            onBackClick = onBackClick
        )

        Spacer(Modifier.width(8.dp))

        // Title
        Column {
            Text(
                text = "Créer une zone", //todo: strings.xml
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "2/2 - Informations du lieu", //todo: strings.xml
                style = MaterialTheme.typography.bodySmall,
                color = TealAccent
            )
        }
    }
}

@Composable
fun ZoneLocationCard(
    locationName: String,
    latitude: String,
    longitude: String,
    radius: Int,
    onModifyClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(18.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Icon location todo: remplacer par la map si possible
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    TealAccent.copy(alpha = 0.12f),
                    RoundedCornerShape(14.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Filled.LocationOn,
                contentDescription = null,
                tint = TealAccent
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            // Location name
            Text(
                text = locationName,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(4.dp))

            // Latitude N · Longitude O
            Text(
                text = "$latitude N · $longitude O", // todo: strings.xml
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(4.dp))

            // Radius
            Text(
                text = "Rayon : ${radius} m", // todo: strings.xml
                style = MaterialTheme.typography.labelSmall,
                color = TealAccent
            )
        }

        // Modify btn
        IconButton(onClick = onModifyClick) {
            Icon(
                Icons.Default.Edit,
                contentDescription = "Modifier l'emplacement",
                tint = TealAccent
            )
        }
    }
}