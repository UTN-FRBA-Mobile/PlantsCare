package ar.edu.utn.frba.mobile.plantscare.ui.main.myPlant.history

import java.time.LocalDateTime

data class PlantHistoryData(
    val date: LocalDateTime,
    val status: WateringStatus,
    val color: PlantHistoryColor
)