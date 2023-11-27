package ar.edu.utn.frba.mobile.plantscare.ui.main.utils

import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.camera.core.ImageProxy
import java.nio.ByteBuffer

fun imageProxyToBitmap(imageProxy: ImageProxy): Bitmap? {
    val rotationMatrix = Matrix().apply {
        postRotate(90f)
    }

    val buffer: ByteBuffer = imageProxy.planes[0].buffer
    val remaining = buffer.remaining()
    val bytes = ByteArray(remaining)
    buffer.get(bytes)
    val bitmap: Bitmap? = android.graphics.BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    return bitmap?.let { Bitmap.createBitmap(it, 0, 0, bitmap.width, bitmap.height, rotationMatrix, true) }

}