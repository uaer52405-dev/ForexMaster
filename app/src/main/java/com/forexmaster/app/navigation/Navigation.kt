package com.forexmaster.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.forexmaster.app.ui.components.BottomNavBar
import com.forexmaster.app.ui.screens.HistorialScreen
import com.forexmaster.app.ui.screens.HomeScreen
import com.forexmaster.app.ui.screens.LoginScreen
import com.forexmaster.app.ui.screens.PerfilScreen
import com.forexmaster.app.ui.screens.RecargarScreen

sealed class Screen(val route: String) {
    data object Home      : Screen("home")
    data object Recargar  : Screen("recargar")
    data object Historial : Screen("historial")
    data object Perfil    : Screen("perfil")
    data object Login     : Screen("login")
}

private val bottomNavRoutes = setOf(
    Screen.Home.route,
    Screen.Recargar.route,
    Screen.Historial.route,
    Screen.Perfil.route
)

@Composable
fun ForexMasterNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomNavRoutes) {
                BottomNavBar(
                    currentRoute  = currentRoute,
                    onItemSelected = { route ->
                        navController.navigate(route) {
                            popUpTo(Screen.Home.route) { saveState = true }
                            launchSingleTop = true
                            restoreState    = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController    = navController,
            startDestination = Screen.Home.route,
            modifier         = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onNavigateToRecargar = { navController.navigate(Screen.Recargar.route) },
                    onNavigateToPerfil   = { navController.navigate(Screen.Perfil.route) }
                )
            }
            composable(Screen.Recargar.route) {
                RecargarScreen()
            }
            composable(Screen.Historial.route) {
                HistorialScreen()
            }
            composable(Screen.Perfil.route) {
                PerfilScreen(
                    onNavigateToLogin = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    onBackClick    = { navController.popBackStack() },
                    onLoginSuccess = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
