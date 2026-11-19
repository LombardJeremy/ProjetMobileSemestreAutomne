package com.grman.geofencing.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.grman.geofencing.screens.auth.LoginScreen
import com.grman.geofencing.screens.auth.SignUpScreen
import com.grman.geofencing.screens.auth.WelcomeScreen

object Routes {
    const val WELCOME = "welcome"
    const val SIGN_UP = "sign_up"
    const val LOGIN = "login"
}

@Composable
fun AppNavigation() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Routes.WELCOME) {
        composable(Routes.WELCOME) {
            WelcomeScreen(
                onSignUpClick = { nav.navigate(Routes.SIGN_UP) },
                onLogInClick = { nav.navigate(Routes.LOGIN) }
            )
        }
        composable(Routes.SIGN_UP) {
            SignUpScreen(
                onBackClick = { nav.popBackStack() },
                onLogInClick = { nav.navigate(Routes.LOGIN) { popUpTo(Routes.WELCOME) } },
                onSubmit = { name, email, pwd -> /* TODO: auth */ }
            )
        }
        composable(Routes.LOGIN) {
            LoginScreen(
                onBackClick = { nav.popBackStack() },
                onSignUpClick = { nav.navigate(Routes.SIGN_UP) { popUpTo(Routes.WELCOME) } },
                onSubmit = { email, pwd -> /* TODO: auth */ }
            )
        }
    }
}