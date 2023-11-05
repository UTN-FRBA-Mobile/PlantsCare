package ar.edu.utn.frba.mobile.plantscare.ui.main.myPlant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.utn.frba.mobile.plantscare.model.PlantInfo
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.APICallState
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.loadScreen
import ar.edu.utn.frba.mobile.plantscare.ui.theme.Blue
import ar.edu.utn.frba.mobile.plantscare.ui.theme.SkyBlue

@Composable
fun WateringFrequency(state: APICallState<PlantInfo>) {
    loadScreen(state) {
        WateringFrequencyView(it)
    }
}


@Composable
private fun WateringFrequencyView(plantInfo: PlantInfo?) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            // .background(SalmonColor)
            .padding(16.dp)
    ) {
        plantInfo?.let {
            LazyColumn {
                items(plantInfo.wateringFrequency.toList()) { (month, wateringFrequency) ->
                    Spacer(modifier = Modifier.height(12.dp))
                    WaterFrequencyItem(month = month, wateringFrequency = wateringFrequency)
                }
            }
        }


    }
}

@Composable
fun WaterFrequencyItem(month: String, wateringFrequency: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(SkyBlue)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(Blue)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = wateringFrequency.toString(),
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.width(24.dp))

            Column {
                Text(text = month)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    repeat(wateringFrequency) {
                        Box(
                            modifier = Modifier
                                .size(25.dp)
                                .background(Blue)
                        )
                    }
                }
            }
        }
    }
}