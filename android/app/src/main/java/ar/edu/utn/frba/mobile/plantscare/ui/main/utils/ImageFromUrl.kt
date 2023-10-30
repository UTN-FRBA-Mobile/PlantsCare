package ar.edu.utn.frba.mobile.plantscare.ui.main.utils

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter

@Composable
fun ImageFromUrl(url: String,
                 modifier: Modifier = Modifier,
                 contentScale: ContentScale = ContentScale.Crop,
                 alignment: Alignment = Alignment.Center) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            crossfade(true)
        }
    )

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale,
        alignment = alignment
    )
}