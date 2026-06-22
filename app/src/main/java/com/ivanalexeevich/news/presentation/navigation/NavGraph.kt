package com.ivanalexeevich.news.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ivanalexeevich.news.presentation.screens.settings.SettingsScreen
import com.ivanalexeevich.news.presentation.screens.subscription.SubscriptionsScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Subscription.route
    ) {
        composable(route = Screen.Subscription.route) {
            SubscriptionsScreen(
                onNavigateToSettings = {
                    navController.navigate(Screen.Settings.route)
                }
            )

        }
        composable(route = Screen.Settings.route) {
            SettingsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class Screen(val route: String) {


    object Subscription : Screen("subscriptions")

    object Settings : Screen("settings")
}