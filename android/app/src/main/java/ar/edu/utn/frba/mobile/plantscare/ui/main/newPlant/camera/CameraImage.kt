package ar.edu.utn.frba.mobile.plantscare.ui.main.newPlant.camera

import androidx.annotation.StringRes
import androidx.camera.core.ImageProxy
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.utn.frba.mobile.plantscare.R

@Composable
fun CameraImage(
    hasPermission: Boolean,
    @StringRes cameraTextButton: Int,
    onRequestPermission: () -> Unit,
    onImageCaptured: (ImageProxy) -> Unit,
) {

    if (hasPermission) {
        CameraScreen(onImageCaptured, cameraTextButton)
    } else {
        NoPermissionScreen(onRequestPermission)
    }
}

@Preview
@Composable
private fun PreviewCameraPlant() {
    CameraImage(
        hasPermission = true,
        onRequestPermission = {},
        onImageCaptured = {},
        cameraTextButton = R.string.add_plant_camera_button
    )
}
