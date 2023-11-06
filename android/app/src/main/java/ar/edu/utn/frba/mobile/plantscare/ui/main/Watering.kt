package ar.edu.utn.frba.mobile.plantscare.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.model.Plant
import ar.edu.utn.frba.mobile.plantscare.model.PlantProperties
import ar.edu.utn.frba.mobile.plantscare.model.WateringData
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.APICallState
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.loadScreen
import ar.edu.utn.frba.mobile.plantscare.ui.theme.LightGreen50Color
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun Watering(navController: NavHostController, state: APICallState<List<WateringData>>) {
    loadScreen(state) {
        MyWateringScreen(it)
    }
}
@Composable
fun MyWateringScreen(wateringData: List<WateringData>) {
    val today = LocalDate.now()
    val allDays = (-7..7).map { today.plusDays(it.toLong()) }
    val rowScrollState = rememberScrollState()
    val sizeInPx = with(LocalDensity.current) { 40.dp.toPx() }.toInt() * 15 / 4
    LaunchedEffect(Unit) { rowScrollState.animateScrollTo(sizeInPx)}
    val columnScrollState = rememberScrollState()
    var selectedDate by remember {
        mutableStateOf(today)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(LightGreen50Color)
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        ) {
            Text(text = "Watering Calendar")
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .horizontalScroll(rowScrollState)
                    .padding(top = 10.dp)
            ) {
                for (day in allDays) {
                    DayBox(day, selectedDate) {
                        selectedDate = it
                    }
                }
            }


            Text(text = selectedDate.toString())


            Column (
                modifier = Modifier
                    .padding(top = 10.dp)
                    .verticalScroll(columnScrollState)
                    .weight(1f)
            ) {
                WateringItem()
                WateringItem()
                WateringItem()
                WateringItem()
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun DayBox(day: LocalDate, selectedDate: LocalDate, onDateSelected: (LocalDate) -> Unit) {
    val today = LocalDate.now().format(DateTimeFormatter.ofPattern("d"))
    val dayText = day.format(DateTimeFormatter.ofPattern("d"))
    val selectedDateText = selectedDate.format(DateTimeFormatter.ofPattern("d"))
    val backgroundColor =
        if (dayText == selectedDateText) {
            MaterialTheme.colors.primaryVariant
        } else if (dayText == today) {
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
            .clickable {
                onDateSelected(day)
            }
    ) {
        Text(
            text = dayText,
            color = MaterialTheme.colors.surface
        )
    }
}

@Composable
fun WateringItem()
{
    Row (
        modifier = Modifier.fillMaxWidth()
    ){
        Card(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
                .height(125.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp,
            backgroundColor = Color.White
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = painterResource(id = R.drawable.default_plant),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                        .clip(shape = MaterialTheme.shapes.medium)
                )
                Text(text = "hola")
            }
        }

        Button(
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            onClick = {
            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(10.dp)
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.baseline_water_drop_24),
                contentDescription = "water drop logo"
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WateringPreview() {
    val exampleData = listOf(
        WateringData(
            date = "2023-11-19T00:00:00.000Z",
            plants = listOf(
                Plant(
                    id = 1,
                    name = "my plant",
                    type = "Hyacinth",
                    description = "Water hyacinth (Eichhornia crassipes) is a fast-growing flowering plant species with ovular, waxy leaves...",
                    currentWateringFrequency = 3,
                    properties = PlantProperties(
                        size = "SMALL",
                        environment = "INDOOR",
                        sunExposure = "HIGH",
                        difficulty = "HARD"
                    ),
                    imageGallery = listOf("https://i.imgur.com/FioQT6e.jpeg")
                )
            )
        )
    )
    val successState = APICallState.Success(exampleData)
    Watering(navController = rememberNavController(), state = successState)
}