package com.grman.geofencing.items.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.grman.geofencing.ui.theme.AppBackground
import com.grman.geofencing.ui.theme.GraySubtitle
import com.grman.geofencing.ui.theme.LittleRoundedCornerShape
import com.grman.geofencing.ui.theme.MediumRoundedCornerShape
import com.grman.geofencing.ui.theme.RedFail
import com.grman.geofencing.ui.theme.TealAccent
import com.grman.geofencing.ui.theme.TealContainer


// Barre du haut : retour + titre, contenu scrollable et footer
@Composable
fun AuthLayout(
    modifier: Modifier = Modifier,
    topTitle: String? = null,
    onBackClick: (() -> Unit)? = null,
    footer : @Composable () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppBackground)
            .systemBarsPadding()
            .imePadding()
    ) {

        // Barre du haut
        if (onBackClick != null)
        {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Bouton retour
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Retour",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp).clickable { onBackClick() }
                )

                // Titre
                if (topTitle != null) {
                    Spacer(Modifier.width(16.dp))
                    Text(topTitle, color = GraySubtitle, style = MaterialTheme.typography.labelSmall)
                }
             }
        }

        // Contenu scrollable
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            content = content
        )

        // Footer
        Box(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            footer()
        }
    }
}

// Texte d'action clickable
@Composable
fun AuthFooter(text: String, actionText: String, onClick: () -> Unit) {
    Text(
        text = buildAnnotatedString {
            append("$text  ")
            withStyle(SpanStyle(color = TealAccent)) { append(actionText) }
        },
        color = GraySubtitle,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.clickable { onClick() }
    )
}

// Titre + sous-titre
@Composable
fun AuthTitle(title: String, subtitle: String) {
    Text(
        title,
        color = Color.White,
        style = MaterialTheme.typography.headlineMedium
    )

    Spacer(Modifier.height(10.dp))

    Text(
        subtitle,
        color = GraySubtitle,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun ErrorMessage(message: String?) {
    if (message.isNullOrBlank())
        return

    Text(
        message,
        color = RedFail,
        style = MaterialTheme.typography.bodySmall
    )

    Spacer(Modifier.height(10.dp))
}

// Logo: todo custom image ?
@Composable
fun LogoBadge(size: Dp = 48.dp) {
    Box(
        modifier = Modifier.size(size).background(TealContainer, MediumRoundedCornerShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(Icons.Filled.MyLocation, null, tint = TealAccent, modifier = Modifier.size(size / 2))
    }
}

// Header avec logo + nom de l'app
@Composable
fun LogoHeader(name: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Filled.MyLocation,
            null,
            tint = TealAccent,
            modifier = Modifier.size(28.dp)
        )

        Spacer(Modifier.width(10.dp))

        Text(
            name,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium)
    }
}

// Pastille d'info avec icone + texte
@Composable
fun InfoPill(icon: ImageVector, text: String, modifier: Modifier) {
    Row(
        modifier = modifier
            .clip(LittleRoundedCornerShape)
            .background(AppBackground.copy(alpha = 0.7f))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon,
            null,
            tint = TealAccent,
            modifier = Modifier.size(16.dp)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text,
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )
    }
}