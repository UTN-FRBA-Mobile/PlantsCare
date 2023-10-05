package ar.edu.utn.frba.mobile.plantscare.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.ui.main.model.PlantInfo
import ar.edu.utn.frba.mobile.plantscare.ui.main.services.PlantsService
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.ImageFromUrl
import ar.edu.utn.frba.mobile.plantscare.ui.theme.SalmonColor

@Composable
fun MyPlantInfoView(navController: NavHostController) {
    ShowPreview();
}

@Composable
@Preview
private fun ShowPreview() {
    var plantInfo: PlantInfo? by remember { mutableStateOf(PlantsService.getPlantInfo(1)) }
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
            .background(SalmonColor)
    ) {
        plantInfo?.let {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(it.name)
                ImageFromUrl(url = "https://png.pngtree.com/png-clipart/20210329/ourlarge/pngtree-happy-plant-png-image_3141339.jpg",
                    modifier = Modifier.heightIn(10.dp, 100.dp).fillMaxWidth())
                Text(it.size)
                Text(it.environment)
                Text(it.sunExposure)
                Text(it.difficulty)
                Text(it.wateringFrequency.toString())
                Text("Plant Description: ${it.description}")
            }
        }
    }
}