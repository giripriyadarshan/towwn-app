package com.giripriyadarshan.towwn.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.giripriyadarshan.towwn.lib.NavItem
import com.giripriyadarshan.towwn.screens.AboutScreen
import com.giripriyadarshan.towwn.screens.MainScreen


@Composable
fun NavBar(navController: NavHostController) {
    val items = listOf(
        NavItem.Home,
        NavItem.About
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        items.forEach { item ->
            NavigationBarItem(
                label = { Text(item.label, color = MaterialTheme.colorScheme.onSurface) },
                icon = {
                    if (item.route == "home") {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    } else {
                        Icon(Icons.Filled.Info, contentDescription = "About")
                    }
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}

@Composable
fun NavActivity(navController: NavHostController, openWhatsappContact: (String, Context) -> Unit) {
    NavHost(
        navController,
        startDestination = NavItem.Home.route,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)

    ) {
        composable("home") { MainScreen(openWhatsappContact) }
        composable("about") { AboutScreen() }
    }
}