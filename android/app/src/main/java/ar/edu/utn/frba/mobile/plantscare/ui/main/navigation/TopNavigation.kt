package ar.edu.utn.frba.mobile.plantscare.ui.main.navigation

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.theme.GreenPlant

@Composable
fun TopNavigation(navController: NavHostController) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name), color = GreenPlant)
        }
    )
}