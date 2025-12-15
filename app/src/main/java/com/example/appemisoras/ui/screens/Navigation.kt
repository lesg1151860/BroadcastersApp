package com.example.appemisoras.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appemisoras.R

sealed class AppScreens(
    val route: String,
    val title: Int? = null,
    val icon: Int? = null
) {
    object Home : AppScreens("home", R.string.home, R.drawable.ic_home)
    object Search : AppScreens("search", R.string.search, R.drawable.ic_search)
    object Library : AppScreens("library", R.string.library, R.drawable.ic_library)
    object Account : AppScreens("account", R.string.account, R.drawable.ic_account)
    object Player : AppScreens("player")
}

val items = listOf(
    AppScreens.Home,
    AppScreens.Search,
    AppScreens.Library,
    AppScreens.Account,
)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val stationsViewModel: StationsViewModel = viewModel()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = when (currentDestination?.route) {
        AppScreens.Player.route -> false
        else -> true
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = Color.Black
                ) {
                    items.forEach { screen ->
                        NavigationBarItem(
                            icon = { screen.icon?.let { Icon(painterResource(id = it), contentDescription = screen.title?.let { title -> stringResource(id = title) }) } },
                            label = { screen.title?.let { Text(stringResource(id = it)) } },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                unselectedIconColor = Color.Gray,
                                selectedTextColor = Color.White,
                                unselectedTextColor = Color.Gray
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppScreens.Home.route) { HomeScreen(navController, stationsViewModel) }
            composable(AppScreens.Search.route) { SearchScreen(navController, stationsViewModel) }
            composable(AppScreens.Library.route) { BibliotecaScreen(navController, stationsViewModel) }
            composable(AppScreens.Account.route) { TuCuentaScreen() }
            composable(AppScreens.Player.route) { PlayerScreen(onBackClick = { navController.popBackStack() }, stationsViewModel) }
        }
    }
}