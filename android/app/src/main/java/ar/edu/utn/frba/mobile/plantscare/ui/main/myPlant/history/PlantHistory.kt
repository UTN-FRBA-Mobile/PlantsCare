package ar.edu.utn.frba.mobile.plantscare.ui.main.myPlant.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import kotlin.random.Random

enum class WateringStatus {
    WATERED,
    NO_INFO,
    NEEDS_WATERING,
    NOT_WATERED
}

private fun getPlantHistoryExample(): List<PlantHistoryData> {
    val startDate = LocalDateTime.of(2023, 10, 29, 0, 0)
    val endDate = LocalDateTime.of(2023, 12, 25, 0, 0)
    return generateSequence(startDate) { it.plusDays(1) }
        .takeWhile { !it.isAfter(endDate) }
        .map {
            val randomStatus = WateringStatus.values().let { it[Random.nextInt(it.size)] }
            PlantHistoryData(it, randomStatus, PlantHistoryColor.fromEnum(randomStatus))
        }
        .toList()
}



@Composable
fun PlantHistory() {
    val plantHistories = getPlantHistoryExample()
    var lastDate by remember { mutableStateOf(LocalDateTime.now()) }
    Box(
        //contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
           // .background(Color.White)
    ) {
        Column {
            Text(
                text = lastDate.month.toString().toLowerCase().capitalize(),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(16.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp)
            ) {
                items(plantHistories) { item ->
                    PlantHistoryItem(item)
                    Spacer(modifier = Modifier.height(16.dp))
                    if(intArrayOf(1, 10, 20, 30).contains(item.date.dayOfMonth)) {
                        DisposableEffect(Unit) {
                            lastDate = item.date
                            onDispose { }
                        }
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun PlantHistoryPreview() {
    PlantHistory()
}