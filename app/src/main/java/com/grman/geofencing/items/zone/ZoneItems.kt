package com.grman.geofencing.items.zone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.grman.geofencing.R
import com.grman.geofencing.items.common.BackButton
import com.grman.geofencing.ui.theme.GrayLabel
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
                color = MaterialTheme.colorScheme.onSurface // todo: color
            )

            Spacer(Modifier.height(4.dp))

            // Latitude N · Longitude O
            Text(
                text = "$latitude N · $longitude O", // todo: strings.xml
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant // todo: color
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

// Titre au-dessus de chaque section
@Composable
fun ZoneSectionLabel(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        color = GrayLabel
    )
}

// Row de selection de type de zone
@Composable
fun ZoneTypeSelector(
    selectedType: String,
    onTypeSelected: (String) -> Unit
) {
    val types = listOf( // todo: strings.xml ?
        "Nature",
        "Patrimoine",
        "Insolite",
        "Autre"
    )

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        types.forEach { type ->

            val selected = selectedType == type

            FilterChip(
                selected = selected,
                onClick = { onTypeSelected(type) },
                label = {
                    Text(
                        text = type,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = TealAccent.copy(alpha = 0.18f),
                    selectedLabelColor = TealAccent,
                    containerColor = MaterialTheme.colorScheme.surface, // todo: color
                    labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = selected,
                    borderColor = MaterialTheme.colorScheme.surface, // todo: color
                    selectedBorderColor = TealAccent
                )
            )
        }
    }
}

// todo: enlever clavier avec clic en dehors
@Composable
fun ZoneFormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    singleLine: Boolean = true,
    maxLength: Int? = null
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ZoneSectionLabel(label)

            if (maxLength != null) {
                Text(
                    text = "${value.length}/$maxLength",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant // todo: color
                )
            }
        }

        Spacer(Modifier.height(6.dp))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    placeholder,
                    color = MaterialTheme.colorScheme.onSurfaceVariant // todo: color
                )
            },
            singleLine = singleLine,
            minLines = if (singleLine) 1 else 4,
            maxLines = if (singleLine) 1 else 6,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = TealAccent,
                unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = TealAccent
            ),
            textStyle = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun ZonePhotosCard(
    photoCount: Int,
    onAddClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        // Row avec titre + texte avec nbr max
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ZoneSectionLabel("Photos") // todo: strings.xml

            Text(
                text = "Facultatif · 3 max.", // todo: strings.xml
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant // todo: color
            )
        }

        Spacer(Modifier.height(10.dp))

        // Surface où cliquer pour ajouter les photos
        Surface(
            onClick = onAddClick,
            enabled = photoCount < 3,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface, // todo: color
            border = androidx.compose.foundation.BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.outlineVariant // todo: color
            )
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Icone d'appreil photo
                Icon(
                    Icons.Default.AddAPhoto,
                    contentDescription = null,
                    tint = TealAccent
                )

                Spacer(Modifier.width(10.dp))

                // Texte à cote de l'icone pour ajouter les photos
                Text(
                    text = if (photoCount == 0) {
                        "Ajouter des photos du lieu" // todo: strings.xml
                    } else {
                        "$photoCount / 3 photos ajoutées" // todo: strings.xml
                    },
                    color = MaterialTheme.colorScheme.onSurfaceVariant, // todo: color
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

// Simple petit texte d'erreur
@Composable
fun ZoneErrorMessage(
    message: String
) {
    Text(
        text = message,
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFFFF6B6B), // todo: color
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center
    )
}