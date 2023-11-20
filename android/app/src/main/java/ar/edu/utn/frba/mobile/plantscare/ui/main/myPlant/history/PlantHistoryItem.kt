package ar.edu.utn.frba.mobile.plantscare.ui.main.myPlant.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDateTime
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun PlantHistoryItem(item: PlantHistoryData) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.align(Alignment.CenterVertically)) {
            Icon(
                imageVector = Icons.Default.WaterDrop,
                contentDescription = null,
                tint = item.color.getValue()
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .background(
                    color = item.color.getValue(),
                    shape = RoundedCornerShape(20.dp)
                )
                .width(250.dp)
                .height(50.dp),
        ) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    text = formatDateTime(item.date),
                    //  modifier = Modifier.align(Alignment.Center),
                )
                Text(
                    text = item.status.toString().lowercase(Locale.ENGLISH).replace('_', ' '),
                    fontWeight = FontWeight.Light,
                    fontSize = 10.sp
                )
            }
        }
    }

}

private fun formatDateTime(localDateTime: LocalDateTime): String {
    val dayOfWeek = localDateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    val dayOfMonth = localDateTime.dayOfMonth
    return "$dayOfWeek $dayOfMonth"
}
