package ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.topNavigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.theme.DarkGreen500Color

@Composable
fun MyPlantTopNavigation(navController: NavHostController) {
    Row (Modifier.clickable { navController.popBackStack() } ){
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null,  tint = DarkGreen500Color)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = stringResource(id = R.string.my_plant_info), color = DarkGreen500Color)
    }
}