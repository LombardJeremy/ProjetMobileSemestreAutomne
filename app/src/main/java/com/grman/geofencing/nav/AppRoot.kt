package com.grman.geofencing.nav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.grman.geofencing.data.auth.AuthState
import com.grman.geofencing.items.nav.AppBottomBar
import com.grman.geofencing.ui.theme.AppBackground
import com.grman.geofencing.viewmodels.app.AppViewModel

// Racine de l'UI, sert à afficher le scaffold avec nav bar ou pas (si map ou profile)
@Composable
fun AppRoot(viewModel: AppViewModel = viewModel()) {

    // Check si on laisse l'user déjà connecté ou pas
    val authState by viewModel.authState.collectAsState()

    if (authState is AuthState.Loading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val startDestination = if (authState is AuthState.Authenticated) Routes.MAP else Routes.WELCOME

    // todo: à metetre dans le view model ?
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState();
    val currentRoute = backStackEntry?.destination?.route;

    // Déconnexion / session expirée : retour à Welcome
    LaunchedEffect(authState) {
        if (authState is AuthState.Unauthenticated &&
            navController.currentDestination?.route in bottomBarRoutes
        ) {
            navController.navigate(Routes.WELCOME) { popUpTo(0) { inclusive = true } }
        }
    }

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
            startDestination = startDestination,
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