package ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.bottomNavigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.main.Guides
import ar.edu.utn.frba.mobile.plantscare.ui.main.MyPlants
import ar.edu.utn.frba.mobile.plantscare.ui.main.Watering

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object MyPlants : Screen("plants", R.string.my_plants_screen_button, Icons.Default.Home)
    object Watering : Screen("watering", R.string.watering_button, Icons.Default.DateRange)
    object Guides : Screen("guides", R.string.guides_button, Icons.Default.Search)
}

@Composable
fun BottomNavigationGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(navController = navController,
        startDestination = Screen.MyPlants.route,
        Modifier.padding(paddingValues)
    ) {
        composable(route= Screen.MyPlants.route) { MyPlants(navController) }
        composable(route=  Screen.Watering.route) { Watering(navController) }
        composable(route=  Screen.Guides.route) { Guides(navController) }
    }
}