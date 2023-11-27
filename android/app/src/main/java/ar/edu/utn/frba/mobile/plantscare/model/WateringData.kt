package ar.edu.utn.frba.mobile.plantscare.model

import kotlinx.serialization.Serializable


@Serializable
data class WateringData(
  val date: String,
  val plants: List<Plant>
)

@Serializable
data class Plant(
  val id: Int,
  val name: String,
  val type: String,
  val description: String,
  val currentWateringFrequency: Int,
  val properties: PlantProperties,
  val imageGallery: List<String>
)

@Serializable
data class WateringRequest(
  val date: String
)

@Serializable
data class WateringResponse(
  val status: String,
  val date: String
)