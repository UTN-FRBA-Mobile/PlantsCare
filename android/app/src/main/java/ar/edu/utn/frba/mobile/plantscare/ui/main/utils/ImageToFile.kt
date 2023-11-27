package ar.edu.utn.frba.mobile.plantscare.ui.main.utils

import android.content.Context
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.util.Date
import java.util.Locale


fun BitmapToFile(context: Context, bitmap: Bitmap): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val fileName = "IMG_$timeStamp.jpg"
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

    val imageFile = File(storageDir, fileName)
    val fileOutputStream = FileOutputStream(imageFile)
    bitmap!!.compress(Bitmap.CompressFormat.JPEG, 10, fileOutputStream)
    fileOutputStream.close()
    return imageFile

}