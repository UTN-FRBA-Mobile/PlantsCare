package ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.topNavigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.theme.DarkGreen500Color

@Composable
fun MainTopNavigation(navController: NavHostController) {
    Text(text = stringResource(id = R.string.app_name), color = DarkGreen500Color)
}