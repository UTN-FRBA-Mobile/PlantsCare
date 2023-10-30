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
import ar.edu.utn.frba.mobile.plantscare.model.PlantInfo
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.APICallState
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.loadScreen
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun PlantHistory(state: APICallState<PlantInfo>) {
    loadScreen(state) {
        println(it)
        PlanHistoryView(it)
    }
}

@Composable
private fun PlanHistoryView(plantInfo: PlantInfo?) {
    val plantHistories = plantInfo?.history?.map {
        PlantHistoryData(
            it.date.dropLast(1).toLocalDateTime(),
            it.status,
            PlantHistoryColor.fromEnum(it.status)
        )
    } ?: listOf()

    var lastDate by remember { mutableStateOf(Clock.System.now().toLocalDateTime(TimeZone.UTC)) }
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
                    if (intArrayOf(1, 28).contains(item.date.dayOfMonth)) {
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
    // PlantHistory()
}