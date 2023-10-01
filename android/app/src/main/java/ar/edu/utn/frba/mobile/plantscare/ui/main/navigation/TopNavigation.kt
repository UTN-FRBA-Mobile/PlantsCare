package ar.edu.utn.frba.mobile.plantscare.ui.main.navigation

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.theme.DarkGreen500Color
import ar.edu.utn.frba.mobile.plantscare.ui.theme.navBarBackgroundColor

@Composable
fun TopNavigation(navController: NavHostController) {
    TopAppBar(
        backgroundColor = navBarBackgroundColor,
        title = {
            Text(text = stringResource(id = R.string.app_name), color = DarkGreen500Color)
        }
    )
}