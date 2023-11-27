package ar.edu.utn.frba.mobile.plantscare.ui.main.newPlant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.edu.utn.frba.mobile.plantscare.ui.theme.LightGreen50Color
import ar.edu.utn.frba.mobile.plantscare.ui.theme.LightYellow50Color
import java.util.Locale

//
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectableMetadataPlantItem(item: PlantMetadata, properties: MutableMap<String, String>) {
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
            onExpandedChange = { isExpanded = it }) {
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
                onDismissRequest = { isExpanded = false }) {
                item.options.forEach { option ->
                    DropdownMenuItem(
                        content = { Text(text = option) },
                        onClick = {
                            properties[item.property] = option.uppercase(Locale.ENGLISH)
                            selectedOption = option
                            isExpanded = false
                        }
                    )
                }
            }
        }

    }

}