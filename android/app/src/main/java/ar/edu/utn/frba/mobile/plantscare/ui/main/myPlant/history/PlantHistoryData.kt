package ar.edu.utn.frba.mobile.plantscare.ui.main.myPlant.history

import ar.edu.utn.frba.mobile.plantscare.model.WateringStatus
import kotlinx.datetime.LocalDateTime

data class PlantHistoryData(
    val date: LocalDateTime,
    val status: WateringStatus,
    val color: PlantHistoryColor
)