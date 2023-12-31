package ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.bottomNavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.utils.getRouteToNavigate
import ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.utils.isDestination
import ar.edu.utn.frba.mobile.plantscare.ui.theme.DarkGreen500Color
import ar.edu.utn.frba.mobile.plantscare.ui.theme.navBarBackgroundColor

val mainBottomNavigationItems = listOf(
    Screen.MyPlants,
    Screen.Watering,
    Screen.NewPlant,
    Screen.Guides,
    Screen.Profile
)

val myPlantNavigationItems = listOf(
    Screen.MyPlantInfo,
    Screen.History,
    Screen.WateringFrequency,
)

private fun getButtonsToRender(currentDestination: NavDestination?) =
    when {
        isDestination(currentDestination, MyPlantBaseRoute) -> myPlantNavigationItems
        isDestination(currentDestination, Screen.Login.route) -> listOf()
        else -> mainBottomNavigationItems
    }


@Composable
fun BottomNavigation(
    navController: NavHostController,
) {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = navBarBackgroundColor,
        contentColor = DarkGreen500Color,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            getButtonsToRender(currentDestination).forEach { screen ->
                BottomNavigationItem(
                    icon = { Icon(screen.icon, contentDescription = null) },
                    label = { Text(stringResource(screen.resourceId), color = Color.Gray) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(getRouteToNavigate(screen, navBackStackEntry)) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}


