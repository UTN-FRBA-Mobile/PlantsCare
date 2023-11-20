package ar.edu.utn.frba.mobile.plantscare.model

import kotlinx.serialization.Serializable

@Serializable
data class PlantWateringHistory (
    val date: String,
    val status: WateringStatus
)
@Serializable
data class PlantProperties (
    val size: String,
    val environment: String,
    val sunExposure: String,
    val difficulty: String,
)
@Serializable
data class PlantInfo(
    val id: Int,
    val type: String,
    val name: String,
    val description: String,
    val currentWateringFrequency: Int,
    val properties: PlantProperties,
    val imageGallery: List<String>,
    val wateringFrequency: Map<String, Int>,
    val history: List<PlantWateringHistory>
)