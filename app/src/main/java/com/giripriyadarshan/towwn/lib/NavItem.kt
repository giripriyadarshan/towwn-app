package com.giripriyadarshan.towwn.lib

sealed class NavItem(val route: String, val label: String) {
    object Home : NavItem("home", "Home")
    object About : NavItem("about", "About")
}
