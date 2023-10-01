package ar.edu.utn.frba.mobile.plantscare.ui.main.newPlant

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.main.newPlant.camera.CameraImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NewPlant(navController: NavHostController) {
    val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    CameraImage(
        hasPermission = cameraPermissionState.status.isGranted,
        onRequestPermission = cameraPermissionState::launchPermissionRequest,
        cameraTextButton = R.string.add_plant_camera_button,
        onImageCaptured =  { imageProxy ->
            Log.i("CameraContent", "Image Captured")
            Log.i("CameraContent", imageProxy.imageInfo.timestamp.toString())
            imageProxy.close()
            navController.navigate("plants")
        }
    )
}

