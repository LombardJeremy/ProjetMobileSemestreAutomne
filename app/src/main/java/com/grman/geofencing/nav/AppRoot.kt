package com.grman.geofencing.nav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.grman.geofencing.items.nav.AppBottomBar
import com.grman.geofencing.ui.theme.AppBackground

// Racine de l'UI, sert à afficher le scaffold avec nav bar ou pas (si map ou profile)
@Composable
fun AppRoot() {
    // todo: viewmodel ?

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState();
    val currentRoute = backStackEntry?.destination?.route;

    Scaffold(
        containerColor = AppBackground,
        contentWindowInsets = WindowInsets(0),
        bottomBar = {
            // Animation d'apparition de la barre
            AnimatedVisibility(
                visible = currentRoute in bottomBarRoutes,
                enter = slideInVertically { it },
                exit = slideOutVertically { it }
            ) {
                AppBottomBar(
                    items = bottomNavItems,
                    currentRoute = currentRoute,
                    onItemClick = { route -> navController.navigateToTab(route) }
                )
            }
        }
    ) {

        innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )

    }
}

private fun NavHostController.navigateToTab(route: String) {
    navigate(route) {
        popUpTo(Routes.MAP) { saveState = true }   // MAP = onglet de départ après l'auth
        launchSingleTop = true
        restoreState = true
    }
}