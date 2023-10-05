package ar.edu.utn.frba.mobile.plantscare.ui.main.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter

@Composable
fun ImageFromUrl(url: String, modifier: Modifier = Modifier) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            crossfade(true)
        }
    )

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .fillMaxSize()
    )
}