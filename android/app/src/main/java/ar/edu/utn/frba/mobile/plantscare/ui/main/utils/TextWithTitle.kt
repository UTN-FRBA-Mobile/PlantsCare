package ar.edu.utn.frba.mobile.plantscare.ui.main.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.utn.frba.mobile.plantscare.ui.theme.SalmonColor

@Composable
fun TextWithTitle(title: String, text: String, columnModifier: Modifier = Modifier) {
    Column (modifier = columnModifier) {
        Text(text = title, style = MaterialTheme.typography.overline.copy(color = Color.Gray))
        Text(text = text, style = MaterialTheme.typography.body2.copy(color = Color.Black))
    }
}


@Preview
@Composable
fun Preview() {
    Box(modifier = Modifier.background(color = SalmonColor).fillMaxSize()) {
        TextWithTitle(title = "Prueba", text = "Esto es una Prueba")
    }
}