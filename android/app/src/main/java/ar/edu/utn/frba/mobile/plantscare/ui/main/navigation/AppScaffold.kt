package ar.edu.utn.frba.mobile.plantscare.ui.main.navigation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.bottomNavigation.BottomNavigation
import ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.bottomNavigation.BottomNavigationGraph
import ar.edu.utn.frba.mobile.plantscare.ui.theme.AppTheme


@Composable
fun AppScaffold(navController: NavHostController) {
    AppTheme {
        Scaffold(
            topBar = { TopNavigation(navController) },
            bottomBar = { BottomNavigation(navController) }
        ){
            paddingValues -> BottomNavigationGraph(navController, paddingValues)
        }
    }
}
