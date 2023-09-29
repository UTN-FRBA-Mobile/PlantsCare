package ar.edu.utn.frba.mobile.plantscare.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.theme.AppTheme
import ar.edu.utn.frba.mobile.plantscare.ui.theme.GreenPlant

@Composable
fun AppScaffold(
    title: String? = null,
    navController: NavController? = null,
    content: @Composable (PaddingValues) -> Unit) {
    val navigationIcon: (@Composable () -> Unit)? =
        if (navController?.previousBackStackEntry != null) {
            {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icons.Filled.ArrowBack
                }
            }
        } else null
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = title ?: stringResource(id = R.string.app_name), color = GreenPlant)
                    },
                    navigationIcon = navigationIcon,
                )
            },
            content = {

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    content(it)
                }
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        BottomNavigationItem(
                            icon = { Icon(Icons.Default.Home, contentDescription = "Plants") },
                            label = { Text(text = "Plants") },
                            selected = true,
                            onClick = { /* Handle click */ }
                        )

                        BottomNavigationItem(
                            icon = { Icon(Icons.Default.DateRange, contentDescription = "Watering") },
                            label = { Text(text = "Watering") },
                            selected = false,
                            onClick = { /* Handle click */ }
                        )

                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    Icons.Default.AddCircle,
                                    contentDescription = "New Plant"
                                )
                            },
                            label = { Text(text = " ") },
                            selected = false,
                            onClick = { /* Handle click */ }
                        )

                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    Icons.Default.Search,
                                    contentDescription = "Guides"
                                )
                            },
                            label = { Text(text = "Guides") },
                            selected = false,
                            onClick = { /* Handle click */ }
                        )
                    }
                }
            }
        )
    }
}