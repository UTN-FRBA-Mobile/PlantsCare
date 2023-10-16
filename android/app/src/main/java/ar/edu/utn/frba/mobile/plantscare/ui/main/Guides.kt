package ar.edu.utn.frba.mobile.plantscare.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import ar.edu.utn.frba.mobile.plantscare.R
import ar.edu.utn.frba.mobile.plantscare.ui.theme.LightGreen50Color

data class PlantGuide(
    val name: String,
    val imageResId: Int,
    val timeToRead: Int
)

val myPlantGuidesList = listOf(
    PlantGuide("Planta 1", R.drawable.default_guide, 10),
    PlantGuide("Planta 2", R.drawable.default_guide, 10),
    PlantGuide("Planta 3", R.drawable.default_guide, 10),
    PlantGuide("Planta 4", R.drawable.default_guide, 10),
    PlantGuide("Planta 5", R.drawable.default_guide, 10),
    PlantGuide("Planta 6", R.drawable.default_guide, 10),
    PlantGuide("Planta 7", R.drawable.default_guide, 10),
    PlantGuide("Planta 8", R.drawable.default_guide, 10),
    PlantGuide("Menta 9", R.drawable.default_guide, 10),
    PlantGuide("Pothos 10", R.drawable.default_guide, 10),
)

@Composable
fun Guides(navController: NavHostController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        SearchAndItemList(items = myPlantGuidesList)
    }
}


@Composable
fun SearchAndItemList(items : List<PlantGuide>) {
    var searchQuery by remember { mutableStateOf("") }
    //val items = (1..10).toList() // Reemplaza con tu lista de elementos

    Column(
        modifier = Modifier.fillMaxSize()

    ) {
        Text(text = "Plants Guide", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(18.dp))
        SearchBar(searchQuery = searchQuery, onSearchQueryChange = { searchQuery = it })
        ItemList(items = items, searchQuery = searchQuery)
    }
}

@Composable
fun SearchBar(searchQuery: String, onSearchQueryChange: (String) -> Unit){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(LightGreen50Color)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        BasicTextField(value = searchQuery,
            onValueChange = { onSearchQueryChange(it) },
            modifier = Modifier
                .width(350.dp)
                .height(50.dp)
                .padding(8.dp)
        )
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search Icon",
            tint = Color.LightGray,
            modifier = Modifier
                .size(48.dp)
        )
    }
}

@Composable
fun ItemList(items : List<PlantGuide>, searchQuery : String) {
val filteredItems = items.filter { it.name.contains(searchQuery, ignoreCase = true) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        filteredItems.chunked(2) { rowItems ->
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                    ,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (item in rowItems) {
                        BoxItem(item = item)
                    }
                }
            }
        }
    }
}

@Composable
fun BoxItem(item: PlantGuide) {
    Box(
        modifier = Modifier
            .width(160.dp)
            .height(110.dp)
    ) {
        Image(painter = painterResource(id = item.imageResId),
            contentDescription = "background image",
            modifier = Modifier
                .fillMaxSize()
                .width(160.dp)
                .height(110.dp)
                .clip(RoundedCornerShape(8.dp))
            ,
            contentScale = ContentScale.Crop
            )
        Box(
            modifier = Modifier
                .width(100.dp)
                .background(Color.Gray.copy(alpha = 0.5f))
                .align(Alignment.BottomStart)
                .clip(RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Row {
                    Icon(
                        imageVector = Icons.Rounded.Info,
                        contentDescription = "Search Icon",
                        tint = Color.White,
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Text(
                        text = item.timeToRead.toString() + " mins",
                        fontSize = 11.sp,
                        color = Color.White
                    )
                }
                Text(
                    text = item.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchAndItemListPreview(@PreviewParameter(PreviewParameterProvider::class) params: Unit) {
    SearchAndItemList(items = myPlantGuidesList)
}
