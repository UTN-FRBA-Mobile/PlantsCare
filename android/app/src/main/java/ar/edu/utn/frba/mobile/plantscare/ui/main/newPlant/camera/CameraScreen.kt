package ar.edu.utn.frba.mobile.plantscare.ui.main.newPlant.camera
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import ar.edu.utn.frba.mobile.plantscare.ui.theme.LightGreen500Color
import ar.edu.utn.frba.mobile.plantscare.ui.theme.WhiteColor
import java.util.concurrent.Executor


@Composable
fun CameraScreen(
    onImageCaptured: (ImageProxy) -> Unit,
    @StringRes textButton: Int
) {
    CameraContent(onImageCaptured, textButton)
}

@Composable
private fun CameraContent(
    onImageCaptured: (ImageProxy) -> Unit,
    @StringRes textButton: Int
) {

    val context: Context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val cameraController: LifecycleCameraController = remember { LifecycleCameraController(context) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton( //taking a photo component
                text = { Text(text = stringResource(textButton)) },
                onClick = { capturePhoto(context, onImageCaptured, cameraController) },
                icon = { Icon(imageVector = Icons.Default.AddCircle, contentDescription = stringResource(textButton)) },
                backgroundColor = WhiteColor,
                contentColor = LightGreen500Color
            )
        }
    ) { paddingValues: PaddingValues ->
        CameraView(cameraController, lifecycleOwner , paddingValues)
    }
}
@Composable
private fun CameraView(cameraController: LifecycleCameraController,
                       lifecycleOwner: LifecycleOwner,
                       paddingValues: PaddingValues) {
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            factory = { context ->
                PreviewView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    setBackgroundColor(Color.BLACK)
                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                    scaleType = PreviewView.ScaleType.FILL_START
                }.also { previewView ->
                    previewView.controller = cameraController
                    cameraController.bindToLifecycle(lifecycleOwner)
                }
            }
        )
    }
}

private fun capturePhoto(
    context: Context,
    onImageCaptured: (ImageProxy) -> Unit,
    cameraController: LifecycleCameraController,
) {
    val mainExecutor: Executor = ContextCompat.getMainExecutor(context)

    cameraController.takePicture(mainExecutor, object : ImageCapture.OnImageCapturedCallback() {
        override fun onCaptureSuccess(image: ImageProxy) {
            onImageCaptured(image)
        }

        override fun onError(exception: ImageCaptureException) {
            Log.e("CameraContent", "Error capturing image", exception)
        }
    })
}