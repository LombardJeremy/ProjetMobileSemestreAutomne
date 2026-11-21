package com.grman.geofencing.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Person
import com.grman.geofencing.R
import com.grman.geofencing.items.nav.BottomNavItem

object Routes {
    const val WELCOME = "welcome"
    const val SIGN_UP = "sign_up"
    const val LOGIN = "login"

    const val MAP = "map"
    const val PROFILE = "profile"
}

val bottomNavItems = listOf(
    BottomNavItem(Routes.MAP, R.string.nav_map, Icons.Outlined.Map),
    BottomNavItem(Routes.PROFILE, R.string.nav_profile, Icons.Outlined.Person)
)

val bottomBarRoutes = setOf(Routes.MAP, Routes.PROFILE)