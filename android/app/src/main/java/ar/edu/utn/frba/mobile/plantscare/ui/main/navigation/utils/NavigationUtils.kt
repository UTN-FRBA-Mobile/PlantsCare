package ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.utils

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.bottomNavigation.Screen

fun isDestination(currentDestination: NavDestination?, route: String) =
    currentDestination?.route?.contains(route) == true

fun getRouteToNavigate(
    screen: Screen,
    navBackStackEntry: NavBackStackEntry?
) = screen.route.replace("{id}", navBackStackEntry?.arguments?.getString("id") ?: "0")
