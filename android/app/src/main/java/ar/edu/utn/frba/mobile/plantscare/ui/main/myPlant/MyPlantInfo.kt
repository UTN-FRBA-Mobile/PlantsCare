package ar.edu.utn.frba.mobile.plantscare.ui.main.myPlant

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.model.PlantInfo
import ar.edu.utn.frba.mobile.plantscare.model.WateringStatus
import ar.edu.utn.frba.mobile.plantscare.ui.main.stringToLocalDate
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.ImageFromUrl
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.TextWithTitle
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.APICallState
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.loadScreen
import java.time.LocalDate

@Composable
fun MyPlantInfoView(state: APICallState<PlantInfo>) {
    loadScreen(state) {
        ShowPreview(it)
    }
}

@Composable
private fun ShowPreview(plantInfo: PlantInfo?) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
    ) {
        plantInfo?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(text = it.name)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .height(300.dp)
                ) {
                    ImageFromUrl(
                        url = it.imageGallery[0],
                        modifier = Modifier
                            .aspectRatio(9f / 16f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                    ) {
                        TextWithTitle(title = "Size", text = it.properties.size)
                        Spacer(modifier = Modifier.height(24.dp))
                        TextWithTitle(title = "Environment", text = it.properties.environment)
                        Spacer(modifier = Modifier.height(24.dp))
                        TextWithTitle(title = "Sun Exposure", text = it.properties.sunExposure)
                        Spacer(modifier = Modifier.height(24.dp))
                        TextWithTitle(title = "Difficulty", text = it.properties.difficulty)
                        Spacer(modifier = Modifier.height(24.dp))
                        TextWithTitle(
                            title = "Watering Frequency",
                            text = it.currentWateringFrequency.toString() + " days"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextWithTitle(title = "Description", text = it.description)
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        val status = getStatus(plantInfo)
                        Text(text = getText(status))
                        Image(
                            painter = painterResource(id = getImage(status)),
                            contentDescription = "Water",
                            modifier = Modifier
                                .height(72.dp)
                                .width(72.dp)
                                .clickable { water(plantInfo, status) } ,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

        }
    }
}

private fun getStatus(plantInfo: PlantInfo): WateringStatus {
    return plantInfo.history.find { stringToLocalDate(it.date) == LocalDate.now() }?.status ?: WateringStatus.NO_INFO
}

private fun getText(status: WateringStatus): String {
    return when(status) {
        WateringStatus.NEEDS_WATERING -> "Need Water"
        WateringStatus.WATERED -> "Watered"
        else -> "Don't need water"
    }
}

private fun getImage(status: WateringStatus): Int {
    return when(status) {
        WateringStatus.NEEDS_WATERING -> R.drawable.waterdrop
        else -> R.drawable.happy_plant
    }
}

private fun water(plantInfo: PlantInfo, status: WateringStatus) {
    if (status == WateringStatus.NEEDS_WATERING) {
        Log.i("WATER", "watering plant ${plantInfo.id}")
    }
}