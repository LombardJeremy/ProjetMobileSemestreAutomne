package com.grman.geofencing.items.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.grman.geofencing.ui.theme.*

// Bouton générique
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    height: Dp = 52.dp,
    enabled: Boolean = true,
    loading: Boolean = false,
    containerColor: Color = TealAccent,
    contentColor: Color = AppBackground,
    trailingIcon: ImageVector? = null,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth().height(height),
        shape = LittleRoundedCornerShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor.copy(alpha = 0.3f)
        )
    ) {
        // Loading = chargement circulaire
        if (loading) {
            CircularProgressIndicator(Modifier.size(22.dp), strokeWidth = 2.dp, color = contentColor)
        } else {
            // Bouton avec texte + icon si y'en a
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(text, style = textStyle)

                if (trailingIcon != null) {
                    Icon(trailingIcon,
                        null,
                        modifier = Modifier.align(Alignment.CenterEnd).size(20.dp)
                    )
                }
            }
        }
    }
}

// Bouton retour
@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
){
    Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = "Retour",
        tint = Color.White,
        modifier = modifier.size(20.dp).clickable { onBackClick() }
    )
}