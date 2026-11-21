package com.grman.geofencing.items.nav

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.grman.geofencing.ui.theme.AppBackground
import com.grman.geofencing.ui.theme.MediumRoundedCornerShape
import com.grman.geofencing.ui.theme.TealAccent
import com.grman.geofencing.ui.theme.TealContainer

data class BottomNavItem(
    val route: String,
    @StringRes val label: Int,
    val icon: ImageVector
)

@Composable
fun AppBottomBar(
    items: List<BottomNavItem>,
    currentRoute: String?,
    onItemClick: (String) -> Unit, // pour la nav
    modifier: Modifier = Modifier
) {
    // Navigation bar
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(AppBackground)
            .navigationBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Selection qui change couleur et route
            items.forEach { item ->
                val selected = currentRoute == item.route
                val color = if (selected) TealAccent else Color.White

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(MediumRoundedCornerShape)
                        .background(if (selected) TealContainer else Color.Transparent)
                        .clickable(role = Role.Tab) { onItemClick(item.route) }
                        .padding(vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Icon map/profile
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(Modifier.height(4.dp))

                    // Text map/profile
                    Text(
                        text = stringResource(item.label),
                        color = color,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}