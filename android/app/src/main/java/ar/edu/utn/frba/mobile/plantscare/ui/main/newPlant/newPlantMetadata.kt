package ar.edu.utn.frba.mobile.plantscare.ui.main.newPlant

import android.util.Log
import androidx.camera.core.ImageProxy
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.main.utils.AddButton
import ar.edu.utn.frba.mobile.plantscare.ui.theme.LightGreen50Color
import ar.edu.utn.frba.mobile.plantscare.ui.theme.LightYellow50Color

sealed class PlantMetadata(val name: String, val options: List<String> ) {
    object SunExposure : PlantMetadata("Sun Exposure", listOf("Low", "Moderate", "High"))
    object Environment : PlantMetadata("Environment", listOf("Indoor", "Outdoor"))
}

val plantMetadataItems = listOf(PlantMetadata.SunExposure, PlantMetadata.Environment)

@Composable
@androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
fun NewPlantMetadata(navController: NavHostController, imageProxy: ImageProxy) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            items(plantMetadataItems) { item ->
                SelectablePlantItem(item)
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        AddButton(
            textButton = R.string.add_plant_metadata_button,
            onClick = {
                Log.i("ImageProcessing", "Image Saved")
                Log.i("ImageProcessing", imageProxy?.image?.timestamp.toString() ?: "not found")
                imageProxy?.close()
            }
        )
    }

}
@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SelectablePlantItem(item: PlantMetadata) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Select an option") }


    Column(
        modifier = Modifier
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