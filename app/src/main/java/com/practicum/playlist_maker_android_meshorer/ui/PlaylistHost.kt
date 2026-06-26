package com.practicum.playlist_maker_android_meshorer.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.practicum.playlist_maker_android_meshorer.ui.main.Main_screen
import com.practicum.playlist_maker_android_meshorer.ui.playlist.PlaylistScreen
import com.practicum.playlist_maker_android_meshorer.ui.playlist.PlaylistViewModel
import com.practicum.playlist_maker_android_meshorer.ui.search.SearchScreen
import com.practicum.playlist_maker_android_meshorer.ui.settings.SettingsScreen


enum class Screen(val route: String) {
    MAIN_MENU("main_menu"),
    SEARCH("search"),
    SETTINGS("settings"),

    PLAYLISTS("playlists")
}

@Composable
fun PlaylistHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.MAIN_MENU.route
        ) {
        composable(route = Screen.MAIN_MENU.route) {
            Main_screen(
                navigateToSearch = { navController.navigate(Screen.SEARCH.route) },
                navigateToSettings = { navController.navigate(Screen.SETTINGS.route) },
                navigateToPlaylists = { navController.navigate(Screen.PLAYLISTS.route)}
            )
        }

        composable(route = Screen.SEARCH.route) {
            SearchScreen(
                navigateBack = { navController.navigate(Screen.MAIN_MENU.route) },
                onClick = {index -> if (index == null) navController.popBackStack()},
                modifier = Modifier
            )
        }

        composable(route = Screen.SETTINGS.route) {
            SettingsScreen(
                navigateBack = { navController.navigate(Screen.MAIN_MENU.route) }
            )
        }

        composable(route = Screen.PLAYLISTS.route) {
            val playlistViewModel: PlaylistViewModel = viewModel()
            PlaylistScreen(
                navigateBack = { navController.navigate(Screen.MAIN_MENU.route) },
                playlistsViewModel = playlistViewModel
            )
        }

    }
}