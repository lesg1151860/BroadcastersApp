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
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appemisoras.R
import com.example.appemisoras.ui.screens.BibliotecaScreen

sealed class AppScreens(
    val route: String,
    val title: Int,
    val icon: Int
) {
    object Home : AppScreens("home", R.string.home, R.drawable.ic_home)
    object Search : AppScreens("search", R.string.search, R.drawable.ic_search)
    object Library : AppScreens("library", R.string.library, R.drawable.ic_library)
    object Account : AppScreens("account", R.string.account, R.drawable.ic_account)
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
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.Black
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(painterResource(id = screen.icon), contentDescription = stringResource(id = screen.title)) },
                        label = { Text(stringResource(id = screen.title)) },
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
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppScreens.Home.route) { HomeScreen() }
            composable(AppScreens.Search.route) { SearchScreen() }
            composable(AppScreens.Library.route) { BibliotecaScreen() }
            composable(AppScreens.Account.route) { TuCuentaScreen() }
        }
    }
}