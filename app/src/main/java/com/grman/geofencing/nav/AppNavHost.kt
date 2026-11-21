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

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.WELCOME,
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
                onSubmit = { name, email, pwd -> navController.navigateToMainGraph() } // todo: auth et sign up success -> navigate
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                onBackClick = { navController.popBackStack() },
                onSignUpClick = { navController.navigate(Routes.SIGN_UP) { popUpTo(Routes.WELCOME) } },
                onSubmit = { email, pwd -> navController.navigateToMainGraph() } // todo: auth et login success -> navigate
            )
        }

        composable(Routes.MAP) {
            MapScreen()
        }

        composable(Routes.PROFILE) {
            ProfileScreen()
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