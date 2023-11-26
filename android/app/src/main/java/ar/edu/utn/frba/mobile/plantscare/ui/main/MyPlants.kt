package ar.edu.utn.frba.mobile.plantscare.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.model.PlantInfo
import ar.edu.utn.frba.mobile.plantscare.model.WateringStatus
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.ImageFromUrl
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.APICallState
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.loadScreen
import ar.edu.utn.frba.mobile.plantscare.ui.theme.Grey
import ar.edu.utn.frba.mobile.plantscare.ui.theme.LightGreen50Color
import ar.edu.utn.frba.mobile.plantscare.ui.theme.SoftGreen
import ar.edu.utn.frba.mobile.plantscare.ui.theme.SoftRed
import java.time.LocalDate

@Composable
fun MyPlants(navController: NavHostController, state: APICallState<List<PlantInfo>>) {
    loadScreen(state = state) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            MyPlantsContent(it) {
                navigateToMyPlant(navController, it)
            }
        }
    }
}


fun navigateToMyPlant(navController: NavHostController, id: Int) {
    navController.navigate("plants/$id/info") {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun PlantList(plantsList: List<PlantInfo>, onClickPlant: (id: Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(plantsList) { plant ->
            PlantItem(plant, onClickPlant)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PlantItem(plant: PlantInfo, onClickPlant: (id: Int) -> Unit ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightGreen50Color)
            .padding(8.dp)
            .clickable {
                onClickPlant(plant.id)
            }
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)

        ) {
            ImageFromUrl(url = plant.imageGallery[0], modifier = Modifier
                .fillMaxHeight()
                .width(40.dp)
                .clip(shape = MaterialTheme.shapes.medium))
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
                    // si es 0 -> Today; si es 1 -> Tomorrow; >1 -> in X days
                    Text(text = "tomorrow", style = MaterialTheme.typography.body1, textAlign = TextAlign.Center)
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            (6 downTo 0).map { index ->
                val date = LocalDate.now().minusDays(index.toLong())
                DayOfWeekItem(
                    date = date,
                    status = getStatus(plant, date)
                )
            }
        }
    }
}

@Composable
fun DayOfWeekItem(date: LocalDate, status: WateringStatus) {
    val dayLetter: String = date.dayOfWeek.name[0].toString()

    Column(
        modifier = Modifier
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(getColor(status), shape = CircleShape)
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
private fun MyPlantsContent(myPlantsList: List<PlantInfo> ,onClickPlant: (id: Int) -> Unit) {
    PlantList(myPlantsList, onClickPlant)
}

private fun getColor(status: WateringStatus): Color {
    return when(status) {
        WateringStatus.WATERED -> SoftGreen
        WateringStatus.NOT_WATERED -> SoftRed
        WateringStatus.NEEDS_WATERING -> SoftRed
        else -> Grey
    }
}

private fun getStatus(plantInfo: PlantInfo, date: LocalDate): WateringStatus {
    return plantInfo.history.find { stringToLocalDate(it.date) == date }?.status ?: WateringStatus.NO_INFO
}