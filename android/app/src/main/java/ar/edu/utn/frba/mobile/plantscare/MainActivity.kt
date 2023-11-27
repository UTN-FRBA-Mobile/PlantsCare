package ar.edu.utn.frba.mobile.plantscare

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import ar.edu.utn.frba.mobile.plantscare.services.ApplicationViewModel
import ar.edu.utn.frba.mobile.plantscare.ui.main.navigation.AppScaffold

import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import ar.edu.utn.frba.mobile.plantscare.services.ProfileViewModel

class MainActivity : ComponentActivity() {
  private val appViewModel: ApplicationViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      App(appViewModel)
    }
    prepLocationUpdates()
  }

  private fun prepLocationUpdates() {
    if (ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
      ) == PackageManager.PERMISSION_GRANTED
    ) { requestLocationUpdates() }
    else { requestSinglePermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION) }
  }

  private val requestSinglePermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
    isGranted ->
      if(isGranted) { requestLocationUpdates() }
    else { }
  }

  private fun requestLocationUpdates() {
    appViewModel.startLocationUpdates()
  }
}


@Composable
private fun App(applicationViewModel: ApplicationViewModel) {
  val navController = rememberNavController()
  AppScaffold(navController = navController, applicationViewModel)
}