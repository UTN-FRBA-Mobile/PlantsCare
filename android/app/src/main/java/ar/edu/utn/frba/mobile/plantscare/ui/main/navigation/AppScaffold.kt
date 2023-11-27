package ar.edu.utn.frba.mobile.plantscare.ui.main.navigation

import android.content.Context
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.ui.main.login.signIn.GoogleAuthUiClient
import ar.edu.utn.frba.mobile.plantscare.services.ApplicationViewModel
import ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.bottomNavigation.BottomNavigation
import ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.bottomNavigation.BottomNavigationGraph
import ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.topNavigation.TopNavigation
import ar.edu.utn.frba.mobile.plantscare.ui.theme.AppTheme


@Composable
fun AppScaffold(
    navController: NavHostController,
    auth: GoogleAuthUiClient,
    context: Context,
    applicationViewModel: ApplicationViewModel
) {
    AppTheme {
        Scaffold(
            topBar = { TopNavigation(navController) },
            bottomBar = { BottomNavigation(navController) },
        ){
            paddingValues -> BottomNavigationGraph(navController, paddingValues, auth, context, applicationViewModel)
        }
    }
}
