package ar.edu.utn.frba.mobile.plantscare.ui.main.newPlant

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.camera.core.ImageProxy
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.model.PlantInfo
import ar.edu.utn.frba.mobile.plantscare.model.PlantProperties
import ar.edu.utn.frba.mobile.plantscare.model.SimplePlantInfo
import ar.edu.utn.frba.mobile.plantscare.network.PlantsClient
import ar.edu.utn.frba.mobile.plantscare.services.MyPlantViewModel
import ar.edu.utn.frba.mobile.plantscare.services.NewPlantViewModel
import ar.edu.utn.frba.mobile.plantscare.ui.main.navigateToMyPlant
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.AddButton
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.BitmapToFile
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.loadScreen
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.imageProxyToBitmap
import java.io.File

sealed class PlantMetadata(val name: String, val property: String, val options: List<String> ) {
    object Size: PlantMetadata("Size", "size", listOf("Small", "Medium", "Large"))
    object Difficulty: PlantMetadata("Difficulty", "difficulty", listOf("Easy", "Medium", "Hard"))
    object SunExposure : PlantMetadata("Sun Exposure", "sunExposure", listOf("Low", "Medium", "High"))
    object Environment : PlantMetadata("Environment", "environment", listOf("Indoor", "Outdoor"))
}

val plantMetadataItems = listOf(
    PlantMetadata.SunExposure,
    PlantMetadata.Environment,
    PlantMetadata.Size,
    PlantMetadata.Difficulty
)

@Composable
fun NewPlantImageMetadata(navController: NavHostController, imageProxy: ImageProxy) {
    val bitmap = imageProxyToBitmap(imageProxy)
    if(bitmap != null) {
        val file = BitmapToFile(navController.context, bitmap)
        NewPlantImageMetadataViewModel(navController, file, bitmap)
    }
}
@Composable
fun NewPlantImageMetadataViewModel(navController: NavHostController, file: File, bitmap: Bitmap) {
    val newPlantViewModel = viewModel<NewPlantViewModel>()
    newPlantViewModel.sendImage(file)

    loadScreen(newPlantViewModel.state) {
        NewPlantMetadataView(navController, it, bitmap)
    }

}

@Composable
fun NewPlantMetadataView(navController: NavHostController, plant: PlantInfo, bitmap: Bitmap) {

    val properties by remember { mutableStateOf(mutableMapOf<String, String>())  }
    val myPlantViewModel = viewModel<MyPlantViewModel>()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            PlantImageDescription(bitmap, plant)
        }
        items(plantMetadataItems) { item ->
            SelectableMetadataPlantItem(item, properties)
        }
        item {
            AddPlantButton(navController, myPlantViewModel, plant, properties)
        }
    }

}

@Composable
private fun PlantImageDescription(
    bitmap: Bitmap,
    plant: PlantInfo
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp)
            .padding(8.dp)
    ) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier.aspectRatio(9f / 16f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = plant.type,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2.copy(color = androidx.compose.ui.graphics.Color.Black)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = plant.description)
        }

    }
}

@Composable
private fun AddPlantButton(
    navController: NavHostController,
    myPlantViewModel: MyPlantViewModel,
    plant: PlantInfo,
    properties: MutableMap<String, String>
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 60.dp)
            .scale(1.2f)
    ) {
        AddButton(
            textButton = R.string.add_plant_metadata_button,
            onClick = {
                updatePlant(myPlantViewModel, plant, properties)
                navigateToMyPlant(navController, plant.id)
            }
        )
    }
}

private fun updatePlant(
    myPlantViewModel: MyPlantViewModel,
    plant: PlantInfo,
    properties: MutableMap<String, String>
) {
    myPlantViewModel.setId(plant.id) {
        properties["updated"] = "updated"
        PlantsClient.myPlant.updatePlant(
            it,
            SimplePlantInfo(
                plant.name,
                PlantProperties(
                    size = properties["size"] ?: plant.properties.size,
                    environment = properties["environment"] ?: plant.properties.environment,
                    sunExposure = properties["sunExposure"] ?: plant.properties.sunExposure,
                    difficulty = properties["difficulty"] ?: plant.properties.difficulty,
                )
            )
        )
    }
}
/*
@Preview
@Composable
fun NewPlantMetadataPreview() {
    NewPlantMetadataView(
        plant = PlantInfo(
            2,
            "Mint",
            "My Mint",
            "the mint is mint",
            1,
            PlantProperties("s", "e", "s", "d"),
            listOf(),
            buildMap {
                put("January", 1)
            },
            listOf()
        ) ,
        bitmap = createExampleBitmap()
    )
}

 */


private fun createExampleBitmap(): Bitmap {
    val bitmapWidth = 466
    val bitmapHeight = 800

    val bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    // Draw a simple example content (a red square)
    val paint = Paint().apply {
        color = Color.RED
    }
    canvas.drawRect(50f, 50f, 250f, 250f, paint)

    return bitmap
}