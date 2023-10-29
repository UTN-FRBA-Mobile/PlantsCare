package ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.topNavigation

import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.bottomNavigation.MyPlantBaseRoute
import ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.utils.isDestination
import ar.edu.utn.frba.mobile.plantscare.ui.theme.navBarBackgroundColor

@Composable
fun TopNavigation(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    TopAppBar(
        backgroundColor = navBarBackgroundColor,
        title = {
            when {
                isDestination(currentDestination, MyPlantBaseRoute) -> MyPlantTopNavigation(navController)
                else -> MainTopNavigation(navController)
            }
        }
    )
}