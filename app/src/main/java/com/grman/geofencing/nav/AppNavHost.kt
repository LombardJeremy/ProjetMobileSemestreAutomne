package com.grman.geofencing.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.grman.geofencing.screens.auth.LoginScreen
import com.grman.geofencing.screens.auth.SignUpScreen
import com.grman.geofencing.screens.auth.WelcomeScreen
import com.grman.geofencing.nav.Routes
import com.grman.geofencing.screens.map.MapScreen
import com.grman.geofencing.screens.profile.ProfileScreen
import com.grman.geofencing.screens.zone.CreateZoneScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(Routes.WELCOME) {
            WelcomeScreen(
                onSignUpClick = { navController.navigate(Routes.SIGN_UP) },
                onLogInClick = { navController.navigate(Routes.LOGIN) }
            )
        }

        composable(Routes.SIGN_UP) {
            SignUpScreen(
                onBackClick = { navController.popBackStack() },
                onLogInClick = { navController.navigate(Routes.LOGIN) { popUpTo(Routes.WELCOME) } },
                onSignUpSuccess = { navController.navigateToMainGraph() }
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                onBackClick = { navController.popBackStack() },
                onSignUpClick = { navController.navigate(Routes.SIGN_UP) { popUpTo(Routes.WELCOME) } },
                onLoginSuccess = { navController.navigateToMainGraph() }
            )
        }

        composable(Routes.MAP) {
            MapScreen(
                onCreateZoneClick = { navController.navigate(Routes.CREATE_ZONE) }
            )
        }

        composable(Routes.PROFILE) {
            ProfileScreen()
        }

        composable(Routes.CREATE_ZONE) {
            CreateZoneScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onModifyLocationClick = {
                    navController.popBackStack()
                },
                onAddPhotosClick = {
                    // todo
                },
                onPublishSuccess = {
                    // todo
                }
            )
        }
    }
}

// Après connexion : va sur la carte et retire tout l'historique d'auth (retour pas possible)
fun NavHostController.navigateToMainGraph() {
    navigate(Routes.MAP) {
        popUpTo(Routes.WELCOME) { inclusive = true }
        launchSingleTop = true
    }
}