package ar.edu.utn.frba.mobile.plantscare.ui.main.myPlant.history

import androidx.compose.ui.graphics.Color
import ar.edu.utn.frba.mobile.plantscare.ui.theme.Blue
import ar.edu.utn.frba.mobile.plantscare.ui.theme.Grey
import ar.edu.utn.frba.mobile.plantscare.ui.theme.SoftBlue
import ar.edu.utn.frba.mobile.plantscare.ui.theme.SoftRed

sealed class PlantHistoryColor {
    abstract fun getValue(): Color

    object WATERED : PlantHistoryColor() {
        override fun getValue(): Color = Blue
    }

    object NO_INFO : PlantHistoryColor() {
        override fun getValue(): Color = Grey
    }

    object NEEDS_WATERING : PlantHistoryColor() {
        override fun getValue(): Color = SoftBlue
    }
    object NOT_WATERED : PlantHistoryColor() {
        override fun getValue(): Color = SoftRed
    }

    companion object {
        fun fromEnum(enumValue: WateringStatus): PlantHistoryColor {
            return when (enumValue) {
                WateringStatus.NOT_WATERED -> NOT_WATERED
                WateringStatus.NO_INFO -> NO_INFO
                WateringStatus.NEEDS_WATERING -> NEEDS_WATERING
                WateringStatus.WATERED -> WATERED

            }
        }
    }
}