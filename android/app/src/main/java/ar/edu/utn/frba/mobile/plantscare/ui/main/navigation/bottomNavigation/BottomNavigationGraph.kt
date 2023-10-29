package ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.bottomNavigation

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.main.Guides
import ar.edu.utn.frba.mobile.plantscare.ui.main.Login
import ar.edu.utn.frba.mobile.plantscare.ui.main.MyPlants
import ar.edu.utn.frba.mobile.plantscare.ui.main.Profile
import ar.edu.utn.frba.mobile.plantscare.ui.main.Watering
import ar.edu.utn.frba.mobile.plantscare.ui.main.myPlantView.ImageGallery
import ar.edu.utn.frba.mobile.plantscare.ui.main.myPlantView.MyPlantInfoView
import ar.edu.utn.frba.mobile.plantscare.ui.main.myPlantView.PlantHistory
import ar.edu.utn.frba.mobile.plantscare.ui.main.myPlantView.WateringFrequency
import ar.edu.utn.frba.mobile.plantscare.ui.main.newPlant.NewPlant

sealed class MainScreen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {

    object Login: MainScreen("login", R.string.my_plants_screen_button, Icons.Default.Home)
    object MyPlants : MainScreen("plants", R.string.my_plants_screen_button, Icons.Default.Home)
    object NewPlant : MainScreen("newPlant", R.string.new_plant_button, Icons.Default.Add)
    object Watering : MainScreen("watering", R.string.watering_button, Icons.Default.DateRange)
    object Guides : MainScreen("guides", R.string.guides_button, Icons.Default.Search)
    object Profile : MainScreen("profile", R.string.profile_button, Icons.Default.Person)
}

sealed class MyPlantScreen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object MyPlantInfo : MainScreen("plants/{id}/info", R.string.my_plant_info, Icons.Default.Person)
    object ImageGallery : MainScreen("plants/{id}/gallery", R.string.my_plant_gallery, Icons.Default.Person)
    object History : MainScreen("plants/{id}/history", R.string.my_plant_history, Icons.Default.Person)
    object WateringFrequency : MainScreen("plants/{id}/watering", R.string.my_plant_watering, Icons.Default.Person)
}

@Composable
fun BottomNavigationGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(navController = navController,
        startDestination = MainScreen.Login.route,
        Modifier.padding(paddingValues)
    ) {
        composable(route= MainScreen.Login.route) { Login(navController) }
        composable(route= MainScreen.MyPlants.route) { MyPlants(navController) }
        composable(route= MainScreen.NewPlant.route) { NewPlant(navController) }
        composable(route= MainScreen.Watering.route) { Watering(navController) }
        composable(route= MainScreen.Guides.route) { Guides(navController) }
        composable(route= MainScreen.Profile.route) { Profile(navController) }
        composable(route= MyPlantScreen.MyPlantInfo.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            id?.let { Log.i("ID", it) }
            MyPlantInfoView(navController)
        }
        composable(route= MyPlantScreen.ImageGallery.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            id?.let { Log.i("ID", it) }
            ImageGallery(navController)
        }
        composable(route= MyPlantScreen.History.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            id?.let { Log.i("ID", it) }
            PlantHistory(navController)
        }
        composable(route= MyPlantScreen.WateringFrequency.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            id?.let { Log.i("ID", it) }
            WateringFrequency()
        }
    }
}