package ar.edu.utn.frba.mobile.plantscare.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.theme.SalmonColor
import ar.edu.utn.frba.mobile.plantscare.ui.theme.SoftGreen
import ar.edu.utn.frba.mobile.plantscare.ui.theme.SoftRed

data class Plant(
    val name: String,
    val imageResId: Int,
    val wateringDays: List<Boolean>
)

val myPlantsList = listOf(
    Plant("Planta 1", R.drawable.default_plant, listOf(true, false, true, true, false, true, false)),
    Plant("Planta 2", R.drawable.default_plant, listOf(true, true, false, false, true, true, true)),
    Plant("Planta 3", R.drawable.default_plant, listOf(true, true, false, false, true, true, true)),
    Plant("Planta 4", R.drawable.default_plant, listOf(true, true, false, false, true, true, true)),
    Plant("Planta 5", R.drawable.default_plant, listOf(true, true, false, false, true, true, true)),
    Plant("Planta 6", R.drawable.default_plant, listOf(true, true, false, false, true, true, true))
)

@Composable
fun MyPlants(navController: NavHostController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        ShowPreview()
    }
}

@Composable
fun PlantList(plantsList: List<Plant>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(plantsList) { plant ->
            PlantItem(plant)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PlantItem(plant: Plant) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SalmonColor)
            .padding(8.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Image(
                painter = painterResource(id = plant.imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(40.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Text(text = plant.name, style = MaterialTheme.typography.h6)
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.waterdrop),
                        contentDescription = "tomorrow",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(24.dp)
                            .height(24.dp)
                            .clip(shape = MaterialTheme.shapes.medium)
                    )
                    Text(text = "tomorrow", style = MaterialTheme.typography.body1, textAlign = TextAlign.Center)
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            plant.wateringDays.forEachIndexed { index, isWatered ->
                DayOfWeekItem(
                    dayOfWeek = index + 1,
                    isWatered = isWatered
                )
            }
        }
    }
}

@Composable
fun DayOfWeekItem(dayOfWeek: Int, isWatered: Boolean) {
    val dayLetters = listOf("S", "M", "T", "W", "T", "F", "S")
    val dayLetter = dayLetters[dayOfWeek - 1]

    Column(
        modifier = Modifier
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(if (isWatered) SoftGreen else SoftRed, shape = CircleShape)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = dayLetter,
                style = MaterialTheme.typography.caption.copy(color = Color.Black)
            )
        }
    }
}

@Composable
@Preview
private fun ShowPreview() {
    PlantList(plantsList = myPlantsList)
}