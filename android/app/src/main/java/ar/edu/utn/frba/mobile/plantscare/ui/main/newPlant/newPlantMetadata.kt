package ar.edu.utn.frba.mobile.plantscare.ui.main.newPlant

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import androidx.camera.core.ImageProxy
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.model.PlantInfo
import ar.edu.utn.frba.mobile.plantscare.model.PlantProperties
import ar.edu.utn.frba.mobile.plantscare.services.NewPlantViewModel
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.AddButton
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.BitmapToFile
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.api.loadScreen
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.imageProxyToBitmap
import ar.edu.utn.frba.mobile.plantscare.ui.theme.LightGreen50Color
import ar.edu.utn.frba.mobile.plantscare.ui.theme.LightYellow50Color
import java.io.File

sealed class PlantMetadata(val name: String, val options: List<String> ) {

    object Size: PlantMetadata("Size", listOf("Small", "Medium", "Large"))
    object Difficulty: PlantMetadata("Difficulty", listOf("Easy", "Medium", "Hard"))
    object SunExposure : PlantMetadata("Sun Exposure", listOf("Low", "Medium", "High"))
    object Environment : PlantMetadata("Environment", listOf("Indoor", "Outdoor"))
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
    val file = BitmapToFile(navController.context,  bitmap)
    NewPlantImageMetadataViewModel(file, bitmap)

}
@Composable
fun NewPlantImageMetadataViewModel(file: File, bitmap: Bitmap) {

    val newPlantViewModel = viewModel<NewPlantViewModel>()
    newPlantViewModel.sendImage(file)

    loadScreen(newPlantViewModel.state) {
        NewPlantMetadataView(it, bitmap)
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NewPlantMetadataView(plant: PlantInfo, bitmap: Bitmap) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
               // .padding(32.dp)
        ) {
            item {
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
                        Text(text = plant.type, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.body2.copy(color = androidx.compose.ui.graphics.Color.Black))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = plant.description)
                    }

                }
            }
            items(plantMetadataItems) { item ->
                SelectablePlantItem(item)
              //  Spacer(modifier = Modifier.height(32.dp))
            }
            item {
                AddButton(
                        textButton = R.string.add_plant_metadata_button,
                        onClick = {
                            Log.i("ImageProcessing", "Image Saved")
                            // Log.i("ImageProcessing", imageProxy?.image?.timestamp.toString() ?: "not found")

                            //   imageProxy?.close()
                        }
                    )
                }
            }
}

    //
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SelectablePlantItem(item: PlantMetadata) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Select an option") }


    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 8.dp)
            .fillMaxWidth()
            .background(LightGreen50Color)
            .padding(16.dp)
    ) {
        Text(text = item.name)
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {isExpanded = it} ) {
            TextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    backgroundColor = LightYellow50Color,

                ),
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false}) {
                item.options.forEach{ option -> DropdownMenuItem(
                    content = { Text(text = option) },
                    onClick = {
                        selectedOption = option
                        isExpanded = false
                    }
                )
                }
            }
        }

    }

}

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