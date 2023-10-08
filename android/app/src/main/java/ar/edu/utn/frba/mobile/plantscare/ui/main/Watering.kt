package ar.edu.utn.frba.mobile.plantscare.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.theme.LightGreen50Color
import java.time.LocalDate
import java.time.format.DateTimeFormatter


// Models: Esto despues
data class mPlant(
    val name: String,
    val imageResId: Int,
    val wateringDays: List<Boolean>
)

val mmyPlantsList = listOf(
    Plant("Planta 1", R.drawable.default_plant, listOf(true, false, true, true, false, true, false)),
    Plant("Planta 2", R.drawable.default_plant, listOf(true, true, false, false, true, true, true)),
    Plant("Planta 3", R.drawable.default_plant, listOf(true, true, false, false, true, true, true)),
    Plant("Planta 4", R.drawable.default_plant, listOf(true, true, false, false, true, true, true)),
    Plant("Planta 5", R.drawable.default_plant, listOf(true, true, false, false, true, true, true)),
    Plant("Planta 6", R.drawable.default_plant, listOf(true, true, false, false, true, true, true))
)

@Composable
fun Watering(navController: NavHostController) {
    val today = LocalDate.now()
    val allDays = (-7..7).map { today.plusDays(it.toLong()) }
    val scrollState = rememberScrollState()
    val sizeInPx = with(LocalDensity.current) { 40.dp.toPx() }.toInt() * 15 / 4
    LaunchedEffect(Unit) { scrollState.animateScrollTo(sizeInPx)}

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightGreen50Color)
                .padding(8.dp)
        ) {
            Text(text = "Watering")
            Row(
                modifier = Modifier.horizontalScroll(scrollState),
                horizontalArrangement = Arrangement.Center,
            ) {
                for (day in allDays) {
                    DayBox(day.format(DateTimeFormatter.ofPattern("d")))
                    Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun DayBox(dayText: String) {
    val today = LocalDate.now().format(DateTimeFormatter.ofPattern("d"))
    val backgroundColor =
        if (dayText == today) {
            MaterialTheme.colors.primary
        } else if (dayText.toInt() % 2 == 0) {
            MaterialTheme.colors.secondary
        } else {
            MaterialTheme.colors.secondaryVariant
        }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(backgroundColor)
            .size(40.dp)
    ) {
        Text(
            text = dayText,
            color = MaterialTheme.colors.surface
        )
    }
}

@Composable
@Preview(showBackground = true)
fun WateringPreview() {
    Watering(navController = rememberNavController())
}