package ar.edu.utn.frba.mobile.plantscare.ui.main.myPlant

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
import androidx.compose.ui.unit.dp
import ar.edu.utn.frba.mobile.plantscare.model.PlantInfo
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.ImageFromUrl
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.TextWithTitle
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.APICallState
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.loadScreen

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
            //.background(SalmonColor)
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
                    ImageFromUrl(url = it.imageGallery[0],
                        modifier = Modifier
                            .aspectRatio(9f / 16f))
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
                        TextWithTitle(title = "Watering Frequency", text = it.currentWateringFrequency.toString() + " days")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextWithTitle(title = "Description", text = it.description)
            }

        }
    }
}