package ar.edu.utn.frba.mobile.plantscare.ui.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.model.Plant
import ar.edu.utn.frba.mobile.plantscare.model.PlantProperties
import ar.edu.utn.frba.mobile.plantscare.model.WateringData
import ar.edu.utn.frba.mobile.plantscare.model.WateringRequest
import ar.edu.utn.frba.mobile.plantscare.network.PlantsClient
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.ImageFromUrl
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.APICallState
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.loadScreen
import ar.edu.utn.frba.mobile.plantscare.ui.theme.Blue
import ar.edu.utn.frba.mobile.plantscare.ui.theme.DarkGreen500Color
import ar.edu.utn.frba.mobile.plantscare.ui.theme.LightGreen500Color
import ar.edu.utn.frba.mobile.plantscare.ui.theme.LightGreen50Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun stringToLocalDate(timestampAsDateString: String): LocalDate? {
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
    return try {
        val instant = Instant.from(format.parse(timestampAsDateString))
        instant.atZone(ZoneOffset.UTC).toLocalDate()
    } catch (e: Exception) {
        // Handle parsing errors or return null
        null
    }
}
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
    val sizeInPx = with(LocalDensity.current) { 40.dp.toPx() }.toInt() * 15 / 4 + 10
    LaunchedEffect(Unit) { rowScrollState.animateScrollTo(sizeInPx)}
    val columnScrollState = rememberScrollState()
    var selectedDate by remember {
        mutableStateOf(today)
    }
    var selectedWateringData by remember {
        mutableStateOf(wateringData.find{ stringToLocalDate(it.date) == selectedDate })
    }
    LaunchedEffect(selectedDate) {
        selectedWateringData = wateringData.find { stringToLocalDate(it.date) == selectedDate }
    }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(LightGreen50Color)
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Watering Calendar",
                    style = MaterialTheme.typography.subtitle1,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = selectedDate.toString(),
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }


            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .horizontalScroll(rowScrollState)
                ) {
                    for (day in allDays) {
                        DayBox(day, selectedDate) {
                            selectedDate = it
                        }
                    }
                }
            }

            Column (
                modifier = Modifier
                    .padding(top = 8.dp)
                    .verticalScroll(columnScrollState)
                    .weight(1f)
                    .height(IntrinsicSize.Min)
            ) {
                if (selectedWateringData?.plants?.isNullOrEmpty() == true){
                    Box(
                        modifier = Modifier .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No hay plantas a regar para ese día",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.height(8.dp))
                    selectedWateringData?.plants?.forEach { plant ->
                        WateringItem(plant, coroutineScope, selectedWateringData!!)
                    }
                }
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
            DarkGreen500Color
        } else if (dayText == today) {
            Color(0xFF4C7CAF)
        } else {
            LightGreen500Color
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
fun WateringItem(plant: Plant, coroutineScope: CoroutineScope, selectedWateringData: WateringData)
{
    Row (
        modifier = Modifier.fillMaxWidth()
    ){
        Card(
            modifier = Modifier
                .padding(bottom = 16.dp)
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
                ImageFromUrl(
                    url = plant.imageGallery[0],
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )

                Text(
                    text = plant.name,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                )
            }
        }

        Button(
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp)
                .align(Alignment.CenterVertically)
                .shadow(elevation = 8.dp, CircleShape),
            onClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    makePostRequest(plant.id, selectedWateringData)
                }
            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Blue,
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


private suspend fun makePostRequest(plantId: Int, selectedWateringData: WateringData) {
    val body = WateringRequest(date = selectedWateringData.date)

    try {
        Log.d("myTag", "plantId: $plantId, Body: $body")
        val response = PlantsClient.watering.postRequest(plantId, body)

        if (response.isSuccessful) {
            // Response is successful, parse the body
            val wateringResponse = response.body()
            val status = wateringResponse?.status
            val date = wateringResponse?.date
            Log.d("myTag", "Status: $status, Date: $date")
        } else {
            // Response is unsuccessful, handle accordingly
            Log.e("myTag", "Unsuccessful response: ${response.code()}")
        }
    } catch (e: Exception) {
        // Handle the failure
        Log.e("myTag", e.toString())
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