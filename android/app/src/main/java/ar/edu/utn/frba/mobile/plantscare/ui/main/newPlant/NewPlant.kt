package ar.edu.utn.frba.mobile.plantscare.ui.main.newPlant

import android.util.Log
import androidx.camera.core.ImageProxy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.main.newPlant.camera.CameraImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
@Composable
fun NewPlant(navController: NavHostController) {
    val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    var imageData by remember { mutableStateOf<ImageProxy?>(null) }
    if(imageData?.image == null) {
        CameraImage(
            hasPermission = cameraPermissionState.status.isGranted,
            onRequestPermission = cameraPermissionState::launchPermissionRequest,
            cameraTextButton = R.string.add_plant_camera_button,
            onImageCaptured =  { imageProxy ->
                imageData = imageProxy
                Log.i("CameraContent", "Image Captured")
                Log.i("CameraContent", imageProxy.imageInfo.timestamp.toString())
                    //  navController.navigate("plants")
            }
        )
    }
    else {
        NewPlantMetadata(navController, imageData as ImageProxy)
    }
}

