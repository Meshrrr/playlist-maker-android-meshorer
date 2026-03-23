package com.practicum.playlist_maker_android_meshorer

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHost
import androidx.navigation.compose.composable


enum class Screen(val route: String) {
    MAIN_MENU("main_menu"),
    SEARCH("search"),
    SETTINGS("settings")
}

@Composable
fun PlaylistHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.MAIN_MENU.route
        ) {
        composable(route = Screen.MAIN_MENU.route) {
            Main_screen(
                navigateToSearch = {navController.navigate(Screen.SEARCH.route)},
                navigateToSettings = {navController.navigate(Screen.SETTINGS.route)}
            )
        }

        composable(route = Screen.SEARCH.route) {
            SearchScreen(
                navigateBack = {navController.navigate(Screen.MAIN_MENU.route)}
            )
        }

        composable(route = Screen.SETTINGS.route) {
            SettingsScreen(
                navigateBack = {navController.navigate(Screen.MAIN_MENU.route)}
            )
        }

    }
}