package ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.bottomNavigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.EnergySavingsLeaf
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.network.PlantsClient
import ar.edu.utn.frba.mobile.plantscare.services.GuidesViewModel
import ar.edu.utn.frba.mobile.plantscare.services.MyPlantViewModel
import ar.edu.utn.frba.mobile.plantscare.services.MyPlantsViewModel
import ar.edu.utn.frba.mobile.plantscare.services.ProfileViewModel
import ar.edu.utn.frba.mobile.plantscare.services.WateringViewModel
import ar.edu.utn.frba.mobile.plantscare.ui.main.Guides
import ar.edu.utn.frba.mobile.plantscare.ui.main.Login
import ar.edu.utn.frba.mobile.plantscare.ui.main.MyPlants
import ar.edu.utn.frba.mobile.plantscare.ui.main.Profile
import ar.edu.utn.frba.mobile.plantscare.ui.main.Watering
import ar.edu.utn.frba.mobile.plantscare.ui.main.guide.Guide
import ar.edu.utn.frba.mobile.plantscare.ui.main.myPlant.MyPlantInfoView
import ar.edu.utn.frba.mobile.plantscare.ui.main.myPlant.WateringFrequency
import ar.edu.utn.frba.mobile.plantscare.ui.main.myPlant.history.PlantHistory
import ar.edu.utn.frba.mobile.plantscare.ui.main.newPlant.NewPlant

val MyPlantBaseRoute = "plants/{id}"
sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {

    object Login: Screen("login", R.string.my_plants_screen_button, Icons.Default.Home)
    object MyPlants : Screen("plants", R.string.my_plants_screen_button, Icons.Default.Home)
    object NewPlant : Screen("newPlant", R.string.new_plant_button, Icons.Default.Add)
    object Watering : Screen("watering", R.string.watering_button, Icons.Default.DateRange)
    object Guides : Screen("guides", R.string.guides_button, Icons.Default.Search)
    object GuidesArticle : Screen("guideArticle", R.string.guides_button, Icons.Default.Search)
    object Profile : Screen("profile", R.string.profile_button, Icons.Default.Person)
    object MyPlantInfo : Screen("${MyPlantBaseRoute}/info", R.string.my_plant_info, Icons.Default.EnergySavingsLeaf)
    object History : Screen("${MyPlantBaseRoute}/history", R.string.my_plant_history, Icons.Default.WaterDrop)
    object WateringFrequency : Screen("${MyPlantBaseRoute}/frequency", R.string.my_plant_frequency, Icons.Default.Timeline)
}

@Composable
fun BottomNavigationGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {

    NavHost(navController = navController,
        startDestination = Screen.Login.route,
        Modifier.padding(paddingValues)
    ) {
        composable(route= Screen.Login.route) { Login(navController) }
        composable(route= Screen.MyPlants.route) {
            MyPlants(navController, viewModel<MyPlantsViewModel>().state)
        }
        composable(route= Screen.NewPlant.route) { NewPlant(navController) }
        composable(route= Screen.Watering.route) {
            Watering(navController, viewModel<WateringViewModel>().state)
        }
        composable(route= Screen.Guides.route) { 
            Guides(navController, viewModel<GuidesViewModel>().state)
        }
        composable(route= Screen.GuidesArticle.route) {
            Guide(navController)
        }
        composable(route= Screen.Profile.route) {
            Profile(navController, viewModel<ProfileViewModel>().state)
        }
        composable(route= Screen.MyPlantInfo.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
            val myPlantViewModel = viewModel<MyPlantViewModel>()
            myPlantViewModel.setId(id) {
                PlantsClient.myPlant.getPlantById(it)
            }
            MyPlantInfoView(myPlantViewModel.state)
        }
        composable(route= Screen.History.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
            val myPlantViewModel = viewModel<MyPlantViewModel>()
            myPlantViewModel.setId(id) {
                PlantsClient.myPlant.getPlantById(it)
            }
            PlantHistory(myPlantViewModel.state)
        }
        composable(route= Screen.WateringFrequency.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
            val myPlantViewModel = viewModel<MyPlantViewModel>()
            myPlantViewModel.setId(id) {
                PlantsClient.myPlant.getPlantById(it)
            }
            WateringFrequency(myPlantViewModel.state)
        }
    }
}