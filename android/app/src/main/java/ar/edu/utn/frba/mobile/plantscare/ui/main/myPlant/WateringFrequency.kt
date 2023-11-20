package ar.edu.utn.frba.mobile.plantscare.ui.main.myPlant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.utn.frba.mobile.plantscare.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.utn.frba.mobile.plantscare.model.PlantInfo
import ar.edu.utn.frba.mobile.plantscare.model.PlantProperties
import ar.edu.utn.frba.mobile.plantscare.model.PlantWateringHistory
import ar.edu.utn.frba.mobile.plantscare.model.WateringStatus
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
private fun WateringFrequencyTitle(name: String) {
  Text(text = "Frecuencia de regado semanal de la planta: $name", fontSize = 28.sp)
}

@Composable
private fun WateringFrequencyView(plantInfo: PlantInfo?) {
  Box(
    contentAlignment = Alignment.TopCenter,
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    plantInfo?.let {
      LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item { WateringFrequencyTitle(plantInfo.name) }
        items(plantInfo.wateringFrequency.toList()) { (month, wateringFrequency) ->
          WaterFrequencyItem(month = month, wateringFrequency = wateringFrequency)
        }
      }
    }
  }
}

@Composable
private fun WateringFrequencyIndicatorText(frequency: String) {
  Text(
    modifier = Modifier
      .padding(8.dp)
      .drawBehind { drawCircle(color = Blue, radius = this.size.maxDimension) },
    text = frequency,
  )
}

@Composable
private fun WateringFrequencyIndicatorImage() {
  Image(
    painter = painterResource(id = R.drawable.waterdrop),
    contentDescription = stringResource(id = R.string.profile_image_content_description),
    modifier = Modifier.size(width = 25.dp, height = 35.dp)
  )
}

@Composable
fun WaterFrequencyItem(month: String, wateringFrequency: Int) {
  val boxModifier = Modifier
    .fillMaxWidth()
    .height(60.dp)
    .clip(RoundedCornerShape(20.dp))
    .background(SkyBlue)
    .padding(horizontal = 24.dp, vertical = 0.dp)

  Text(text = month, fontSize = 22.sp)
  Box(modifier = boxModifier, contentAlignment = Alignment.CenterStart) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Start,
    ) {
      WateringFrequencyIndicatorText(wateringFrequency.toString())
      Spacer(modifier = Modifier.width(24.dp))
      repeat(wateringFrequency) { WateringFrequencyIndicatorImage() }
    }
  }
}

private fun getFakePlantInfo(): PlantInfo {
  val plantProperties = PlantProperties(
    "2",
    "environment",
    "sun exposure",
    "high"
  )

  return PlantInfo(
    id = 1,
    type = "Fern",
    name = "Boston Fern",
    description = "A beautiful fern plant...",
    currentWateringFrequency = 7,
    properties = PlantProperties(
      size = "Medium",
      environment = "Indoor",
      sunExposure = "Indirect sunlight",
      difficulty = "Moderate"
    ),
    imageGallery = listOf(
      "https://example.com/image1.jpg",
      "https://example.com/image2.jpg"
    ),
    wateringFrequency = mapOf(
      "spring" to 7,
      "summer" to 6,
      "fall" to 6,
      "winter" to 8
    ),
    history = listOf(
      PlantWateringHistory("2023-10-01", WateringStatus.WATERED),
      PlantWateringHistory("2023-10-10", WateringStatus.WATERED),
      PlantWateringHistory("2023-10-20", WateringStatus.NEEDS_WATERING)
    )
  )
}

@Preview
@Composable
private fun WateringFrequencyViewPreview() {
  val plantInfo = getFakePlantInfo()
  WateringFrequencyView(plantInfo)
}